package br.edu.atitus.apisample.repositories;

import br.edu.atitus.apisample.entities.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, UUID> {

}
