package br.com.lanchonete.config;

import org.jobrunr.server.JobActivator;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;

@ApplicationScoped
public class MessageBrokerJobActivator implements JobActivator  {

    @Override
    public <T> T activateJob(Class<T> type) {
       return CDI.current().select(type).get(); 
    }

}
