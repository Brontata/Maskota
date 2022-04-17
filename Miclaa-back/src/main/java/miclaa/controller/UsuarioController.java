package miclaa.controller;

import lombok.RequiredArgsConstructor;
import miclaa.domain.Mensagem;
import miclaa.domain.Usuario;
import miclaa.service.UsuarioService;
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
    public List<Usuario> list(){
        return UsuarioService.listAll();
    }


//    @PostMapping //Aula 9
//    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
//        UsuarioService.save(anime);
//    }


}
