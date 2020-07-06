package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.model.Category;
import ru.geekbrains.model.Product;
import ru.geekbrains.repo.CategoryRepository;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String categoryList(Model model,
                              @RequestParam(name = "partName", required = false, defaultValue = "") String partName,
                              @RequestParam(name = "page") Optional<Integer> page,
                              @RequestParam(name = "size") Optional<Integer> size) {
        logger.info("Category list");
        Page<Category> categoryPage;
            System.out.println("partName!=null");
            categoryPage = categoryService.filterName(partName,
                    PageRequest.of(page.orElse(1) - 1, size.orElse(5)));

        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("prevPageNumber", categoryPage.hasPrevious() ? categoryPage.previousPageable().getPageNumber() + 1 : -1);
        model.addAttribute("nextPageNumber", categoryPage.hasNext() ? categoryPage.nextPageable().getPageNumber() + 1 : -1);

        return "categories";
    }

    @GetMapping("new")
    public String createCategory(Model model) {
        logger.info("Create category form");
        System.out.println("1 step");
        model.addAttribute("category", new Category());
        return "category";
    }

    @PostMapping
    public String saveCategory(Category category) {
        logger.info("Save category method");

        categoryService.save(category);
        return "redirect:/category";
    }

//    @GetMapping("edit")
//    public String editProduct(Model model) {
//        logger.info("Edit Product form");
//
//        model.addAttribute("product", new Product());
//        return "productEdit";
//    }

//    @PostMapping ("productEdit")
//    public String editProduct(Product product, BindingResult bindingResult) {
//        logger.info("Edit user method");
//
//        if (bindingResult.hasErrors()) {
//            return "product";
//        }
//        productService.editProduct(product);
//        return "redirect:/product";
//    }

    @GetMapping("edit")
    public String editCategory(@RequestParam long id, Model model){

        model.addAttribute("category", categoryService.findById(id));
        return "category";

    }

    @DeleteMapping
    public String deleteCategory(@RequestParam Long id){
        System.out.println("delete");
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
}
