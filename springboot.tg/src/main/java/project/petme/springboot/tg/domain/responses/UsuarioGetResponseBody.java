package project.petme.springboot.tg.domain.responses;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioGetResponseBody {
    private Long idUsuario;
    private String estado;
    private String cidade;
    private String username;
    private String email;
    private List<GetAllUsuariosPetsResponseBody> pets;
    private String fotoPerfil;
    private boolean isAtivo;
}
