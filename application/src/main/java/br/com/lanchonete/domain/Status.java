package br.com.lanchonete.domain;

public enum Status {
    AGUARDANDO_PAGAMENTO,
    RECEBIDO,
    EM_PREPARACAO,
    PRONTO,
    FINALIZADO,
    CANCELADO;

    public static Status getStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo");
        }
        return Status.valueOf(status.toUpperCase());
    }
}
