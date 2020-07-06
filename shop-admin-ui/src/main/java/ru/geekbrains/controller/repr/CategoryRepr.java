package ru.geekbrains.controller.repr;

import ru.geekbrains.model.Category;

import java.io.Serializable;

public class CategoryRepr implements Serializable {

    private long id;

    private String title;

    private String description;

    private long productCount;

    public CategoryRepr(long id, String name, String description, long productCount) {
        this.id = id;
        this.title = name;
        this.description = description;
        this.productCount = productCount;
    }

    public CategoryRepr(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getProductCount() {
        return productCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
