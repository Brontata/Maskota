package miclaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miclaa.domain.Pet;

import java.util.List;

@RestController // força todos os retornos a serem strings
@RequestMapping("pets")
public class PetController {
    @GetMapping
    public List<Pet> list() {
        return List.of(new Pet("Slinky", 1, "SaoPaulo", 66, "Parceiro do Woody", "BoituvaCity")); // fiz uma lista para
                                                                                                  // mockar mais pets se
                                                                                                  // necessario
    }

}
