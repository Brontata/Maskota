package project.petme.springboot.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.petme.springboot.tg.domain.Pet;

import javax.transaction.Transactional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Modifying
    @Query(value = "insert into usuario_pets (usuario_id_usuario, pets_id_pet) VALUES (:id_usuario,:id_pet)",
            nativeQuery = true)
    @Transactional
    void petUserRelationship(@Param("id_usuario") Long id_usuario, @Param("id_pet") Long id_pet);
    
}
