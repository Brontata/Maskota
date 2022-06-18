package project.petme.springboot.tg.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.petme.springboot.tg.domain.Curtida;
import project.petme.springboot.tg.domain.Pet;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.domain.responses.GetAllCurtidas;
import project.petme.springboot.tg.domain.responses.GetAllCurtidasUsuario;
import project.petme.springboot.tg.repository.CurtidaRepository;
import project.petme.springboot.tg.repository.PetRepository;
import project.petme.springboot.tg.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurtidaUsuarioPetService {

    private final CurtidaRepository curtidaRepository;
    private final PetRepository petRepository;
    private final UsuarioRepository usuarioRepository;
    private final PetService petService;
    private final UsuarioService usuarioService;
    private final ModelMapper mapper = new ModelMapper();

    public List<Curtida> findAll(){
        return curtidaRepository.findAll();
    }

    public void curtir(long idUser, long idPet) {
        Usuario usuario = usuarioService.findByIdOrThrowBadRequestException(idUser);
        Pet pet = petService.findByIdOrThrowBadRequestException(idPet);

        List<Curtida> curtidas = findAll();

        curtidas = curtidas.stream()
                .filter(p -> p.getUsuario().getIdUsuario() == usuario.getIdUsuario() && p.getPet().getIdPet() == pet.getIdPet()).collect(Collectors.toList());

        if (curtidas.size() == 1){
            curtidas.get(0).setCurtido(!curtidas.get(0).isCurtido());
            curtidaRepository.save(curtidas.get(0));
        } else {
            Curtida curtida = new Curtida(null, usuario, pet, true);
            Curtida curtidaSalva = curtidaRepository.save(curtida);
        }
    }

    public List<GetAllCurtidas> curtidasPet(Long idPet){
        Pet pet = petService.findByIdOrThrowBadRequestException(idPet);
        List<Curtida> curtidas = findAll();
        List<GetAllCurtidas> curtidasResponse = new ArrayList<>();

        curtidas = curtidas.stream()
                .filter(p -> p.getPet().getIdPet() == pet.getIdPet() && p.isCurtido()).collect(Collectors.toList());

        for (Curtida curtida: curtidas) {
            GetAllCurtidas curtidaSalva = mapper.map(curtida, GetAllCurtidas.class);
            GetAllCurtidasUsuario usuario = mapper.map(curtida.getUsuario(), GetAllCurtidasUsuario.class);
            curtidaSalva.setUsuario(usuario);

            curtidasResponse.add(curtidaSalva);
        }

        return curtidasResponse;
    }

//    public List<Curtida> curtidasPetRaw(Long idPet){
//        Pet pet = petService.findByIdOrThrowBadRequestException(idPet);
//        List<Curtida> curtidas = findAll();
//
//        curtidas = curtidas.stream()
//                .filter(p -> p.getPet().getIdPet() == pet.getIdPet() && p.isCurtido()).collect(Collectors.toList());
//
//        return curtidas;
//    }

}
