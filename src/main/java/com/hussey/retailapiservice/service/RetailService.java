package com.hussey.retailapiservice.service;
import com.hussey.retailapiservice.model.Product;
import com.hussey.retailapiservice.util.feign.ProductClient;
import com.hussey.retailapiservice.viewmodel.ProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class RetailService {
    // Properties
    @Autowired
    private final ProductClient productClient;

    // Constructors
    public RetailService(ProductClient productClient) {
        this.productClient = productClient;
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
}
