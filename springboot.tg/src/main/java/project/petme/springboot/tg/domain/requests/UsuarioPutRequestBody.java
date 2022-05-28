package project.petme.springboot.tg.domain.requests;

import lombok.Data;
import project.petme.springboot.tg.domain.Pet;

import java.util.List;

@Data
public class UsuarioPutRequestBody {
    private Long idUsuario;
    private String username;
    private String nome;
    private String email;
    private String regiao;
    private String senha;
    private String telefone;
    private List<Pet> pets;
//    private Imagem fotoPerfil;
}
