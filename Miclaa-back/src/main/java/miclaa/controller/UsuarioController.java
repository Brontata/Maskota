package miclaa.controller;

import miclaa.domain.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //força todos os retornos a serem strings
@RequestMapping("usuarios")
public class UsuarioController {
    @GetMapping
    public List<Usuario> list(){
        return List.of(new Usuario("Joao", 1, "SP", "Sorocaba", "admin", "admin@seila.com", "11122233301")); //fiz uma lista para mockar mais usuarios se necessario
    }


}
