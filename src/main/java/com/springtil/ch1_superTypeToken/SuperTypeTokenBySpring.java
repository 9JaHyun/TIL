package com.springtil.ch1_superTypeToken;

import com.springtil.ch1_superTypeToken.testing.User;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class SuperTypeTokenBySpring {

    public static void main(String[] args) {
        RestTemplate rt = new RestTemplate();
//        List<Map> users = rt.getForObject("http://localhost:8080", List.class);
        List<User> users = rt.exchange("http://localhost:8080", HttpMethod.GET, null,
            new ParameterizedTypeReference<List<User>>() {}).getBody();
        users.forEach(System.out::println);
    }
}
