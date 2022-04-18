package miclaa.service;

import miclaa.domain.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Logica de negocios
 * /
 */
@Service
public class UsuarioService {

    private static List<Usuario> usuarios;
            static {
                usuarios = new ArrayList<>(List.of(new Usuario("Joao", 1L, "SP", "Sorocaba", "admin", "admin@seila.com", "11122233301")));
            }

    public static List<Usuario> listAll(){ //aula 7
        return usuarios;
    }

    public static Usuario findById(Long id){ //aula 8
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario nao encontrado"));
    }

    public static Usuario save(Usuario usuario){ //aula 9
        usuario.setId(ThreadLocalRandom.current().nextLong(3, 10000000));
        usuarios.add(usuario);
        return usuario;

    }
}
