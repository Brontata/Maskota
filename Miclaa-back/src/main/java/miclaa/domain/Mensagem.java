package miclaa.domain;

import java.util.Date;

public class Mensagem {

    private double id;
    private Usuario destinatario;
    private Usuario remetente;
    private Date data;

    public Mensagem(double id, Usuario destinatario, Usuario remetente, Date data) {
        this.id = id;
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.data = data;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
