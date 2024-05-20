package br.com.lanchonete.messageconsumer;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class MessageConsumerJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumerJob.class);
    private final MessageBrokerWorker worker;

    // @Inject
    public MessageConsumerJob(MessageBrokerWorker worker) {
        this.worker = worker;
    }

    void onStart(@Observes StartupEvent ev) {
        try {
            LOGGER.info("Initializing JobRunr and scheduling jobs");

            JobRunr.configure()
                .useStorageProvider(new InMemoryStorageProvider())
                .useBackgroundJobServer()
                .useDashboard()
                .initialize();
            
            LOGGER.info("JobRunr initialized successfully");
            
            BackgroundJob.enqueue(() -> worker.getMessageNovoPedido());

            LOGGER.info("Jobs scheduled successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
