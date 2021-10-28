package great.dog.api.service;

import great.dog.api.domain.dto.DogFeedingDto;
import great.dog.api.domain.entity.Dog;
import great.dog.api.domain.entity.DogFeeding;
import great.dog.api.repository.DogFeedingRepository;
import great.dog.api.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<DogFeeding> dogFeeding = dogFeedingRepository.findByDogId(dogId);
        return dogFeeding.stream().map(value -> modelMapper.map(value, DogFeedingDto.Response.class)).collect(Collectors.toList());
    }

    public int save(DogFeedingDto.SaveRequest request) {
        if (Objects.isNull(request.getDogId())) return -1;
        Optional<Dog> oDog = dogRepository.findById(request.getDogId());
        if(!oDog.isPresent()) return -1;
        DogFeeding.DogFeedingBuilder builder = DogFeeding.builder().
                type(request.getType()).
                quantity(request.getQuantity()).
                feedingTimestamp(request.getFeedingTimestamp()).
                dog(oDog.get());
        return dogFeedingRepository.save(builder.build()) != null ? 1 : 0;
    }

    public int update(Long id, DogFeedingDto.UpdateRequest request) {
        Optional<DogFeeding> dogFeeding = dogFeedingRepository.findById(id);
        if (!dogFeeding.isPresent()) return -1;
        dogFeeding.ifPresent(d -> {
            if(StringUtils.isNotBlank(request.getType())) d.setType(request.getType());
            if(StringUtils.isNotBlank(request.getQuantity())) d.setQuantity(request.getQuantity());
            if(request.getFeedingTimestamp() != null) d.setFeedingTimestamp(request.getFeedingTimestamp());
            dogFeedingRepository.save(d);
        });
        return 1;
    }
}
