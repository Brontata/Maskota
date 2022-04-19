package project.petme.springboot.tg.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.domain.responses.UsuarioGetResponseBody;
import project.petme.springboot.tg.repository.UsuarioRepository;
import project.petme.springboot.tg.domain.requests.UsuarioPostRequestBody;
import project.petme.springboot.tg.domain.requests.UsuarioPutRequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    ModelMapper mapper = new ModelMapper();

    public List<UsuarioGetResponseBody> listAll() {
        List<UsuarioGetResponseBody> usuariosResponse = new ArrayList<>();
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario: usuarios) {
            usuariosResponse.add(mapper.map(usuario, UsuarioGetResponseBody.class));
        }

        return usuariosResponse;
    }

    public Usuario findByIdOrThrowBadRequestException(long id) {
        return  usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario nao encontrado"));
    }

    public Usuario save(UsuarioPostRequestBody usuarioRequest) {
        Usuario usuario = mapper.map(usuarioRequest, Usuario.class);
        usuario.setSenha(encoder.encode(usuario.getSenha())); //CRIPTOGRAFANDO SENHA
        usuarioRepository.save(usuario);
        return usuario;
    }

    public void delete(long id) {
        usuarioRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(UsuarioPutRequestBody usuarioPutRequestBody) {
        findByIdOrThrowBadRequestException(usuarioPutRequestBody.getIdUsuario());

        Usuario usuario = mapper.map(usuarioPutRequestBody, Usuario.class);
        usuarioRepository.save(usuario);
    }
}
