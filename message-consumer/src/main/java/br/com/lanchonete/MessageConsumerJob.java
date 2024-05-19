package br.com.lanchonete;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import org.jobrunr.jobs.Job;
import org.jobrunr.scheduling.JobScheduler;

@ApplicationScoped
public class MessageConsumerJob {
    private MessageBrokerWorker messageBrokerWorker;
    private JobScheduler jobScheduler;

    @Inject
    public MessageConsumerJob(MessageBrokerWorker messageBrokerWorker, JobScheduler jobScheduler) {
        this.messageBrokerWorker = messageBrokerWorker;
        this.jobScheduler = jobScheduler;
    }

    void onStart(@Observes StartupEvent ev) {
        jobScheduler.<Job>scheduleRecurrently("*/1 * * * *", () -> messageBrokerWorker.getMessageNovoPedido());
        jobScheduler.<Job>scheduleRecurrently("*/1 * * * *", () -> messageBrokerWorker.getMessagePagamentoEfetuado());
    }
}
