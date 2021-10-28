package great.dog.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import great.dog.api.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByName(String name);

    Optional<User> findById(Long id);

}
