package project.petme.springboot.tg.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.petme.springboot.tg.domain.Pet;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.domain.responses.PetResponseBody;
import project.petme.springboot.tg.domain.responses.UsuarioPetResponseBody;
import project.petme.springboot.tg.repository.PetRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<PetResponseBody> listAll(){

        List<Pet> listaPets = petRepository.findAll();
        
        List<PetResponseBody> petsListResponse = new ArrayList<>();
        for (Pet pet :listaPets) {
            PetResponseBody petResponse = mapper.map(pet, PetResponseBody.class);
            UsuarioPetResponseBody usuarioPetResponseBody = mapper.map(pet.getUsuario(), UsuarioPetResponseBody.class);
            petResponse.setUsuario(usuarioPetResponseBody);
            petsListResponse.add(petResponse);
        }

        return petsListResponse;
    }

    public Pet findByIdOrThrowBadRequestException(long id) {
//        logger.info("### Procurando usuário por ID ###");
        return  petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario nao encontrado"));
    }

    public Pet save(Pet pet) {
        petRepository.save(pet);
        return pet;
    }

    public void petUserRelationship(Long id_usuario, Long id_pet){
        petRepository.petUserRelationship(id_usuario, id_pet);
    }
}
