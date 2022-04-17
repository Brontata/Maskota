package miclaa.service;

import miclaa.domain.Usuario;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class UsuarioService {

//    public List<Usuario> listAll(){
//        //cuidado com esse metodo, se nao pega tudo que tem na base =P
//    }

    public Usuario save(Usuario usuario){ //aula 9
        Usuario.setId(ThreadLocalRandom.current().nextLong(3, 10000000));

    }
}
