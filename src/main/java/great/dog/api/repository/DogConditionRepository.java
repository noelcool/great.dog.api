package great.dog.api.repository;

import great.dog.api.domain.entity.DogCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DogConditionRepository extends JpaRepository<great.dog.api.domain.entity.DogCondition, Long> {

    List<DogCondition> findByDogId(Long dogId);

    Optional<DogCondition> findByIdAndDelYn(Long id, String n);

    List<DogCondition> findByDogIdAndDelYn(Long dogId, String n);
}
