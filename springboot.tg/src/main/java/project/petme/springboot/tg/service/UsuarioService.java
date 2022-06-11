package project.petme.springboot.tg.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.petme.springboot.tg.domain.Pet;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.domain.responses.GetAllUsuariosPetsResponseBody;
import project.petme.springboot.tg.domain.responses.GetAllUsuariosResponseBody;
import project.petme.springboot.tg.domain.responses.UsuarioGetResponseBody;
import project.petme.springboot.tg.repository.UsuarioRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    protected EntityManager em;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper = new ModelMapper();
//    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    public List<GetAllUsuariosResponseBody> listAll() {
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
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<GetAllUsuariosResponseBody> usuariosResponse = new ArrayList<>();

        for (Usuario usuario: usuarios) {
            List<GetAllUsuariosPetsResponseBody> petsUsuarioResponse = new ArrayList<>();
            GetAllUsuariosResponseBody usuarioResponse = mapper.map(usuario, GetAllUsuariosResponseBody.class);
            for (Pet pet: usuario.getPets()){
                GetAllUsuariosPetsResponseBody petResponse = mapper.map(pet, GetAllUsuariosPetsResponseBody.class);
                petsUsuarioResponse.add(petResponse);
            }
            usuarioResponse.setPets(petsUsuarioResponse);

            usuariosResponse.add(usuarioResponse);
        }

        return usuariosResponse;
    }

    public Usuario findByIdOrThrowBadRequestException(long id) {
//        logger.info("### Procurando usuário por ID ###");
        return  usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario nao encontrado"));



    }

    public UsuarioGetResponseBody findByUsernameOrThrowBadRequestException(String username){
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario " + username + " nao encontrado"));

        List<GetAllUsuariosPetsResponseBody> petsUsuarioResponse = new ArrayList<>();
        UsuarioGetResponseBody usuarioBuscado = mapper.map(usuario, UsuarioGetResponseBody.class);
        for (Pet pet: usuario.getPets()){
            GetAllUsuariosPetsResponseBody petResponse = mapper.map(pet, GetAllUsuariosPetsResponseBody.class);
            petsUsuarioResponse.add(petResponse);
        }
        usuarioBuscado.setPets(petsUsuarioResponse);

        return usuarioBuscado;
    }

    public Usuario save(Usuario usuario) {
//        logger.info("### Salvando usuário ###");
//        Usuario usuario = mapper.map(usuarioRequest, Usuario.class);
        usuario.setSenha(encoder.encode(usuario.getSenha())); //CRIPTOGRAFANDO SENHA
        //usuario.setIdUsuario(null);

        if(usuario.getPets() == null){
            List<Pet> listaPets = new ArrayList<>();
            usuario.setPets(listaPets);
        }

        Optional<Usuario> validaExistencia = usuarioRepository.findByUsername(usuario.getUsername());
        if(!validaExistencia.isEmpty()){
            throw new RuntimeException("O nome de usuario ja esta sendo utilizado");
        }

        usuarioRepository.save(usuario);
        return usuario;
    }

    public void delete(long id) {
        usuarioRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(Usuario usuario) {
        Usuario usuarioSalvo = findByIdOrThrowBadRequestException(usuario.getIdUsuario());

        if (usuario.getCPF() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CPF não pode ser alterado");
        }

        usuarioRepository.save(usuarioSalvo);
        //usuarioRepository.save(findByIdOrThrowBadRequestException(usuario.getIdUsuario()));
    }
}
