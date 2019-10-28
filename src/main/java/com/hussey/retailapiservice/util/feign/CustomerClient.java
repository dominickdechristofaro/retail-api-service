package com.hussey.retailapiservice.util.feign;
import com.hussey.retailapiservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "customer-service")
public interface CustomerClient {
    // Insert feigns here
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    Customer findCustomer(@PathVariable int id);
}
