package com.generation.blogpessoal.repository;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


//notação que indica que é uma classe de teste e que rodará em uma porta aleatória a cada tetse realizado
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

//(será executado e resetado após o uso)cria uma instancia de etste que define que o ciclo de vida do teste vai respeitar o ciclo de vida da classe 
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;
	
	@BeforeAll
	void start() { //0L ID padrão para banco de dados em memória
		
		repository.save(new Usuario(0L, "Maiar", "isadora@gmail.com", "51 e pinga", "https://i.imgur.com/FETvs2O.jpg\r\n"));

		repository.save(new Usuario(0L, "Michael", "michael@gmail.com", "nunca fui rebaixado", "https://i.imgur.com/FETvs2O.jpg\r\n"));
		
		repository.save(new Usuario(0L, "Brocco", "brocco@gmail.com", "broccolis", "https://i.imgur.com/FETvs2O.jpg\r\n"));
		
		repository.save(new Usuario(0L, "Mayara", "will31smith@gmail.com", "cenoura", "https://i.imgur.com/FETvs2O.jpg\r\n"));
		
	}
	
	//Testando aplicação para retornar apenas um usuário
	//verde - certo ; azul - falha ; vermelho - erro de codigo
	@Test
	@DisplayName("Teste que retona 1 usuário")
	public void retornaUmUsuario() {
		Optional<Usuario> usuario = repository.findByUsuario("isadora@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("isadora@gmail.com")); //ele  nao retorna valor, mas sim apenas um TRUE or FALSE
	}
	
	@AfterAll
	public void end() {
		repository.deleteAll();
	}
}
