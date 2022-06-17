package project.petme.springboot.tg.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPet;
    private String numero;
    private String nome;
    private String estado;
    private String cidade;
//    private Curtidas curtidas;
    private String descricao;
    @Lob
    private String fotoPet;
    @Column(columnDefinition="BOOLEAN DEFAULT true")
    private boolean isAtivo;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario")
    private Usuario usuario;
}
