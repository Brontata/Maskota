package miclaa.domain;

public class Usuario {


    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome;
}
