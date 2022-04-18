package miclaa.controller;

import lombok.RequiredArgsConstructor;
import miclaa.domain.Usuario;
import miclaa.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe que contem os endpoints para Usuario
 * /
 */
@RestController //força todos os retornos a serem strings
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioservice;
    @GetMapping
    public ResponseEntity<List<Usuario>> list(){
        return ResponseEntity.ok(UsuarioService.listAll()); //infos adicionais: Aula 8
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return ResponseEntity.ok(UsuarioService.findById(id)); //infos adicionais: Aula 8
    }


    @PostMapping //Aula 9
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody Usuario usuario){
        return UsuarioService.save(usuario);
    }


}
