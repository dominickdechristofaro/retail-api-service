package com.hussey.retailapiservice.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hussey.retailapiservice.service.RetailService;
import com.hussey.retailapiservice.viewmodel.ProductViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RetailController.class)
public class RetailControllerTest {
    // Properties
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RetailService retailService;
    private ObjectMapper objectMapper = new ObjectMapper();

    private ProductViewModel pvm1 = buildProductViewModel( "name1", "description1", new BigDecimal("9.99"));
    private ProductViewModel pvm2 = buildProductViewModel( "name2", "description2", new BigDecimal("14.99"));
    private ProductViewModel pvm3 = buildProductViewModel( "name2", "description3", new BigDecimal("19.99"));

    // Tests
    @Test
    public void test_getAllProduct_shouldReturnAListOfAllProductViewModels() throws Exception {
        // Create the all ProductList
        List<ProductViewModel> allProductList = new ArrayList<>();
        allProductList.add(pvm1);
        allProductList.add(pvm2);
        allProductList.add(pvm3);

        // Create the response JSON
        String responseJson = objectMapper.writeValueAsString(allProductList);

        // Mock the service layer behavior
        Mockito.when(retailService.getAllProduct()).thenReturn(allProductList);

        // MVC Mock
        this.mockMvc.perform(get("/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    public void test_getProductByProductName_shouldReturnAListOfAllProductViewModelsByProductName() throws Exception {
        // Create the productListByProductName
        List<ProductViewModel> productNameList = new ArrayList<>();
        productNameList.add(pvm2);
        productNameList.add(pvm3);

        // Create the response JSON
        String responseJson = objectMapper.writeValueAsString(productNameList);

        // Mock the service layer behavior
        Mockito.when(retailService.getProductByProductName("name2")).thenReturn(productNameList);

        // MVC Mock
        this.mockMvc.perform(get("/product/byName/name2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    // Helper Methods
    private ProductViewModel buildProductViewModel(String productName, String productDescription, BigDecimal listPrice) {
        ProductViewModel pvm = new ProductViewModel();
        pvm.setProductName(productName);
        pvm.setProductDescription(productDescription);
        pvm.setListPrice(listPrice);
        return pvm;
    }
}
