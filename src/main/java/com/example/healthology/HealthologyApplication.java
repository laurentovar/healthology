package com.example.healthology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
public class HealthologyApplication {


    public static void main(String[] args) {
        SpringApplication.run(HealthologyApplication.class, args);


        System.out.println("      _.-'''''-._\n" +
                "    .'  _     _  '.\n" +
                "   /   (o)   (o)   \\\n" +
                "  |                 |\n" +
                "  |  \\           /  |\n" +
                "   \\  '.       .'  /\n" +
                "    '.  `'---'`  .'\n" +
                "      '-._____.-");
    }

}
