package com.hussey.retailapiservice.util.feign;
import com.hussey.retailapiservice.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    // Insert feign's here
    @RequestMapping(value = "/inventory/byProduct/{productId}", method = RequestMethod.GET)
    List<Inventory> findInventoryByProductId(@PathVariable int productId);
}
