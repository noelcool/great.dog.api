package great.dog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import great.dog.api.domain.entity.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {

}
