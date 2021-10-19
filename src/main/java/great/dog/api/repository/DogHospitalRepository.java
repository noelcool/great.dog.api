package great.dog.api.repository;

import great.dog.api.domain.entity.DogHospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogHospitalRepository extends JpaRepository<DogHospital, Long> {
}
