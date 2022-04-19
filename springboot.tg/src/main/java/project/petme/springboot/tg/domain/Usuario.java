package project.petme.springboot.tg.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String username;
    private String nome;
    private String email;
    private String regiao; //regiao pertence ao pet ALTERAR
    private String senha;
    private String telefone;
    //private Imagem fotoPerfil;
    private ArrayList<Pet> pets;
}
