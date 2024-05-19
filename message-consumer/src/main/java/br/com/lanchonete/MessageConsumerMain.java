package br.com.lanchonete;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class MessageConsumerMain {
    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
