package great.dog.api.service;

import great.dog.api.domain.dto.DogFeedingDto;
import great.dog.api.domain.entity.Dog;
import great.dog.api.domain.entity.DogFeeding;
import great.dog.api.repository.DogFeedingRepository;
import great.dog.api.repository.DogRepository;
import great.dog.api.util.TypeUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DogFeedingService {

    private final DogFeedingRepository dogFeedingRepository;
    private final ModelMapper modelMapper;
    private final DogRepository dogRepository;

    public DogFeedingDto.Response findById(Long id) {
        Optional<DogFeeding> dogFeeding = dogFeedingRepository.findById(id);
        return dogFeeding.map(value -> modelMapper.map(value, DogFeedingDto.Response.class)).orElse(null);
    }

    public List<DogFeedingDto.Response> findByDogId(Long dogId) {
        List<DogFeeding> dogFeeding = dogFeedingRepository.findByDogIdOrderByFeedingTimestamp(dogId);
        return dogFeeding.stream().map(value -> modelMapper.map(value, DogFeedingDto.Response.class)).collect(Collectors.toList());
    }

    public int save(DogFeedingDto.SaveRequest dto) {
        if (Objects.isNull(dto.getDogId())) return -1;
        Optional<Dog> oDog = dogRepository.findById(dto.getDogId());
        if(!oDog.isPresent()) return -1;
        DogFeeding.DogFeedingBuilder builder = DogFeeding.builder().
                type(dto.getType()).
                name(dto.getName()).
                quantity(dto.getQuantity()).
                feedingTimestamp(Timestamp.valueOf(dto.getFeedingTimestamp())).
                dog(oDog.get());
        return dogFeedingRepository.save(builder.build()) != null ? 1 : 0;
    }

    @Transactional
    public int update(Long id, DogFeedingDto.UpdateRequest dto) {
        Optional<DogFeeding> dogFeeding = dogFeedingRepository.findById(id);
        if (!dogFeeding.isPresent()) return -1;
        Optional<Dog> dog = dogRepository.findById(dto.getDogId());
        if (!dog.isPresent()) return -1;
        dogFeeding.ifPresent(d -> {
            if(StringUtils.isNotBlank(dto.getType())) d.setType(dto.getType());
            if(StringUtils.isNotBlank(dto.getName())) d.setName(dto.getName());
            if(StringUtils.isNotBlank(dto.getQuantity())) d.setQuantity(dto.getQuantity());
            if(StringUtils.isNotBlank(dto.getFeedingTimestamp())) d.setFeedingTimestamp(TypeUtils.StringToTimestamp(dto.getFeedingTimestamp()));
            if(StringUtils.isNotBlank(dto.getDelYn())) d.setDelYn(dto.getDelYn());
            d.setDog(dog.get());
            dogFeedingRepository.save(d);
        });
        return 1;
    }
}
