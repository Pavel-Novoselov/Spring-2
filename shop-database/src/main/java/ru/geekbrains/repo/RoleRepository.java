package ru.geekbrains.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.geekbrains.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    Page<Role> findByNameLike(String pName, Pageable pageable);

}
