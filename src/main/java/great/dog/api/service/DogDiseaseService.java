package great.dog.api.service;

import great.dog.api.domain.DogDiseaseDto;
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

    public DogDiseaseDto findById(Long id) {
        Optional<DogDisease> dogDisease = dogDiseaseRepository.findByIdAndDelYn(id, "N");
        return dogDisease.map(value -> modelMapper.map(value, DogDiseaseDto.class)).orElse(null);
    }

    public List<DogDiseaseDto> findByDogId(Long dogId) {
        List<DogDisease> dogDiseases = dogDiseaseRepository.findByDogIdAndDelYn(dogId, "N");
        return dogDiseases.stream().map(value -> modelMapper.map(value, DogDiseaseDto.class)).collect(Collectors.toList());
    }

    @Transactional
    public int save(DogDiseaseDto.Request dto) {
        if (Objects.isNull(dto.getDogId())) return -1;
        Optional<Dog> oDog = dogRepository.findByIdAndDelYn(dto.getDogId(), "N");
        if (!oDog.isPresent()) return -1;
        DogDisease.DogDiseaseBuilder builder = DogDisease.builder().
                name(dto.getName()).
                region(dto.getRegion()).
                comment(dto.getComment());
        return dogDiseaseRepository.save(builder.build()) != null ? 1 : 0;
    }

    @Transactional
    public int update(Long id, DogDiseaseDto.Request dto) {
        Optional<DogDisease> dogDisease = dogDiseaseRepository.findByIdAndDelYn(id, "N");
        if (!dogDisease.isPresent()) return -1;
        dogDisease.ifPresent(d -> {
            if(dto.getName() != null) d.setName(dto.getName());
            if(dto.getRegion() != null) d.setRegion(dto.getRegion());
            if(dto.getComment() != null) d.setComment(dto.getComment());
            dogDiseaseRepository.save(d);
        });
        return 0;
    }
}
