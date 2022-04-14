package miclaa.controller;

import miclaa.domain.Mensagem;
import miclaa.domain.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // força todos os retornos a serem strings
@RequestMapping("usuarios")
public class MensagemController {
    @GetMapping
    public List<Mensagem> list() {
        return List.of(new Mensagem(345, null, null, null)); // fiz uma lista para mockar mais mensagens se necessario
    }

}
