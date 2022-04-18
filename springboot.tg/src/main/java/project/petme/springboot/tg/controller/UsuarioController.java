package project.petme.springboot.tg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.requests.UsuarioPostRequestBody;
import project.petme.springboot.tg.requests.UsuarioPutRequestBody;
import project.petme.springboot.tg.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("usuarios")
//@Log4j2
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> list(){
        return new ResponseEntity<>(usuarioService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable long id){
        return ResponseEntity.ok(usuarioService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioPostRequestBody usuario){
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@RequestBody UsuarioPutRequestBody usuario) {
        usuarioService.replace(usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
