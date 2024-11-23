package br.edu.atitus.apisample.services;

import org.springframework.stereotype.Service;

import br.edu.atitus.apisample.entities.UserEntity;
import br.edu.atitus.apisample.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository repository;
	
	
	
	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}

	
	public UserEntity save(UserEntity newUser) throws Exception {
		
		
		if (newUser == null)
			throw new Exception("Objeto nulo!");
		if (newUser.getName() == null || newUser.getName().isEmpty())
			throw new Exception("Nome Inválido!\n");
		if (newUser.getEmail() == null || newUser.getEmail().isEmpty())
			throw new Exception("E-mail Inválido!");
		
		if (newUser.getPassword() == null || newUser.getPassword().isEmpty())
			throw new Exception("Password Inválido!");
		
		
		newUser.setName(newUser.getName().trim());

		if (this.repository.existsByEmail(newUser.getEmail()))
			throw new Exception("Já existe usuario cadastrado com esse email!\n");

		this.repository.save(newUser);
		
		return newUser;
		
	}
}
