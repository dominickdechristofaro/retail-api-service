package com.hussey.retailapiservice.service;
import com.hussey.retailapiservice.model.*;
import com.hussey.retailapiservice.util.feign.*;
import com.hussey.retailapiservice.viewmodel.OrderViewModelInput;
import com.hussey.retailapiservice.viewmodel.OrderViewModelOutput;
import com.hussey.retailapiservice.viewmodel.ProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class RetailService {
    // Properties
    private final ProductClient productClient;
    private final CustomerClient customerClient;
    private final InvoiceClient invoiceClient;
    private final LevelUpClient levelUpClient;
    private final InventoryClient inventoryClient;

    // Constructors
    @Autowired
    public RetailService(ProductClient productClient, CustomerClient customerClient, InvoiceClient invoiceClient, LevelUpClient levelUpClient, InventoryClient inventoryClient) {
        this.productClient = productClient;
        this.customerClient = customerClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.inventoryClient = inventoryClient;
    }

    // Methods
    public List<ProductViewModel> getAllProduct() {
        // Feign to product-service to retrieve all products
        List<Product> allProductList = productClient.findAll();

        // Create a new ProductViewModel and ProductViewModel List
        List<ProductViewModel> productViewModelList = new ArrayList<>();

        // Transform allProductList into a List of ProductViewModels
        allProductList.forEach(product -> {
            ProductViewModel pvm = new ProductViewModel();
            pvm.setProductName(product.getProductName());
            pvm.setProductDescription(product.getProductDescription());
            pvm.setListPrice(product.getListPrice());
            productViewModelList.add(pvm);
        });

        // Return the productViewModelList
        return productViewModelList;
    }

    public List<ProductViewModel> getProductByProductName(String productName) {
        // Feign to product-service to retrieve products by product name
        List<Product> productListByName = productClient.findProductByProductName(productName);

        // Create a new ProductViewModel and ProductViewModel List
        List<ProductViewModel> productViewModelListByName = new ArrayList<>();

        // Transform productListByName into a List of ProductViewModels
        productListByName.forEach(product -> {
            ProductViewModel pvm = new ProductViewModel();
            pvm.setProductName(product.getProductName());
            pvm.setProductDescription(product.getProductDescription());
            pvm.setListPrice(product.getListPrice());
            productViewModelListByName.add(pvm);
        });

        // Return the productViewModelList
        return productViewModelListByName;
    }

    public OrderViewModelOutput placeOrder(OrderViewModelInput orderViewModelInput) {
        // Validate customerId
        Customer customer = customerClient.findCustomer(orderViewModelInput.getCustomerId());
        if(customer == null) {
            throw new IllegalArgumentException("There is no customer with ID: " + orderViewModelInput.getCustomerId());
        }

        // Validate levelUpId using customerId
        LevelUp levelUp = levelUpClient.findLevelUpByCustomerId(orderViewModelInput.getCustomerId());
        if(levelUp == null) {
            throw new IllegalArgumentException("There is no Level Up ID associated with that customer. Please contact the system administrator.");
        }

        // Remove order items that do not have a valid productId
        orderViewModelInput.getOrderMap().entrySet().forEach(set -> {
            Product product = productClient.findProduct(set.getKey());
            if (product == null) {
                System.out.println("There is no product with Id: " + set.getKey() + ". It has been removed from your order.");
                orderViewModelInput.getOrderMap().remove(set.getKey());
            }
        });

        // Remove order items that do not have a valid inventoryId
        orderViewModelInput.getOrderMap().entrySet().forEach(set -> {
           List<Inventory> inventoryList = inventoryClient.findInventoryByProductId(set.getKey());
           Inventory inventory = inventoryList.get(0);
           if(inventory == null) {
               System.out.println("There is no inventoryId associated with the productId: " + set.getKey() + ". It has been removed from your order. Please contact system administrator to correct the error.");
               orderViewModelInput.getOrderMap().remove(set.getKey());
           }
        });

        // Remove order items that do not have a valid quantity in inventory
        orderViewModelInput.getOrderMap().entrySet().forEach(set -> {
           List<Inventory> inventoryList = inventoryClient.findInventoryByProductId(set.getKey());
           Inventory inventory = inventoryList.get(0);
           if(inventory.getQuantity() < set.getValue()) {
               System.out.println("There is not enough inventory in stock to complete the order for productId: " + set.getKey() + ". It has been removed from your order.");
               orderViewModelInput.getOrderMap().remove(set.getKey());
           }
        });

        // Build a OrderViewModelOutput and return
        if(orderViewModelInput.getOrderMap().size() > 0) {
            // Generate a new OrderViewModelOutput
            OrderViewModelOutput orderViewModelOutput = new OrderViewModelOutput();

            // Set the Customer Information
            orderViewModelOutput.setName(customer.getFirstName() + customer.getLastName());
            orderViewModelOutput.setCustomerId(customer.getCustomerId());

            // Generate a new Invoice
            Invoice invoice = new Invoice();
            invoice.setCustomerId(customer.getCustomerId());
            invoice.setPurchaseDate(LocalDate.now());
            Invoice invoiceFinal = invoiceClient.addInvoice(invoice);
            orderViewModelOutput.setInvoiceId(invoice.getInvoiceId());
            orderViewModelOutput.setPurchaseDate(invoice.getPurchaseDate());

            // Make a List for all the Invoice Items
            List<InvoiceItem> invoiceItemList = new ArrayList<>();
            BigDecimal total = new BigDecimal("0.00");

            // Generate a new Invoice Item for each valid order
            orderViewModelInput.getOrderMap().entrySet().forEach(order -> {
                InvoiceItem invoiceItem = new InvoiceItem();
                invoiceItem.setInvoiceId(invoiceFinal.getInvoiceId());
                invoiceItem.setInventoryId(inventoryClient.findInventoryByProductId(order.getKey()).get(0).getInventoryId());
                invoiceItem.setQuantity(order.getValue());
                invoiceItem.setUnitPrice(productClient.findProduct(order.getKey()).getListPrice());
                invoiceItem = invoiceClient.addInvoiceItem(invoiceItem);
                invoiceItemList.add(invoiceItem);
                total.add(invoiceItem.getUnitPrice().multiply(BigDecimal.valueOf(invoiceItem.getQuantity())));
            });

            // Set the InvoiceItemList & Total
            orderViewModelOutput.setInvoiceItemList(invoiceItemList);
            orderViewModelOutput.setTotal(total.setScale(2, BigDecimal.ROUND_HALF_EVEN));

            // Calculate the total number of level up points generated
            int levelUpPointsForOrder = Integer.parseInt(total.toString()) / 50 * 10;
            levelUp.setPoints(levelUp.getPoints() + levelUpPointsForOrder);
            levelUpClient.updateLevelUp(levelUp);
            orderViewModelOutput.setLevelUpOrderPoints(levelUpPointsForOrder);
            orderViewModelOutput.setLevelUpTotalPoints(levelUp.getPoints());

            // Return orderViewModelOutput
            return orderViewModelOutput;
        } else {
            throw new IllegalArgumentException("There are no more orders in your order input. Please try again with a valid order.");
        }
    }
}
