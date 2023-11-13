package com.example.springcontacts;

import com.example.springcontacts.printer.ProfileWorker;
import com.example.springcontacts.runner.ProfileRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class SpringContactsApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SpringContactsApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        ApplicationContext ctx = SpringApplication.run(SpringContactsApplication.class, args);
        ctx.getBean("profileRunner", ProfileRunner.class).doRun();
        ctx.getBean("profileWorker", ProfileWorker.class).doWork();
        LOG.info("APPLICATION FINISHED");
    }
}