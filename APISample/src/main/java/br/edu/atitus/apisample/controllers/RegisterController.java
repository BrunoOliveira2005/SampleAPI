package br.edu.atitus.apisample.controllers;

import br.edu.atitus.apisample.dtos.RegisterDTO;
import br.edu.atitus.apisample.entities.RegisterEntity;
import br.edu.atitus.apisample.entities.UserEntity;
import br.edu.atitus.apisample.services.RegisterService;
import br.edu.atitus.apisample.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.BeanProperty;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/registers")
public class RegisterController {


    private final RegisterService service;
    private final UserService userService;

    public RegisterController(RegisterService service, UserService userService) {
        super();
        this.service = service;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<RegisterEntity> createRegister(@RequestBody RegisterDTO registerDTO) throws Exception {
        //converter DTO2Entity
        RegisterEntity newRegister = new RegisterEntity();
        BeanUtils.copyProperties(registerDTO, newRegister);

        //buscar um usuario cadastrado
        //quando a autenticação estiver autenticando
        UserEntity user = userService.findAll().get(0);
        newRegister.setUser(user);

        //Invocar metodo save da camada service
        service.save(newRegister);

        //retornar a estidade salva
        return ResponseEntity.status(201).body(newRegister);
    }

    @GetMapping
    public ResponseEntity<List<RegisterEntity>> getRegisters() throws Exception {
        var registers = service.findAll();
        return ResponseEntity.ok(registers);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegisterEntity> getOneRegister(@PathVariable UUID id) throws Exception {
        var register = service.findById(id);
        return ResponseEntity.ok(register);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegisterEntity> putRegister(@PathVariable UUID id, @RequestBody RegisterDTO dto) throws Exception {
        RegisterEntity register = service.findById(id);
        BeanUtils.copyProperties(dto, register);

        service.save(register);
        return ResponseEntity.ok(register);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegister(@PathVariable UUID id) throws Exception {
        service.deleteById(id);
        return ResponseEntity.ok("Registro deletado com sucesso!");
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        String mensage = ex.getMessage().replaceAll("\r\n","");
        return ResponseEntity.badRequest().body(mensage);
    }
}
