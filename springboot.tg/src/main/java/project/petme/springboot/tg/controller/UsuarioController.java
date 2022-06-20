package project.petme.springboot.tg.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.petme.springboot.tg.domain.Pet;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.domain.requests.UsuarioRecoverySenha;
import project.petme.springboot.tg.domain.requests.UsuarioTrocaSenhaRequestBody;
import project.petme.springboot.tg.domain.responses.GetAllUsuariosResponseBody;
import project.petme.springboot.tg.domain.responses.PetResponseBody;
import project.petme.springboot.tg.domain.responses.UsuarioGetResponseBody;
import project.petme.springboot.tg.domain.responses.UsuarioPetResponseBody;
import project.petme.springboot.tg.service.CurtidaUsuarioPetService;
import project.petme.springboot.tg.service.PetService;
import project.petme.springboot.tg.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("usuarios")
//@Log4j2
@RequiredArgsConstructor
public class UsuarioController {

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final PetService petService;

    @Autowired
    private final CurtidaUsuarioPetService curtidaUsuarioPetService;

    private final PasswordEncoder encoder;

    @GetMapping
    public ResponseEntity<List<GetAllUsuariosResponseBody>> list(@RequestParam(name = "isAtivo", required = false, defaultValue = "true") boolean isAtivo){
        return new ResponseEntity<>(usuarioService.listAll(isAtivo), HttpStatus.OK);
    }

//    @GetMapping(path = "/{id}")
//    public ResponseEntity<Usuario> findById(@PathVariable long id){
//        return ResponseEntity.ok(usuarioService.findByIdOrThrowBadRequestException(id));
//    }

    @GetMapping(path = "/{username}") //PESQUISA DE USUARIO PELO USERNAME
    public ResponseEntity<UsuarioGetResponseBody> findByUsername(@PathVariable String username){
        return ResponseEntity.ok(usuarioService.findByUsernameOrThrowBadRequestException(username));
    }

    @PostMapping //CRIACAO USUARIO
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        System.out.println("### CADASTRANDO USUARIO ###");
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @PostMapping(path = "/recoveryPassword") //RECUPERACAO DE SENHA
    public ResponseEntity<Void> recoveryPassword (@RequestBody UsuarioRecoverySenha usuarioRecovery) {
        usuarioService.recoveryPassword(usuarioRecovery);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/pets") //CRIACAO DE PET
    public ResponseEntity<PetResponseBody> savePet(@PathVariable long id, @RequestBody Pet pet){
        Usuario usuario = usuarioService.findByIdOrThrowBadRequestException(id);
        pet.setUsuario(usuario);
        Pet petSalvo = petService.save(pet);
        petService.petUserRelationship(usuario.getIdUsuario(), petSalvo.getIdPet());

        PetResponseBody petResponse = mapper.map(petSalvo, PetResponseBody.class);
        UsuarioPetResponseBody usuarioPetResponseBody = mapper.map(petSalvo.getUsuario(), UsuarioPetResponseBody.class);
        petResponse.setUsuario(usuarioPetResponseBody);

        return new ResponseEntity<>(petResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}") //DELECAO DE USUARIO
    public ResponseEntity<Void> delete(@PathVariable long id){
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}") //EDICAO DE USUARIO
    public ResponseEntity<Void> replace(@RequestBody Usuario usuario, @PathVariable long id) {
        usuarioService.replace(usuario, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}/foto") //EDICAO DA FOTO DO USUARIO
    public ResponseEntity<Void> replaceFoto(@RequestBody Usuario usuario, @PathVariable long id) {
        usuarioService.replaceFoto(usuario, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}/senha") //EDICAO DA SENHA DO USUARIO
    public ResponseEntity<Void> replaceSenha(@RequestBody UsuarioTrocaSenhaRequestBody usuario, @PathVariable long id) {
        usuarioService.replaceSenha(usuario, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/pets") //LISTA TODOS PETS
    public ResponseEntity<List<PetResponseBody>> listPets(@RequestParam(name = "isAtivo", required = false, defaultValue = "true") boolean isAtivo){
        return new ResponseEntity<>(petService.listAll(isAtivo), HttpStatus.OK);
    }

    @GetMapping(path = "/pets/{nome}") //PESQUISA PET PELO NOME
    public ResponseEntity<List<PetResponseBody>> findPetsByName(@PathVariable String nome){
        return new ResponseEntity<>(petService.findByName(nome), HttpStatus.OK);
    }

    @GetMapping(path = "/pets/{id}/id") //PESQUISA PET PELO NOME
    public ResponseEntity<PetResponseBody> findPetsById(@PathVariable long id){
        return new ResponseEntity<>(petService.pesquisaPetId(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{idUser}/pets/{idPet}") //EDICAO DE PET
    public ResponseEntity<Void> replacePet(@RequestBody Pet pet, @PathVariable long idUser, @PathVariable long idPet) {
        petService.replace(pet, idPet, idUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{idUser}/pets/{idPet}") //DELECAO DE PET
    public ResponseEntity<Void> deletePet(@PathVariable long idUser, @PathVariable long idPet) {
        petService.delete(idUser, idPet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/{idUser}/pets/{idPet}/curtir") //CURTIR OU DESCURTIR PET
    public ResponseEntity<Void> curtePet(@PathVariable long idUser, @PathVariable long idPet) {
        curtidaUsuarioPetService.curtir(idUser, idPet);
        petService.atualizaCurtidasPet(idPet, curtidaUsuarioPetService.curtidasPet(idPet).size());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
