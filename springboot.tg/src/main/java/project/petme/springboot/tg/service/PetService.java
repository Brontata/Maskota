package project.petme.springboot.tg.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.petme.springboot.tg.domain.Curtida;
import project.petme.springboot.tg.domain.Pet;
import project.petme.springboot.tg.domain.Usuario;
import project.petme.springboot.tg.domain.responses.*;
import project.petme.springboot.tg.repository.CurtidaRepository;
import project.petme.springboot.tg.repository.PetRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final CurtidaRepository curtidaRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<PetResponseBody> listAll(boolean isAtivo){

        List<Pet> listaPets = petRepository.findAll();
        List<Curtida> curtidas = curtidaRepository.findAll();

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
        pet.setAtivo(true);
        Pet petSalvo = petRepository.save(pet);

        return petSalvo;
    }

    public void petUserRelationship(Long id_usuario, Long id_pet){
        petRepository.petUserRelationship(id_usuario, id_pet);
    }

    public void replace(Pet pet, long idPet, long idUser){
        Pet petSalvo = findByIdOrThrowBadRequestException(idPet);

        if (petSalvo.getUsuario().getIdUsuario() != idUser){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O pet não pertence ao usuário informado");
        }

        if (!pet.getNumero().trim().isEmpty()){
            petSalvo.setNumero(pet.getNumero());
        }

        if (!pet.getEstado().trim().isEmpty()){
            petSalvo.setEstado(pet.getEstado());
        }

        if (!pet.getCidade().trim().isEmpty()){
            petSalvo.setCidade(pet.getCidade());
        }

        if (!pet.getDescricao().trim().isEmpty()){
            petSalvo.setDescricao(pet.getDescricao());
        }

        if (!pet.getFotoPet().trim().isEmpty()){
            petSalvo.setFotoPet(pet.getFotoPet());
        }

        petRepository.save(petSalvo);
    }

    public void delete(long idUser, long idPet) {
        Pet pet = findByIdOrThrowBadRequestException(idPet);

        if (pet.getUsuario().getIdUsuario() != idUser){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O pet não pertence ao usuário informado");
        }

        if (pet.isAtivo() == false){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O pet já está desativado");
        }

        pet.setAtivo(false);
        petRepository.save(pet);
    }

    public void atualizaCurtidasPet(long idPet, int quantidadeCurtidas){
        Pet petSalvo = findByIdOrThrowBadRequestException(idPet);

        List<Curtida> curtidas = curtidaRepository.findAll();
        List<GetAllCurtidas> curtidasResponse = new ArrayList<>();

        curtidas = curtidas.stream()
                .filter(p -> p.getPet().getIdPet() == petSalvo.getIdPet() && p.isCurtido()).collect(Collectors.toList());

        for (Curtida curtida: curtidas) {
            GetAllCurtidas curtidaSalva = mapper.map(curtida, GetAllCurtidas.class);
            GetAllCurtidasUsuario usuario = mapper.map(curtida.getUsuario(), GetAllCurtidasUsuario.class);
            curtidaSalva.setUsuario(usuario);

            curtidasResponse.add(curtidaSalva);
        }

        petSalvo.setQuantidadeCurtidas(quantidadeCurtidas);
        petSalvo.setCurtidas(curtidas);

        petRepository.save(petSalvo);
    }

    public PetResponseBody pesquisaPetId(long idPet) {
        Pet petSalvo = findByIdOrThrowBadRequestException(idPet);
        PetResponseBody petResponse = mapper.map(petSalvo, PetResponseBody.class);

        UsuarioPetResponseBody usuarioResponse = mapper.map(petSalvo.getUsuario(), UsuarioPetResponseBody.class);
        petResponse.setUsuario(usuarioResponse);

        return petResponse;
    }
}
