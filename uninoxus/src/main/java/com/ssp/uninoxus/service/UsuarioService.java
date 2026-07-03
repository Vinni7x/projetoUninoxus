package com.ssp.uninoxus.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssp.uninoxus.entities.Usuario;
import com.ssp.uninoxus.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public  Usuario criar (Usuario usuario) {
		//validação para email. vai ser a forma de entrar
		//vou mudar para um usuario dto dps
		return usuarioRepository.save(usuario); 
	}
    
	
} 
