package miclaa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {

    private String nome;
    private Long id;
    private String estado;
    private int curtidas;
    private String descricao;
    private String cidade;


}
