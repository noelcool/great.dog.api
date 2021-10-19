package great.dog.api.service;

import great.dog.api.domain.entity.DogCondition;
import great.dog.api.domain.request.DogConditionRequest;
import great.dog.api.domain.response.DogConditionResponse;
import great.dog.api.domain.response.DogResponse;
import great.dog.api.repository.DogConditionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DogConditionService {

    private final ModelMapper modelMapper;
    private final DogConditionRepository dogConditionRepository;
    
    public List<DogConditionResponse> findAll() {
        List<DogCondition> dogConditions = dogConditionRepository.findAll();
        return dogConditions.stream().map(d -> modelMapper.map(d, DogConditionResponse.class)).collect(Collectors.toList());
    }

    public int save(DogConditionRequest dto) {
        return 0;
    }

    public int update(Long id, DogConditionRequest dto) {
        return 0;
    }

    public DogConditionResponse findById(Long id) {
        return null;
    }
}
