package project.petme.springboot.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.petme.springboot.tg.domain.Curtida;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurtidaRepository extends JpaRepository<Curtida, Long> {
}
