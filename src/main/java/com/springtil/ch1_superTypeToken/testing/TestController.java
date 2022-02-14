package com.springtil.ch1_superTypeToken.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/")
    public List<User> users() {
        return Arrays.asList(new User("A"), new User("B"), new User("C"));
    }
}
