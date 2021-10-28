package great.dog.api.repository;

import great.dog.api.domain.entity.DogCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DogConditionRepository extends JpaRepository<DogCondition, Long> {

    Optional<DogCondition> findById(Long id);

    List<DogCondition> findByDogId(Long dogId);
}
