package project.petme.springboot.tg.domain.responses;

import lombok.Data;

import java.util.List;

@Data
public class PetResponseBody {
    private Long idPet;
    private String numero;
    private String nome;
    private String estado;
    private String cidade;
    private String descricao;
    private String fotoPet;
    private boolean isAtivo;
    private int quantidadeCurtidas;
    private List<GetAllCurtidas> curtidas;
    private UsuarioPetResponseBody usuario;
}
