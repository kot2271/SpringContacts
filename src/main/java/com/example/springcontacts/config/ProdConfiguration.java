package com.example.springcontacts.config;

import com.example.springcontacts.printer.EnvPrinter;
import com.example.springcontacts.printer.ProdEnvPrinter;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application-prod.properties")
@Profile("prod")
public class ProdConfiguration {

    @Bean
    public EnvPrinter envPrinter() {
        return new ProdEnvPrinter();
    }
}
