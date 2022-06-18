package project.petme.springboot.tg.domain.responses;

import lombok.Data;

import java.util.List;

@Data
public class GetAllCurtidasPet {
    private Long idPet;
    private String nome;
    private int quantidadeCurtidas;
    private List<GetAllCurtidas> curtidas;
}
