package great.dog.api.repository;

import great.dog.api.domain.entity.DogFeeding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogFeedingRepository extends JpaRepository<DogFeeding, Long> {
}
