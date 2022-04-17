package miclaa.service;

import miclaa.domain.Usuario;
import miclaa.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Logica de negocios
 * /
 */
@Service
public class UsuarioService implements UsuarioRepository {

    public static List<Usuario> listAll(){ //aula 7
        return List.of(new Usuario("Joao", 1L, "SP", "Sorocaba", "admin", "admin@seila.com", "11122233301")); //fiz uma lista para mockar mais usuarios se necessario
    }

//    public Usuario save(Usuario usuario){ //aula 9
//        Usuario.setId(ThreadLocalRandom.current().nextLong(3, 10000000));
//
//    }
}
