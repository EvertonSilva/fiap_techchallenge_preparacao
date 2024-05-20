package br.com.lanchonete.messageconsumer;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.lanchonete.config.MessageBrokerJobActivator;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class JobConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobConfig.class);
    @Inject
    MessageBrokerJobActivator jobActivator;
    @Inject
    private Worker worker;

    void onStart(@Observes StartupEvent ev) {
        try {
            LOGGER.info("Initializing and config JobRunr");
            
            JobRunr.destroy();
            JobRunr.configure()
                .useStorageProvider(new InMemoryStorageProvider())
                .useJobActivator(jobActivator)
                .useBackgroundJobServer()
                .useDashboard()
                .initialize();
            
            LOGGER.info("Scheduling jobs");
            BackgroundJob.enqueue(worker::getMessageNovoPedido);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
