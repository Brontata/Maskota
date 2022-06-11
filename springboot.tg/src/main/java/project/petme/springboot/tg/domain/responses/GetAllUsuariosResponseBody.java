package project.petme.springboot.tg.domain.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import project.petme.springboot.tg.domain.Pet;

import javax.persistence.*;
import java.util.List;

@Data
public class GetAllUsuariosResponseBody {
    private Long idUsuario;
    private String username;
    private String email;
    private List<GetAllUsuariosPetsResponseBody> pets;
    private String fotoPerfil;
}
