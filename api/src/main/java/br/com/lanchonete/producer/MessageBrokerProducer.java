package br.com.lanchonete.producer;

import java.io.IOException;
import br.com.lanchonete.rabbitmq.RabbitMqConsumer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;

@ApplicationScoped
public class MessageBrokerProducer {

    @Produces
    public RabbitMqConsumer rabbitMqConsumer() throws IOException {
        return new RabbitMqConsumer();
    }

    @Produces
    public JobScheduler jobScheduler() {
        var storage = new InMemoryStorageProvider();
        return new JobScheduler(storage);
    }
}
