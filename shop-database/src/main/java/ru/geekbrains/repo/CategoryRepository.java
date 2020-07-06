package ru.geekbrains.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Category;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);

    Page<Category> findByTitleLike (String partName, Pageable pabeable);

    Page<Category> findAll (Pageable pabeable);
}
