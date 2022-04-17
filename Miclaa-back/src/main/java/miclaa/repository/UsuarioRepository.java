package miclaa.repository;

import miclaa.domain.Usuario;

import java.util.List;

/**
 * Por enquanto so uma interface...
 *
 * No futuro, as queries para conexao com a base
 *
 * /
 */
public interface UsuarioRepository {
    List<Usuario> listAll();
}
