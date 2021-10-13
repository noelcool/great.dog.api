package great.dog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import great.dog.api.domain.entity.Dog;
import great.dog.api.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
