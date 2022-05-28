package project.petme.springboot.tg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.petme.springboot.tg.domain.Pet;
import project.petme.springboot.tg.repository.PetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public List<Pet> listAll(){
        return petRepository.findAll();
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
}
