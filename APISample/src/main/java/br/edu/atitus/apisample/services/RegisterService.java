package br.edu.atitus.apisample.services;


import br.edu.atitus.apisample.entities.RegisterEntity;
import br.edu.atitus.apisample.repositories.RegisterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegisterService {

    private final RegisterRepository repository;

    public RegisterService(RegisterRepository repository) {
        super();
        this.repository = repository;
    }

    //Save
    public RegisterEntity save(RegisterEntity newRegister) throws Exception{

        //validações de regra de negocio
        if (newRegister.getLatitude() < -90 || newRegister.getLatitude() > 90) {
            throw new Exception("Latitude invallida!");
        }
        if (newRegister.getLongitude() < -180 || newRegister.getLongitude() > 180) {
            throw new Exception("Longitude invallida!");
        }
        if (newRegister.getUser() == null || newRegister.getUser().getId() == null) {
            throw new Exception("Usuario invallido!");
        }

        //invocar o metodo da camada repository
        repository.save(newRegister);
        return newRegister;
    }

    //Find by id
    public RegisterEntity findById(UUID id) throws Exception{
        RegisterEntity entity = repository.findById(id)
                .orElseThrow(() -> new Exception("Usuario não encontrado com esse id!"));
        return entity;
    }

    //Find all
    public List<RegisterEntity> findAll() throws Exception{
        List<RegisterEntity> registers = repository.findAll();
        return registers;
    }

    //Delete
    public void deleteById(UUID id) throws Exception {
        repository.deleteById(id);
    }
}
