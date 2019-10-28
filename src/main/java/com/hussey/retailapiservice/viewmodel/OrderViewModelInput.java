package com.hussey.retailapiservice.viewmodel;
import java.util.HashMap;
import java.util.Objects;

public class OrderViewModelInput {
    // Properties
    private Integer customerId;
    private HashMap<Integer, Integer> orderMap;

    // Constructors
    public OrderViewModelInput() {

    }

    public OrderViewModelInput(Integer customerId, HashMap<Integer, Integer> orderMap) {
        this.customerId = customerId;
        this.orderMap = orderMap;
    }

    // Getters
    public Integer getCustomerId() {
        return this.customerId;
    }

    public HashMap<Integer, Integer> getOrderMap() {
        return this.orderMap;
    }

    // Setters
    public void setCustomerId(Integer customerIdIn) {
        this.customerId = customerIdIn;
    }

    public void setOrderMap(HashMap<Integer, Integer> orderListIn) {
        this.orderMap = orderListIn;
    }

    // equals(), hashCode(), toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderViewModelInput that = (OrderViewModelInput) o;
        return customerId.equals(that.customerId) &&
                orderMap.equals(that.orderMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, orderMap);
    }

    @Override
    public String toString() {
        return "OrderViewModelInput{" +
                "customerId=" + customerId +
                ", orderList=" + orderMap +
                '}';
    }
}
