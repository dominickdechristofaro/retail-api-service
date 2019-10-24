package com.hussey.retailapiservice.controller;
import com.hussey.retailapiservice.service.RetailService;
import com.hussey.retailapiservice.viewmodel.ProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RetailController {
    // Properties
    @Autowired
    private RetailService retailService;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProductViewModel> getAllProduct() {
        return retailService.getAllProduct();
    }

    @RequestMapping(value = "/product/byName/{productName}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProductViewModel> getProductByProductName(@PathVariable String productName) {
        return retailService.getProductByProductName(productName);
    }
}
