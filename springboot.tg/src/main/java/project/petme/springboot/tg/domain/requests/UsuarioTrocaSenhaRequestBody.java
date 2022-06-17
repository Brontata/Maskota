package project.petme.springboot.tg.domain.requests;

import lombok.Data;

@Data
public class UsuarioTrocaSenhaRequestBody {
    private String senha;
    private String novaSenha;
}
