package project.petme.springboot.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.petme.springboot.tg.domain.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
