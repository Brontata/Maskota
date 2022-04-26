package project.petme.springboot.tg.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.petme.springboot.tg.domain.Pet;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.domain.responses.PetResponseBody;
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
//    Logger logger = LoggerFactory.getLogger(LoggingController.class);
//    private final ModelMapper mapper = new ModelMapper();

    public List<Usuario> listAll() {
//        logger.info("### Listando todos usuários ###");
//        List<UsuarioGetResponseBody> usuariosResponse = new ArrayList<>();
//        List<Usuario> usuarios = usuarioRepository.findAll();
//        List<PetResponseBody> petsResponse = new ArrayList<>();
//
//
//        for (Usuario usuario: usuarios) {               //Converte o objeto usuario para UsuarioResponse
//            UsuarioGetResponseBody usuarioSalvo = mapper.map(usuario, UsuarioGetResponseBody.class);
//
//            for (Pet pet : usuario.getPets()){
//                PetResponseBody petSalvo = mapper.map(pet, PetResponseBody.class);
//                petsResponse.add(petSalvo);
//            }
//
//            usuariosResponse.add(usuarioSalvo);
//        }

        return usuarioRepository.findAll();
    }

    public Usuario findByIdOrThrowBadRequestException(long id) {
//        logger.info("### Procurando usuário por ID ###");
        return  usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario nao encontrado"));
    }

    public Usuario save(Usuario usuario) {
//        logger.info("### Salvando usuário ###");
//        Usuario usuario = mapper.map(usuarioRequest, Usuario.class);
        usuario.setSenha(encoder.encode(usuario.getSenha())); //CRIPTOGRAFANDO SENHA

        if(usuario.getPets() == null){
            List<Pet> listaPets = new ArrayList<>();
            usuario.setPets(listaPets);
        }
        usuarioRepository.save(usuario);
        return usuario;
    }

    public void delete(long id) {
        usuarioRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(Usuario usuario) {
        //Usuario usuario = mapper.map(usuarioPutRequestBody, Usuario.class);
        usuarioRepository.save(findByIdOrThrowBadRequestException(usuario.getIdUsuario()));
    }
}
