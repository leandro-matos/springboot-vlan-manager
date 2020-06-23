package br.com.vlanmanager.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vlanmanager.projetofinal.dao.UsuarioDAO;
import br.com.vlanmanager.projetofinal.model.Usuario;

@RestController
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO dao;
	
	@GetMapping("/usuarios")
	public ArrayList<Usuario> listarTodosUsuarios(){
		return (ArrayList<Usuario>)dao.findAll();
	}

	@PostMapping("/login")
	public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario){
	    Usuario u = dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
	    Usuario racfu = dao.findByRacfAndSenha(usuario.getRacf(), usuario.getSenha());
	    if (u != null) {
	        return ResponseEntity.ok(u);
	    } else if (racfu !=null ) {
	        return ResponseEntity.ok(racfu);
	    }
	    else {
	        return ResponseEntity.status(403).build();
	    }  
	}
   
       
	
	@PutMapping("/usuario/update")
	public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario atualizaDados){
		try {
			dao.save(atualizaDados);
			return ResponseEntity.ok(atualizaDados);
		}
		catch(Exception ex) {
			return ResponseEntity.status(400).build();
		}
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> buscarPeloId(@PathVariable int id){
		Usuario user = dao.findById(id).orElse(null);
		if (user != null) {
			return ResponseEntity.ok(user);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
