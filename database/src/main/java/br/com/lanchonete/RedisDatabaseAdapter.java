package br.com.lanchonete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.lanchonete.config.RedisConfig;
import br.com.lanchonete.domain.Pedido;
import br.com.lanchonete.infraestructure.IDatabaseAdapter;
import redis.clients.jedis.Jedis;

public class RedisDatabaseAdapter implements IDatabaseAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDatabaseAdapter.class);
    private final Jedis db;

    public RedisDatabaseAdapter() {
        db = RedisConfig.getJedis();
    }

    @Override
    public void save(Pedido pedido) {
        try {
            String key = pedido.getCodigo();
            String value = pedido.getStatus().toString();
            db.set(key, value);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @Override
    public Pedido findByCodigo(String codigo) {
        var pedido = new Pedido();
        try {
            String status = db.get(codigo);
            if (status == null) {
                return null;
            }
            pedido.setCodigo(codigo);
            pedido.setStatus(status);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return pedido;
    }

}
