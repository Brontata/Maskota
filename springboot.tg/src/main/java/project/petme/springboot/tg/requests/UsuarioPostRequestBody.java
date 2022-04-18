package project.petme.springboot.tg.requests;

import lombok.Data;

@Data
public class UsuarioPostRequestBody {
    private String nome;
    private String email;
    private String regiao;
    private String senha;
    private String telefone;
}
