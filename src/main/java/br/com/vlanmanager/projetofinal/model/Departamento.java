package br.com.vlanmanager.projetofinal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TBL_DEPARTAMENTO")
public class Departamento {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome", length=100)
	private String nome;
	
	@Column(name="unidade", length=100)
	private String unidade;
	
	@Column(name="andar", length=15)
	private String andar;
	
	@Column(name="vlan",length=14)
	private String vlan;
	
	@JsonIgnoreProperties("departamento")
	@OneToMany(mappedBy="departamento", cascade=CascadeType.ALL)
	private List<Usuario> listaUsuarios;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getAndar() {
		return andar;
	}

	public void setAndar(String andar) {
		this.andar = andar;
	}

	public String getVlan() {
		return vlan;
	}

	public void setVlan(String vlan) {
		this.vlan = vlan;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Departamento(int id, String nome, String unidade, String andar, String vlan, List<Usuario> listaUsuarios) {
		super();
		this.id = id;
		this.nome = nome;
		this.unidade = unidade;
		this.andar = andar;
		this.vlan = vlan;
		this.listaUsuarios = listaUsuarios;
	}

	public Departamento() {
		super();
	}
	
}