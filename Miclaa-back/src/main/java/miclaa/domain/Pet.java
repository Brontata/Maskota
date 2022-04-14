package miclaa.domain;

public class Pet {

    private String nome;
    private int id;
    private String estado;
    private int curtidas;
    private String descricao;
    private String cidade;

    public Pet(String nome, int id, String estado, int curtidas, String descricao, String cidade) {
        this.nome = nome;
        this.id = id;
        this.estado = estado;
        this.curtidas = curtidas;
        this.descricao = descricao;
        this.cidade = cidade;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return int return the curtidas
     */
    public int getCurtidas() {
        return curtidas;
    }

    /**
     * @param curtidas the curtidas to set
     */
    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    /**
     * @return String return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return String return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}
