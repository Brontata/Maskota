package project.petme.springboot.tg.domain.requests;

import lombok.Data;
import project.petme.springboot.tg.domain.Pet;

@Data
public class UsuarioPostRequestBody {
    private String username;
    private String nome;
    private String email;
    private String regiao;
    private String senha;
    private String telefone;
    private Pet pets;
}
