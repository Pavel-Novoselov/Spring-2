package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.model.Category;
import ru.geekbrains.model.Product;
import ru.geekbrains.repo.CategoryRepository;
import ru.geekbrains.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void editCategory(Category category){
        Optional<Category> categoryFromDB = categoryRepository.findByTitle(category.getTitle());
        if(categoryFromDB.isPresent()){
            Category p = categoryFromDB.get();
            p.setDescription(category.getDescription());
            categoryRepository.save(p);
        }
    }

    @Transactional
    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }

    @Transactional
    public Page<Category> filterName (String partName, Pageable pageable) {
        String pName = partName+"%";
        return categoryRepository.findByTitleLike(pName, pageable);
    }

    @Transactional
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }
}
