package miclaa.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mensagem {

    private Long id;
    private Usuario destinatario;
    private Usuario remetente;
    private Date data;

}
