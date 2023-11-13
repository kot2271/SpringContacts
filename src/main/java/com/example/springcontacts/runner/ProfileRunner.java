package com.example.springcontacts.runner;

import org.springframework.stereotype.Component;

@Component
public class ProfileRunner {

    private final EnvRunner envRunner;

    public ProfileRunner(EnvRunner envRunner) {
        this.envRunner = envRunner;
    }

    public void doRun() {
        envRunner.runEnv();
    }
}
