package miclaa.domain;

public class Usuario {

    private String nome;
    private int id;
    private String estado;
    private String cidade;
    private String senha;
    private String email;
    private String cpf;

    public Usuario(String nome, int id, String estado, String cidade, String senha, String email, String cpf) {
        this.nome = nome;
        this.id = id;
        this.estado = estado;
        this.cidade = cidade;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
    }

    // ------------------MetodosNossos------------------------------
    // nada ainda
    // ------------------gettersSetters------------------------------


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
