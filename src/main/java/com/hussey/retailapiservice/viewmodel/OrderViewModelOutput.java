package com.hussey.retailapiservice.viewmodel;
import com.hussey.retailapiservice.model.InvoiceItem;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class OrderViewModelOutput {
    // Properties
    private String name;
    private Integer customerId;
    private Integer invoiceId;
    private LocalDate purchaseDate;
    private List<InvoiceItem> invoiceItemList;
    private BigDecimal total;
    private Integer levelUpOrderPoints;
    private Integer levelUpTotalPoints;

    // Constructors
    public OrderViewModelOutput() {

    }

    public OrderViewModelOutput(String name, Integer customerId, Integer invoiceId, LocalDate purchaseDate, List<InvoiceItem> invoiceItemList, BigDecimal total, Integer levelUpOrderPoints, Integer levelUpTotalPoints) {
        this.name = name;
        this.customerId = customerId;
        this.invoiceId = invoiceId;
        this.purchaseDate = purchaseDate;
        this.invoiceItemList = invoiceItemList;
        this.total = total;
        this.levelUpOrderPoints = levelUpOrderPoints;
        this.levelUpTotalPoints = levelUpTotalPoints;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public Integer getInvoiceId() {
        return this.invoiceId;
    }

    public LocalDate getPurchaseDate() {
        return this.purchaseDate;
    }

    public List<InvoiceItem> getInvoiceItemList() {
        return this.invoiceItemList;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public Integer getLevelUpOrderPoints() {
        return this.levelUpOrderPoints;
    }

    public Integer getLevelUpTotalPoints() {
        return this.levelUpTotalPoints;
    }

    // Setters
    public void setName(String nameIn) {
        this.name = nameIn;
    }

    public void setCustomerId(Integer customerIdIn) {
        this.customerId = customerIdIn;
    }

    public void setInvoiceId(Integer invoiceIdIn) {
        this.invoiceId = invoiceIdIn;
    }

    public void setPurchaseDate(LocalDate purchaseDateIn) {
        this.purchaseDate = purchaseDateIn;
    }

    public void setInvoiceItemList(List<InvoiceItem> invoiceItemListIn) {
        this.invoiceItemList = invoiceItemListIn;
    }

    public void setTotal(BigDecimal totalIn) {
        this.total = totalIn;
    }

    public void setLevelUpOrderPoints(Integer levelUpOrderPointsIn) {
        this.levelUpOrderPoints = levelUpOrderPointsIn;
    }

    public void setLevelUpTotalPoints(Integer levelUpTotalPointsIn) {
        this.levelUpTotalPoints = levelUpTotalPointsIn;
    }

    // equals(), hashCode(), toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderViewModelOutput that = (OrderViewModelOutput) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(invoiceId, that.invoiceId) &&
                Objects.equals(purchaseDate, that.purchaseDate) &&
                Objects.equals(invoiceItemList, that.invoiceItemList) &&
                Objects.equals(total, that.total) &&
                Objects.equals(levelUpOrderPoints, that.levelUpOrderPoints) &&
                Objects.equals(levelUpTotalPoints, that.levelUpTotalPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, customerId, invoiceId, purchaseDate, invoiceItemList, total, levelUpOrderPoints, levelUpTotalPoints);
    }

    @Override
    public String toString() {
        return "OrderViewModelOutput{" +
                "name='" + name + '\'' +
                ", customerId=" + customerId +
                ", invoiceId=" + invoiceId +
                ", purchaseDate=" + purchaseDate +
                ", invoiceItemList=" + invoiceItemList +
                ", total=" + total +
                ", levelUpOrderPoints=" + levelUpOrderPoints +
                ", levelUpTotalPoints=" + levelUpTotalPoints +
                '}';
    }
}
