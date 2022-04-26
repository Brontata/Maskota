package project.petme.springboot.tg.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long idPet;
    @Getter
    private String nome;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario")
    private Usuario usuario;
}
