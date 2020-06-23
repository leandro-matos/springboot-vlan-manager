package br.com.vlanmanager.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vlanmanager.projetofinal.dao.DepartamentoDAO;
import br.com.vlanmanager.projetofinal.dao.SolicitacaoDAO;
import br.com.vlanmanager.projetofinal.dao.UsuarioDAO;
import br.com.vlanmanager.projetofinal.model.Departamento;
import br.com.vlanmanager.projetofinal.model.Solicitacao;
import br.com.vlanmanager.projetofinal.model.Usuario;

@RestController
@CrossOrigin("*")
public class SolicitacaoController {
	
	@Autowired
	private SolicitacaoDAO sdao;
	
	@Autowired
	private DepartamentoDAO ddao;
	
	@Autowired
	private UsuarioDAO      udao;

	public boolean verificar(@RequestBody Solicitacao novaSolicitacao) {
		Solicitacao resposta = sdao.findByDestinoAndOrigem(novaSolicitacao.getDestino(), novaSolicitacao.getOrigem());
		if (resposta != null) {
			return false;
		} else {
			return true;
		}
	}
	
	@PostMapping("/solicitacoes/new")
	public ResponseEntity<Solicitacao> addNovaSolicitacao(@RequestBody Solicitacao novaSolicitacao){
		try {
			if (verificar(novaSolicitacao)==true) {
				System.out.println("SOLICITANTE:"+novaSolicitacao.getSolicitante().getId());
				System.out.println("ORIGEM:"+novaSolicitacao.getOrigem().getId());
				System.out.println("DESTINO:"+novaSolicitacao.getDestino().getId());
				
				Usuario user = udao.findById(novaSolicitacao.getSolicitante().getId()).get();
				System.out.println("user = "+user.getNome());
				
				Departamento novo = ddao.findById(novaSolicitacao.getDestino().getId()).get();
				
				String comando = "switchport vlan " + user.getDepartamento().getVlan() + ";"+
				                 "interface range " +user.getConectorRede() + " "+
						         novo.getVlan()+ "; exit";
				
				novaSolicitacao.setComandoRoteador(comando);
				user.setDepartamento(novo);
			    
				udao.save(user);
				sdao.save(novaSolicitacao);
				return ResponseEntity.ok(novaSolicitacao);
			} else {
				return ResponseEntity.status(400).build();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/solicitacoes")
	public ArrayList<Solicitacao> getSolicitacoes(){
		return (ArrayList<Solicitacao>)sdao.findAll();
	}

	
	@GetMapping("/solicitacoes/{id}")
	public ResponseEntity<Solicitacao> getDetalhes(@PathVariable int id){
		Solicitacao solicitacao = sdao.findById(id).orElse(null);
		if (solicitacao != null) {
			return ResponseEntity.ok(solicitacao);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
