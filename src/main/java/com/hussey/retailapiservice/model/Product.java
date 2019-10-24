package com.hussey.retailapiservice.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Integer productId;
    @NotEmpty(message = "Name value should not be empty")
    @Size(max = 50, message = "Name was too long")
    private String productName;
    @NotEmpty(message = "Description value should not be empty")
    @Size(max = 255, message = "Description was too long")
    private String productDescription;
    @NotNull(message = "list price must be included")
    @Positive(message = "Price must more that zero")
    @Digits(integer = 5, fraction = 2, message = "Price was in an incorrect format")
    private BigDecimal listPrice;
    @NotNull(message = "unit cost must be included")
    @Positive(message = "Cost must be more that zero")
    @Digits(integer = 5, fraction = 2, message = "Price was in an incorrect format")
    private BigDecimal unitCost;

    public Product() {
    }

    public Product(@NotEmpty(message = "Name value should not be empty") @Size(max = 50, message = "Name was too long") String productName, @NotEmpty(message = "Description value should not be empty") @Size(max = 255, message = "Description was too long") String productDescription, @NotNull(message = "list price must be included") @Digits(integer = 5, fraction = 2, message = "Price was in an incorrect format") BigDecimal listPrice, @NotNull(message = "unit cost must be included") @Digits(integer = 5, fraction = 2, message = "Price was in an incorrect format") BigDecimal unitCost) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.listPrice = listPrice;
        this.unitCost = unitCost;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                productName.equals(product.productName) &&
                productDescription.equals(product.productDescription) &&
                listPrice.equals(product.listPrice) &&
                unitCost.equals(product.unitCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productDescription, listPrice, unitCost);
    }
}
