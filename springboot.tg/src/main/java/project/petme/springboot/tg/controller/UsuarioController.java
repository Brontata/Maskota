package project.petme.springboot.tg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.petme.springboot.tg.domain.Pet;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.service.PetService;
import project.petme.springboot.tg.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("usuarios")
//@Log4j2
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final PetService petService;

    private final PasswordEncoder encoder;

    @GetMapping
    public ResponseEntity<List<Usuario>> list(){
        return new ResponseEntity<>(usuarioService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable long id){
        return ResponseEntity.ok(usuarioService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        System.out.println("### CADASTRANDO USUARIO ###");
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @PostMapping(path = "/{id}/pets")
    public ResponseEntity<Pet> savePet(@PathVariable long id, @RequestBody Pet pet){
        Usuario usuario = usuarioService.findByIdOrThrowBadRequestException(id);
        pet.setUsuario(usuario);
        Pet petSalvo = petService.save(pet);

        return new ResponseEntity<>(petSalvo, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@RequestBody Usuario usuario) {
        usuarioService.replace(usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
