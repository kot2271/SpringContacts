package com.example.springcontacts.printer;

import org.springframework.stereotype.Component;

@Component
public class ProfileWorker {

    private final EnvPrinter envPrinter;

    public ProfileWorker(EnvPrinter env) {
        this.envPrinter = env;
    }

    public void doWork() {
        envPrinter.printEnv();
    }
}
