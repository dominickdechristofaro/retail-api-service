package com.hussey.retailapiservice.service;
import com.hussey.retailapiservice.model.Product;
import com.hussey.retailapiservice.util.feign.ProductClient;
import com.hussey.retailapiservice.viewmodel.ProductViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetailServiceTest {
    // Properties
    private RetailService retailService;
    private ProductClient productClient;

    private Product product1 = buildProduct(1, "name1", "description1", new BigDecimal("9.99"), new BigDecimal("2.99"));
    private Product product2 = buildProduct(2, "name2", "description2", new BigDecimal("14.99"), new BigDecimal("4.99"));
    private Product product3 = buildProduct(3, "name2", "description3", new BigDecimal("19.99"), new BigDecimal("6.99"));


    // SetUp()
    @BeforeEach
    public void setUp() throws Exception {
        setUpProductClientMock();
        retailService = new RetailService(productClient);
    }

    // Tests
    @Test
    public void test_getAllProduct_shouldReturnAListOfAllProductsInProductViewModel() {
        // Get a ProductViewModel List of all Products by calling the service layer
        List<ProductViewModel> pvmList = retailService.getAllProduct();

        // Test getAllProduct() method
        assertEquals(3, pvmList.size());
    }

    @Test
    public void test_getProductByProductName_shouldReturnAListOfProductsByName() {
        // Get a ProductViewModel List of all Products by name by calling thd service layer
        List<ProductViewModel> pvmListByName = retailService.getProductByProductName("name2");

        // Test getProductByProductName() method
        assertEquals(2, pvmListByName.size());
    }

    // Helper Methods
    private void setUpProductClientMock() {
        // Set up the productClient Mock
        productClient = Mockito.mock(ProductClient.class);

        // Mock products
        Product product1 = buildProduct(1, "name1", "description1", new BigDecimal("9.99"), new BigDecimal("2.99"));
        Product product2 = buildProduct(2, "name2", "description2", new BigDecimal("14.99"), new BigDecimal("4.99"));
        Product product3 = buildProduct(3, "name2", "description3", new BigDecimal("19.99"), new BigDecimal("6.99"));

        // Mock All Product List
        List<Product> allProductList = new ArrayList<>();
        allProductList.add(product1);
        allProductList.add(product2);
        allProductList.add(product3);

        // Mock Product List by Name
        List<Product> productListByProductName = new ArrayList<>();
        productListByProductName.add(product2);
        productListByProductName.add(product3);

        // Mock getAllProduct() method
        Mockito.doReturn(allProductList).when(productClient).findAll();

        // Mock getProductByProductName() method
        Mockito.doReturn(productListByProductName).when(productClient).findProductByProductName("name2");
    }

    private Product buildProduct(Integer productId, String productName, String productDescription, BigDecimal listPrice, BigDecimal unitCost) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setListPrice(listPrice);
        product.setUnitCost(unitCost);
        return product;
    }


}
