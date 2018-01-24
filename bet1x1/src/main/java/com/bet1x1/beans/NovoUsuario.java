package com.bet1x1.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.usuarios.TiposDeUsuario;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.entidades.usuarios.enderecos.Endereco;


@ViewScoped
@ManagedBean
public class NovoUsuario extends AbstractBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5976838804313515033L;

	//services
	private ModalidadeServices modalidadeServices = new ModalidadeServices();
	private CompeticaoServices competicaoServices = new CompeticaoServices();
	private EventoServices eventoServices = new EventoServices();
	private UsuarioServices usuarioServices = new UsuarioServices();
	
	
	private String nome;
	private String login;
	private String senha;
	private String senhaConfirmada;
	private String email;
	private String cpf;
	
	
	// endereco
	
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	
	
	
	
	public void init() {
		
		
	}
	
	public void teste() {
		
		usuarioServices.getUsuarioLogado();
	}
	
	
	
	public void criarNovoUsuario() {
		
		
		if(!senha.equals(senhaConfirmada)) {
			
			reportarMensagemDeErro("Senhas não conferem.");
		}
		
		else {
			
			Usuario usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setEmail(email);
			usuario.setTipo(TiposDeUsuario.CLIENTE);
			
			Endereco endereco = new Endereco();
			endereco.setBairro(bairro);
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			
			usuario.setEndereco(endereco);
			
			
			try {
				usuarioServices.save(usuario);
				reportarMensagemDeSucesso("Usuário inserido com sucesso!");
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				reportarMensagemDeErro("Não foi possível cadastrar usuário.");
			}
			
		}
	}
	
	
	
	
	
	
	//getters and setters

	public ModalidadeServices getModalidadeServices() {
		return modalidadeServices;
	}

	public void setModalidadeServices(ModalidadeServices modalidadeServices) {
		this.modalidadeServices = modalidadeServices;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EventoServices getEventoServices() {
		return eventoServices;
	}

	public void setEventoServices(EventoServices eventoServices) {
		this.eventoServices = eventoServices;
	}

	public UsuarioServices getUsuarioServices() {
		return usuarioServices;
	}

	public void setUsuarioServices(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenhaConfirmada() {
		return senhaConfirmada;
	}

	public void setSenhaConfirmada(String senhaConfirmada) {
		this.senhaConfirmada = senhaConfirmada;
	}

	public CompeticaoServices getCompeticaoServices() {
		return competicaoServices;
	}

	public void setCompeticaoServices(CompeticaoServices competicaoServices) {
		this.competicaoServices = competicaoServices;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
