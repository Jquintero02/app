package com.grandma.app.products.dto;

import com.grandma.app.products.enums.CategoryEnum;

import java.math.BigDecimal;

public class ProductDto {
    public ProductDto() {
    }

    private String fantasyName;
    private CategoryEnum category;
    private String description;
    private BigDecimal price;
    private boolean available;

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
