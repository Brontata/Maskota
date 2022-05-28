package project.petme.springboot.tg.domain.responses;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioGetResponseBody {
    private Long idUsuario;
    private String username;
    private String nome;
    private String email;
    private String regiao;
    private String telefone;
    private List<PetResponseBody> pets;
//    private Imagem fotoPerfil;
}
