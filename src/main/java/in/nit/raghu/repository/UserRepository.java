package in.nit.raghu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.raghu.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
}
