package project.petme.springboot.tg.domain.responses;

import lombok.Data;

@Data
public class GetAllUsuariosPetsResponseBody {
    private Long idPet;
    private String nome;
    private String descricao;
    private String[] imagens;
}
