package br.com.lanchonete;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Main {
    
    @Inject
    private static MessageConsumerJob messageConsumerJob;

    public static void main(String[] args) {
        messageConsumerJob.startJobs();
    }
}
