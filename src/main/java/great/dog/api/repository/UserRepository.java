package great.dog.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import great.dog.api.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByName(String name);

    Optional<User> findById(Long id);

    @EntityGraph(attributePaths = "userRoles") //left outer join으로 보유권한 리스트를 바로 가져온다
    Optional<User> findWithUserRolesByEmail(String email);

    boolean existsByEmail(String email);
}
