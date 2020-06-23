package br.com.vlanmanager.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vlanmanager.projetofinal.dao.DepartamentoDAO;
import br.com.vlanmanager.projetofinal.model.Departamento;

@RestController
@CrossOrigin("*")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoDAO dao;
	
	@GetMapping("/departamentos")
	public ArrayList<Departamento> buscarTodos(){
		ArrayList<Departamento> lista;
		lista = (ArrayList<Departamento>)dao.findAll();
		return lista;
	}
	
	@GetMapping("/departamento/{id}")
	public ResponseEntity<Departamento> buscarPorId(@PathVariable int id){
		Departamento depto = dao.findById(id).orElse(null);
		if (depto != null) {
			return ResponseEntity.ok(depto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/departamento/update")
	public ResponseEntity<Departamento> atualizarDepartamento(@RequestBody Departamento atualizaDados){
		try {
			dao.save(atualizaDados);
			return ResponseEntity.ok(atualizaDados);
		}
		catch(Exception ex) {
			return ResponseEntity.status(400).build();
		}
	}

}
