package com.hussey.retailapiservice.util.feign;
import com.hussey.retailapiservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    List<Product> findAll();

    @RequestMapping(value = "/product/byName/{productName}", method = RequestMethod.GET)
    List<Product> findProductByProductName(@PathVariable String productname);

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    Product findProduct(@PathVariable int id);
}
