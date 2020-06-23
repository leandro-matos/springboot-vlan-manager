package br.com.vlanmanager.projetofinal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name="TBL_USUARIO") 
public class Usuario {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int     id;
		
	@Column(name="racf", length=7, nullable=false)
	private String  racf;
	
	@Column(name="funcional", length=9, nullable=false)
	private String  funcional;
	
	@Column(name="nome", length=100)
	private String  nome;
	
	@Column(name="email", length=100)
	private String  email;
	
	@Column(name="senha", length=20)
	private String  senha;
	
	@Column(name="linkfoto", length=200)
	private String  linkFoto;
	
	@Column(name="numserie", length=5)
	private String numSerie;
	
	@Column(name="descricao", length=200)
	private String descricao;
	
	@Column(name="conector", length=5)
	private String conectorRede;
	
	@ManyToOne
	@JsonIgnoreProperties("listaUsuarios")
	private Departamento departamento;
	
	@JsonIgnoreProperties("solicitante")
	@OneToMany(mappedBy="solicitante", cascade=CascadeType.ALL)
	private List<Solicitacao> solicitacoes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRacf() {
		return racf;
	}

	public void setRacf(String racf) {
		this.racf = racf;
	}

	public String getFuncional() {
		return funcional;
	}

	public void setFuncional(String funcional) {
		this.funcional = funcional;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLinkFoto() {
		return linkFoto;
	}

	public void setLinkFoto(String linkFoto) {
		this.linkFoto = linkFoto;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConectorRede() {
		return conectorRede;
	}

	public void setConectorRede(String conectorRede) {
		this.conectorRede = conectorRede;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public Usuario(int id, String racf, String funcional, String nome, String email, String senha, String linkFoto,
			String numSerie, String descricao, String conectorRede, Departamento departamento,
			List<Solicitacao> solicitacoes) {
		super();
		this.id = id;
		this.racf = racf;
		this.funcional = funcional;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.linkFoto = linkFoto;
		this.numSerie = numSerie;
		this.descricao = descricao;
		this.conectorRede = conectorRede;
		this.departamento = departamento;
		this.solicitacoes = solicitacoes;
	}

	public Usuario() {
		super();
	}
		
}
