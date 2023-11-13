package com.example.springcontacts.config;

import com.example.springcontacts.printer.EnvPrinter;
import com.example.springcontacts.printer.InitEnvPrinter;
import com.example.springcontacts.runner.EnvRunner;
import com.example.springcontacts.runner.InitRunner;
import com.example.springcontacts.service.CommandService;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application.properties")
@Profile("init")
public class InitConfiguration {

    private final CommandService commandService;

    public InitConfiguration(CommandService commandService) {
        this.commandService = commandService;
    }

    @Bean
    public EnvRunner envRunner() {
        return new InitRunner(commandService);
    }

    @Bean
    public EnvPrinter envPrinter() {
        return new InitEnvPrinter();
    }
}
