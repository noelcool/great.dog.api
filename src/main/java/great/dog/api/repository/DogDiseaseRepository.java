package great.dog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import great.dog.api.domain.entity.DogDisease;

import java.util.List;
import java.util.Optional;

public interface DogDiseaseRepository extends JpaRepository<DogDisease, Long> {

    List<DogDisease> findByDogIdAndDelYn(Long dogId, String n);

    Optional<DogDisease> findByIdAndDelYn(Long id, String n);
}
