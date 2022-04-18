package project.petme.springboot.tg.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nome;
    private String email;
    private String regiao;
    private String senha;
    private String telefone;
    //private Imagem fotoPerfil;
    //@ManyToOne
    //private List<Pet> pets;
}
