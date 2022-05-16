package project.petme.springboot.tg.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Column(unique = true)
    private String username;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private String CPF;

    @OneToMany
    private List<Pet> pets;
//    @OneToOne
//    private Imagem fotoPerfil;
}
