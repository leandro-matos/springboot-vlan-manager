package br.com.vlanmanager.projetofinal.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.vlanmanager.projetofinal.model.Departamento;
import br.com.vlanmanager.projetofinal.model.Solicitacao;

public interface SolicitacaoDAO extends CrudRepository<Solicitacao, Integer>{
	public Solicitacao findByDestinoAndOrigem(Departamento destino, Departamento origem);
}
