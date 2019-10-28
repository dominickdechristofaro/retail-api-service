package com.hussey.retailapiservice.util.feign;
import com.hussey.retailapiservice.model.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "level-up-service")
public interface LevelUpClient {
    // Insert Feign's here
    @GetMapping("/customer/{id}")
    LevelUp findLevelUpByCustomerId(@PathVariable int id);

    @PutMapping("/levelup")
    void updateLevelUp(@RequestBody LevelUp levelup);
}
