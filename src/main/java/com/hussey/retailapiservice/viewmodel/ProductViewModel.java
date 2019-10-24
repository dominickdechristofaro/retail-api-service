package com.hussey.retailapiservice.viewmodel;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductViewModel {
    // Properties
    private String productName;
    private String productDescription;
    private BigDecimal listPrice;

    // Constructors
    public ProductViewModel() {

    }

    public ProductViewModel(String productName, String productDescription, BigDecimal listPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.listPrice = listPrice;
    }

    // Getters
    public String getProductName() {
        return this.productName;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public BigDecimal getListPrice() {
        return this.listPrice;
    }

    // Setters
    public void setProductName(String productNameIn) {
        this.productName = productNameIn;
    }

    public void setProductDescription(String productDescriptionIn) {
        this.productDescription = productDescriptionIn;
    }

    public void setListPrice(BigDecimal listPriceIn) {
        this.listPrice = listPriceIn;
    }

    // equals(), hashCode(), and equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductViewModel that = (ProductViewModel) o;
        return Objects.equals(productName, that.productName) &&
                Objects.equals(productDescription, that.productDescription) &&
                Objects.equals(listPrice, that.listPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productDescription, listPrice);
    }

    @Override
    public String toString() {
        return "ProductViewModel{" +
                "productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", listPrice=" + listPrice +
                '}';
    }
}
