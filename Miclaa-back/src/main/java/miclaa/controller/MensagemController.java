package miclaa.controller;

import miclaa.domain.Mensagem;
import miclaa.domain.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // força todos os retornos a serem strings
@RequestMapping("mensagens")
public class MensagemController {
    @GetMapping
    public List<Mensagem> list() {
        return List.of(new Mensagem(345L, null, null, null)); // fiz uma lista para mockar mais mensagens se necessario
    }


}


