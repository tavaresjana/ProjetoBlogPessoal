package com.generation.blogpessoal.service;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.model.UsuarioLogin;
import com.generation.blogpessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	//função para cadastrar um usuario 
	public Optional<Usuario>cadastraUsuario(Usuario usuario){
	
		//primeiro valida se o usuario ja existe no banco
		if(repository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();
		
			//criptografa a senha do usuario caso nao exista
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		
		//e por ultimo, salvo o usuario com a senha ja criptografada no banco de dados
		return Optional.of(repository.save(usuario));	
	}
	
	//função para criptografar a senha digitada pelo usuario
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);
	};
	
	public Optional<UsuarioLogin> autenticaUsuario(Optional<UsuarioLogin> usuarioLogin){
		Optional<Usuario> usuario = repository.findByUsuario(usuarioLogin.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));

				return usuarioLogin;

			}
		}	
		
		return Optional.empty();
		
	} 
	
private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);

	}
private String gerarBasicToken(String usuario, String senha) {

	String token = usuario + ":" + senha;
	byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
	return "Basic " + new String(tokenBase64);

}


}
