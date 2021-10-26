package great.dog.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import great.dog.api.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByNameAndDelYn(String name, String string);

    Optional<User> findByIdAndDelYn(Long id, String n);
}
