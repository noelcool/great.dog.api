package great.dog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import great.dog.api.domain.entity.Dog;

import java.util.Optional;

public interface DogRepository extends JpaRepository<Dog, Long> {

    Optional<Dog> findByUserIdAndName(Long id, String name);

    Optional<Dog> findByUserIdAndIdAndDelYn(Long userId, Long id, String string);

    Optional<Dog> findByUserIdAndNameAndDelYn(Long userId, String name, String string);

    Optional<Dog> findByIdAndDelYn(Long id, String string);
}
