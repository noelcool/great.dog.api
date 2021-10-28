package great.dog.api.repository;

import great.dog.api.domain.entity.DogFeeding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DogFeedingRepository extends JpaRepository<DogFeeding, Long> {

    Optional<DogFeeding> findById(Long id);

    List<DogFeeding> findByDogId(Long dogId);
}
