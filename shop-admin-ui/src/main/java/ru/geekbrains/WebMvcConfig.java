package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.geekbrains.model.Brand;
import ru.geekbrains.model.Category;
import ru.geekbrains.model.Role;
import ru.geekbrains.repo.BrandRepository;
import ru.geekbrains.repo.CategoryRepository;
import ru.geekbrains.repo.RoleRepository;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private List<Formatter<?>> formatters;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        formatters.forEach(registry::addFormatter);
    }

    @Bean
    public Formatter<Role> roleFormatter() {
        return new Formatter<Role>() {

            @Autowired
            private RoleRepository repository;

            @Override
            public Role parse(String id, Locale locale) throws ParseException {
                return repository.findById(Long.parseLong(id))
                        .orElseThrow(() -> new ParseException("No Role with id " + id, 0));
            }

            @Override
            public String print(Role role, Locale locale) {
                return role.getId().toString();
            }
        };
    }

    @Bean
    public Formatter<Category> categoryFormatter() {
        return new Formatter<Category>() {

            @Autowired
            private CategoryRepository repository;

            @Override
            public Category parse(String id, Locale locale) throws ParseException {
                return repository.findById(Long.parseLong(id))
                        .orElseThrow(() -> new ParseException("No Category with id " + id, 0));
            }

            @Override
            public String print(Category cat, Locale locale) {
                return cat.getId().toString();
            }
        };
    }

    @Bean
    public Formatter<Brand> brandFormatter() {
        return new Formatter<Brand>() {

            @Autowired
            private BrandRepository repository;

            @Override
            public Brand parse(String id, Locale locale) throws ParseException {
                return repository.findById(Long.parseLong(id))
                        .orElseThrow(() -> new ParseException("No Brand with id " + id, 0));
            }

            @Override
            public String print(Brand brand, Locale locale) {
                return brand.getId().toString();
            }
        };
    }
}
