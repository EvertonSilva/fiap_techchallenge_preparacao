package br.com.lanchonete.domain;

public class Pedido {
    private String codigo;
    private Status status;

    public Pedido() {
        status = Status.AGUARDANDO_PAGAMENTO;
    }

    public Pedido(String codigo) {
        this();
        setCodigo(codigo);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public void avancaStatus() {
        if (status == Status.AGUARDANDO_PAGAMENTO) {
            status = Status.RECEBIDO;
            return;
        }

        if (status == Status.RECEBIDO) {
            status = Status.EM_PREPARACAO;
            return;
        }

        if (status == Status.EM_PREPARACAO) {
            status = Status.PRONTO;
            return;
        }

        if (status == Status.PRONTO) {
            status = Status.FINALIZADO;
        }
    }
}
