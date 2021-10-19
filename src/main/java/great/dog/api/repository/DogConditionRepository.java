package great.dog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DogConditionRepository extends JpaRepository<great.dog.api.domain.entity.DogCondition, Long> {

}
