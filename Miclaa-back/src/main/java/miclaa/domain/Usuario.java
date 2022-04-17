package miclaa.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Usuario {

    private String nome;
    private Long id;
    private String estado;
    private String cidade;
    private String senha;
    private String email;
    private String cpf;

}