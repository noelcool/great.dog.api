package great.dog.api.repository;

import great.dog.api.domain.entity.DogDisease;
import great.dog.api.domain.entity.DogMedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogMedicalHistoryRepository extends JpaRepository<DogMedicalHistory, Long> {
}
