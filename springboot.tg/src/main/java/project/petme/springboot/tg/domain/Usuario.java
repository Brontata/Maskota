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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    private String username;
    private String email;
    private String senha;
    private String CPF;

    @OneToMany
    private List<Pet> pets;
//    @OneToOne
//    private Imagem fotoPerfil;
}
