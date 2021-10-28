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
    public int save(DogDiseaseDto.SaveRequest request) {
        if (Objects.isNull(request.getDogId())) return -1;
        Optional<Dog> oDog = dogRepository.findById(request.getDogId());
        if (!oDog.isPresent()) return -1;
        DogDisease.DogDiseaseBuilder builder = DogDisease.builder().
                name(request.getName()).
                region(request.getRegion()).
                comment(request.getComment()).
                dog(oDog.get());
        return dogDiseaseRepository.save(builder.build()) != null ? 1 : 0;
    }

    @Transactional
    public int update(Long id, DogDiseaseDto.UpdateRequest request) {
        Optional<DogDisease> dogDisease = dogDiseaseRepository.findById(id);
        if (!dogDisease.isPresent()) return -1;
        dogDisease.ifPresent(d -> {
            if(request.getName() != null) d.setName(request.getName());
            if(request.getRegion() != null) d.setRegion(request.getRegion());
            if(request.getComment() != null) d.setComment(request.getComment());
            if(request.getDelYn() != null) d.setDelYn(request.getDelYn());
            dogDiseaseRepository.save(d);
        });
        return 1;
    }
}
