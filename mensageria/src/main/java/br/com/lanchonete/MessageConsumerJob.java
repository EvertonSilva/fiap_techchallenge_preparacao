package br.com.lanchonete;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.BackgroundJob;
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

    public void startJobs() {
        jobScheduler.<Job>scheduleRecurrently("*/1 * * * *", () -> messageBrokerWorker.getMessageNovoPedido());
        jobScheduler.<Job>scheduleRecurrently("*/1 * * * *", () -> messageBrokerWorker.getMessagePagamentoEfetuado());
    }
}
