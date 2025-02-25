




server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false


package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}


---------------------------------------------------------------------------------



server.port=8081
spring.application.name=service-one
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/



package com.example.serviceone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOneApplication.class, args);
    }
}




package com.example.serviceone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceOneController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Service One!";
    }
}


---------------------------------------------------------------------------------

server.port=8082
spring.application.name=service-two
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/




package com.example.servicetwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceTwoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceTwoApplication.class, args);
    }
}



package com.example.servicetwo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ServiceTwoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/greet")
    public String greet() {
        String serviceOneResponse = restTemplate.getForObject("http://service-one/hello", String.class);
        return "Service Two greeting: " + serviceOneResponse;
    }
}




