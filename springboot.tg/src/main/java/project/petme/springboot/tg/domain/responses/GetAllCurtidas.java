package project.petme.springboot.tg.domain.responses;

import lombok.Data;
import project.petme.springboot.tg.domain.Usuario;

import java.util.List;

@Data
public class GetAllCurtidas {
    private Long idCurtida;
    private GetAllCurtidasUsuario usuario;
    private boolean isCurtido;
}
