package project.petme.springboot.tg.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import project.petme.springboot.tg.data.DetalheUsuarioData;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.repository.UsuarioRepository;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final UsuarioRepository repository;

    public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByUsername(username);
        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario [" + username + "] nao encontrado");
        }

        return new DetalheUsuarioData(usuario);
    }
}
