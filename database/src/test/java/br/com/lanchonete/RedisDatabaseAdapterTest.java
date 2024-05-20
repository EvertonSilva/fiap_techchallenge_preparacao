package br.com.lanchonete;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.lanchonete.config.RedisConfig;
import br.com.lanchonete.domain.Pedido;
import redis.clients.jedis.Jedis;

public class RedisDatabaseAdapterTest {
    Jedis db;
    Pedido testData;
    RedisDatabaseAdapter subject;

    @BeforeEach
    void setUp() {
        subject = new RedisDatabaseAdapter();
        testData = new Pedido(UUID.randomUUID().toString());
        db = RedisConfig.getJedis();
    }

    @AfterEach
    void tearDown() {
        db.flushDB();
    }

    @Test
    void testSalvaStatusDoPedido() {
        subject.save(testData);
        assertThat(db.dbSize()).isEqualTo(1);
    }
}
