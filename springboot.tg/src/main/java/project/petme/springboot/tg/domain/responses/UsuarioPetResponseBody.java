package project.petme.springboot.tg.domain.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class UsuarioPetResponseBody {
    private Long idUsuario;
    private String username;
    private String estado;
    private String cidade;
    private String email;
    private String fotoPerfil;
    private boolean isAtivo;
}
