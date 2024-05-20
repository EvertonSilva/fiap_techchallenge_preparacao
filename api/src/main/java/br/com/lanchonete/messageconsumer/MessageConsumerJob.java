package br.com.lanchonete.messageconsumer;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class MessageConsumerJob {
    private final MessageBrokerWorker messageBrokerWorker;
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumerJob.class);

    @Inject
    public MessageConsumerJob(MessageBrokerWorker messageBrokerWorker) {
        this.messageBrokerWorker = messageBrokerWorker;
        JobRunr.configure()
            .useStorageProvider(new InMemoryStorageProvider())
            .useBackgroundJobServer()
            .useDashboard()
            .initialize();
    }

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("Starting MessageConsumerJob");
        BackgroundJob.enqueue(() -> messageBrokerWorker.getMessageNovoPedido());
    }
}
