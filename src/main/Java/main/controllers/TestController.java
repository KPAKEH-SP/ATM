package main.controllers;

import main.repository.CustomCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    public TestController(CustomCrudRepository testRepository) {
        this.testRepository = testRepository;
    }

    private final CustomCrudRepository testRepository;

    @GetMapping("/getUser")
    public String getUserById(@RequestParam("id") int id) {
        System.out.println(id);
        return testRepository.findById(id).get().toString();
    }
}
