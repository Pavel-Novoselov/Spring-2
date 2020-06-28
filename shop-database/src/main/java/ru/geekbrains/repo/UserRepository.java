package ru.geekbrains.repo;

import ru.geekbrains.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);
    Optional<User> findByUsernameAndEmail(String name, String email);

    Page<User> findByAgeGreaterThan(Integer minAge, Pageable pageable);
    Page<User> findByAgeLessThan (Integer maxAge, Pageable pageable);
    Page<User> findByAgeGreaterThanEqualAndAgeLessThanEqual(Integer minAge, Integer maxAge, Pageable pageable);
    Page<User> findByAgeGreaterThanEqualAndAgeLessThanEqualAndUsernameLike(Integer minAge, Integer maxAge, String name, Pageable pageable);

    Optional<User> findUserByUsername(String username);


}
