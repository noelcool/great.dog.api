package great.dog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import great.dog.api.domain.entity.DogDisease;

public interface DogDiseaseRepository extends JpaRepository<DogDisease, Long> {

}
