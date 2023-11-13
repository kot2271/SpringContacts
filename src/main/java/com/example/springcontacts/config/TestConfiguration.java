package com.example.springcontacts.config;

import com.example.springcontacts.printer.EnvPrinter;
import com.example.springcontacts.printer.TestEnvPrinter;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application-test.properties")
@Profile("test")
public class TestConfiguration {

    @Bean
    public EnvPrinter envPrinter() {
        return new TestEnvPrinter();
    }
}
