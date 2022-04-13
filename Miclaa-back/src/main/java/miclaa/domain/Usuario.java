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

//------------------MetodosNossos------------------------------



    //------------------gettersSetters------------------------------


    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
}
