package miclaa.controller;

import miclaa.domain.Mensagem;
import miclaa.domain.Usuario;
import miclaa.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //força todos os retornos a serem strings
@RequestMapping("usuarios")
public class UsuarioController {
    @GetMapping
    public List<Usuario> list(){
        return List.of(new Usuario("Joao", 1, "SP", "Sorocaba", "admin", "admin@seila.com", "11122233301")); //fiz uma lista para mockar mais usuarios se necessario
    }


    @PostMapping //Aula 9
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        UsuarioService.save(anime);
    }


}
