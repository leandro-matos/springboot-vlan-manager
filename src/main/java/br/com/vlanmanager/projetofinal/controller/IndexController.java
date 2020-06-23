package br.com.vlanmanager.projetofinal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String get() {
		return "API com a Lista de Informações sobre o sistema de Vlan's";
	}
	
}
