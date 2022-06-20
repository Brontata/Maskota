package project.petme.springboot.tg.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.petme.springboot.tg.domain.Pet;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.domain.requests.UsuarioRecoverySenha;
import project.petme.springboot.tg.domain.requests.UsuarioTrocaSenhaRequestBody;
import project.petme.springboot.tg.domain.responses.GetAllUsuariosPetsResponseBody;
import project.petme.springboot.tg.domain.responses.GetAllUsuariosResponseBody;
import project.petme.springboot.tg.domain.responses.UsuarioGetResponseBody;
import project.petme.springboot.tg.repository.UsuarioRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    protected EntityManager em;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper = new ModelMapper();
//    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    public List<GetAllUsuariosResponseBody> listAll(boolean isAtivo) {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if(isAtivo){
            usuarios = usuarios.stream()
                    .filter(p -> p.isAtivo()).collect(Collectors.toList());
        }

        if(!isAtivo){
            usuarios = usuarios.stream()
                    .filter(p -> !p.isAtivo()).collect(Collectors.toList());
        }

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
        usuario.setSenha(encoder.encode(usuario.getSenha())); //CRIPTOGRAFANDO SENHA

        if(usuario.getPets() == null){
            List<Pet> listaPets = new ArrayList<>();
            usuario.setPets(listaPets);
        }

        Optional<Usuario> validaExistenciaUsername = usuarioRepository.findByUsername(usuario.getUsername());
        if(!validaExistenciaUsername.isEmpty()){
            if (validaExistenciaUsername.get().isAtivo()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome de usuario ja esta sendo utilizado");
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome de usuario ja esta sendo utilizado e encontra-se inativo, caso pertenca a voce, recupere a senha para reativa-lo");
            }
        }

        Optional<Usuario> validaExistenciaEmail = usuarioRepository.findByEmail(usuario.getEmail());
        if(!validaExistenciaEmail.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O e-mail informado ja esta sendo utilizado");
        }

        Optional<Usuario> validaExistenciaCpf = usuarioRepository.findByCPF(usuario.getCPF());
        if(!validaExistenciaCpf.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CPF informado ja esta sendo utilizado");
        }

        usuario.setAtivo(true);

        usuarioRepository.save(usuario);
        return usuario;
    }

    public void delete(long id) {
        Usuario usuario = findByIdOrThrowBadRequestException(id);
        usuario.setAtivo(false);
        for (Pet pet: usuario.getPets()) {
            pet.setAtivo(false);
        }

        usuarioRepository.save(usuario);
    }

    public void replace(Usuario usuario, long id) {
        Usuario usuarioSalvo = findByIdOrThrowBadRequestException(id);

        if (!usuario.getEmail().trim().isEmpty() ){ //SE O EMAIL FOI PASSADO NA CHAMADA
            Optional<Usuario> usuarioEmail = usuarioRepository.findByEmail(usuario.getEmail());//VERIFICANDO SE O EMAIL JA ESTA SENDO UTILIZADO
            if (usuarioEmail.isPresent()){ //SE O EMAIL JA ESTA VINCULADO A UMA CONTA
                if (usuarioEmail.get().getUsername().equals(usuarioSalvo.getUsername())){ //SE A CONTA QUE ESTA VINCULADA E A MESMA QUE ESTA SENDO ALTERADA
                    usuarioSalvo.setEmail(usuario.getEmail());
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O e-mail informado já está sendo utilizado");
                }
            } else {
                usuarioSalvo.setEmail(usuario.getEmail());
            }
        }

        if (!usuario.getEstado().trim().isEmpty()){
            usuarioSalvo.setEstado(usuario.getEstado());
        }

        if (!usuario.getCidade().trim().isEmpty()){
            usuarioSalvo.setCidade(usuario.getCidade());
        }


        usuarioRepository.save(usuarioSalvo);
        //usuarioRepository.save(findByIdOrThrowBadRequestException(usuario.getIdUsuario()));
    }

    public void replaceFoto(Usuario usuario, long id){
        Usuario usuarioSalvo = findByIdOrThrowBadRequestException(id);

        if (usuario.getFotoPerfil().trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deve-se passar a imagem desejada");
        }

        usuarioSalvo.setFotoPerfil(usuario.getFotoPerfil());

        usuarioRepository.save(usuarioSalvo);
    }

    public void replaceSenha(UsuarioTrocaSenhaRequestBody usuario, long id) {
        Usuario usuarioSalvo = findByIdOrThrowBadRequestException(id);

        if (encoder.matches(usuario.getSenha(), usuarioSalvo.getSenha())){
            usuarioSalvo.setSenha(encoder.encode(usuario.getNovaSenha()));
            usuarioRepository.save(usuarioSalvo);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A senha informada esta incorreta");
        }

    }

    public void recoveryPassword(UsuarioRecoverySenha usuarioRecovery) {
        Usuario usuario = usuarioRepository.findByUsername(usuarioRecovery.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario " + usuarioRecovery.getUsername() + " nao encontrado"));

        if (usuarioRecovery.getEmail().equals(usuario.getEmail()) &&
                usuarioRecovery.getCPF().equals(usuario.getCPF()) &&
                usuarioRecovery.getUsername().equals(usuario.getUsername())) {
            usuario.setAtivo(true);
            usuario.setSenha(encoder.encode(usuarioRecovery.getNovaSenha()));
            usuarioRepository.save(usuario);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os dados fornecidos estão incorretos ");
        }
    }
}
