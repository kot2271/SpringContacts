package com.example.springcontacts.printer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class InitEnvPrinter implements EnvPrinter {

    @Value("${spring.profiles.active}")
    private String env;

    @Override
    public void printEnv() {
        System.out.println("Calling InitEnvPrinter printEnv");
        System.out.println("InitEnvPrinter env is: " + env);
    }
}
