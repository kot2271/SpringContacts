package com.example.springcontacts.runner;

import com.example.springcontacts.service.CommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Profile("init")
public class InitRunner implements EnvRunner {
    private static final Logger LOG = LoggerFactory.getLogger(InitRunner.class);
    public static final String FILE_NAME = "src/main/resources/default-contacts.txt";

    private final CommandService commandService;

    public InitRunner(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void runEnv() {
        LOG.info("EXECUTING : command line runner");
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.println("Введите команду");
            command = scanner.nextLine();

            commandService.handleCommand(command, FILE_NAME);
        } while (!command.equals("exit"));

    }
}
