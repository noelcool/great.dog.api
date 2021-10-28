package great.dog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import great.dog.api.domain.entity.Dog;

import java.util.List;
import java.util.Optional;

public interface DogRepository extends JpaRepository<Dog, Long> {

    Optional<Dog> findByUserIdAndName(Long id, String name);

    Optional<Dog> findByUserIdAndId(Long userId, Long id);

    Optional<Dog> findById(Long id);

    List<Dog> findByUserId(Long id);
}
