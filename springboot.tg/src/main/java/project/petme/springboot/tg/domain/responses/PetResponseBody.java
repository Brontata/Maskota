package project.petme.springboot.tg.domain.responses;

import lombok.Data;

@Data
public class PetResponseBody {
    private Long idPet;
    private String nome;
    private String descricao;
    private String[] imagens;
    private UsuarioPetResponseBody usuario;
}