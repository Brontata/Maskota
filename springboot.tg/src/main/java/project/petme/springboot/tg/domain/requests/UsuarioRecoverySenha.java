package project.petme.springboot.tg.domain.requests;

import lombok.Data;

@Data
public class UsuarioRecoverySenha {
    private String username;
    private String email;
    private String CPF;
    private String novaSenha;
}
