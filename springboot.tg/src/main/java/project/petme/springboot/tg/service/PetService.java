package project.petme.springboot.tg.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.petme.springboot.tg.domain.Pet;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.domain.responses.PetResponseBody;
import project.petme.springboot.tg.domain.responses.UsuarioGetResponseBody;
import project.petme.springboot.tg.domain.responses.UsuarioPetResponseBody;
import project.petme.springboot.tg.repository.PetRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<PetResponseBody> listAll(boolean isAtivo){

        List<Pet> listaPets = petRepository.findAll();
        
        List<PetResponseBody> petsListResponse = new ArrayList<>();
        for (Pet pet :listaPets) {
            PetResponseBody petResponse = mapper.map(pet, PetResponseBody.class);
            UsuarioPetResponseBody usuarioPetResponseBody = mapper.map(pet.getUsuario(), UsuarioPetResponseBody.class);
            petResponse.setUsuario(usuarioPetResponseBody);
            petsListResponse.add(petResponse);
        }

        if(isAtivo){
            petsListResponse = petsListResponse.stream()
                    .filter(p -> p.isAtivo()).collect(Collectors.toList());
        }

        if(!isAtivo){
            petsListResponse = petsListResponse.stream()
                    .filter(p -> !p.isAtivo()).collect(Collectors.toList());
        }

        return petsListResponse;
    }

    public List<PetResponseBody> findByName(String nome){
        List<Pet> listaPets = petRepository.findAll();
        List<PetResponseBody> petsListResponse = new ArrayList<>();

        listaPets = listaPets.stream()
                .filter(p -> p.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());

        for (Pet pet :listaPets) {
            PetResponseBody petResponse = mapper.map(pet, PetResponseBody.class);
            UsuarioPetResponseBody usuarioPetResponseBody = mapper.map(pet.getUsuario(), UsuarioPetResponseBody.class);
            petResponse.setUsuario(usuarioPetResponseBody);
            petsListResponse.add(petResponse);
        }

        return petsListResponse;
    }

//    public PetResponseBody findByNomeOrThrowBadRequestException(String nome){
//        Pet pet = petRepository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet chamado " + nome + " nao encontrado"))
//        PetResponseBody petResponse = mapper
//    }

    public Pet findByIdOrThrowBadRequestException(long id) {
//        logger.info("### Procurando usuário por ID ###");
        return  petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet nao encontrado"));
    }

    public Pet save(Pet pet) {
        petRepository.save(pet);
        return pet;
    }

    public void petUserRelationship(Long id_usuario, Long id_pet){
        petRepository.petUserRelationship(id_usuario, id_pet);
    }

    public void replace(Pet pet){
        //TODO
    }

    public void delete(long id) {
        Pet pet = findByIdOrThrowBadRequestException(id);
        pet.setAtivo(false);
        petRepository.save(pet);
    }
}
