package project.petme.springboot.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.domain.responses.UsuarioGetResponseBody;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
