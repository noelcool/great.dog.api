package great.dog.api.service;

import great.dog.api.domain.dto.DogDiseaseDto;
import great.dog.api.domain.entity.Dog;
import great.dog.api.domain.entity.DogDisease;
import great.dog.api.repository.DogDiseaseRepository;
import great.dog.api.repository.DogRepository;
import lombok.RequiredArgsConstructor;
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
public class DogDiseaseService {

    private final ModelMapper modelMapper;
    private final DogDiseaseRepository dogDiseaseRepository;
    private final DogRepository dogRepository;

    public DogDiseaseDto.Response findById(Long id) {
        Optional<DogDisease> dogDisease = dogDiseaseRepository.findById(id);
        return dogDisease.map(value -> modelMapper.map(value, DogDiseaseDto.Response.class)).orElse(null);
    }

    public List<DogDiseaseDto.Response> findByDogId(Long dogId) {
        List<DogDisease> dogDiseases = dogDiseaseRepository.findByDogId(dogId);
        return dogDiseases.stream().map(value -> modelMapper.map(value, DogDiseaseDto.Response.class)).collect(Collectors.toList());
    }

    @Transactional
    public int save(DogDiseaseDto.SaveRequest dto) {
        if (Objects.isNull(dto.getDogId())) return -1;
        Optional<Dog> oDog = dogRepository.findById(dto.getDogId());
        if (!oDog.isPresent()) return -1;
        DogDisease.DogDiseaseBuilder builder = DogDisease.builder().
                name(dto.getName()).
                region(dto.getRegion()).
                comment(dto.getComment()).
                dog(oDog.get());
        return dogDiseaseRepository.save(builder.build()) != null ? 1 : 0;
    }

    @Transactional
    public int update(Long id, DogDiseaseDto.UpdateRequest dto) {
        Optional<DogDisease> dogDisease = dogDiseaseRepository.findById(id);
        if (!dogDisease.isPresent()) return -1;
        Optional<Dog> dog = dogRepository.findById(dto.getDogId());
        if (!dog.isPresent()) return -1;
        dogDisease.ifPresent(d -> {
            if(dto.getName() != null) d.setName(dto.getName());
            if(dto.getRegion() != null) d.setRegion(dto.getRegion());
            if(dto.getComment() != null) d.setComment(dto.getComment());
            if(dto.getDelYn() != null) d.setDelYn(dto.getDelYn());
            d.setDog(dog.get());
            dogDiseaseRepository.save(d);
        });
        return 1;
    }
}
