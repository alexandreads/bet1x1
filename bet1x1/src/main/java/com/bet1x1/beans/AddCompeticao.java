package com.bet1x1.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.utilitarios.EnderecoPaginas;




@ViewScoped
@ManagedBean
public class AddCompeticao extends AbstractBean{
	
	//services
	private CompeticaoServices competicaoServices = new CompeticaoServices();
	private ModalidadeServices modalidadeServices = new ModalidadeServices();
	
	
	//variaveis
	private Modalidade modalidadeEscolhida;
	
	private Competicao competicao;

	

	
	
	// arrays
	
	private List<Modalidade> modalidades = new ArrayList<Modalidade>();
	
	
	
	
	
	public void init() {
		
		//setando a modalidade
		loadModalidades();
		
		
		
		competicao = new Competicao();
	}
	
	
	public void loadModalidades() {
		modalidades = modalidadeServices.retornarTodasEmOrdemAlfabetica();
		
	}
	
	
	public String adicionarCompeticao() {
		
		try {
			modalidadeEscolhida.getCompeticoes().add(competicao);
			competicao.setModalidade(modalidadeEscolhida);
			modalidadeServices.update(modalidadeEscolhida);
			reportarMensagemDeSucesso("Competição cadastrada com Sucesso!");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			reportarMensagemDeErro(e.getMessage());
		}
		
		return EnderecoPaginas.ADD_COMPETICAO+EnderecoPaginas.FACES_REDIRECT; 
		
	}





	public ModalidadeServices getModalidadeServices() {
		return modalidadeServices;
	}


	public void setModalidadeServices(ModalidadeServices modalidadeServices) {
		this.modalidadeServices = modalidadeServices;
	}


	public Modalidade getModalidadeEscolhida() {
		return modalidadeEscolhida;
	}


	public void setModalidadeEscolhida(Modalidade modalidadeEscolhida) {
		this.modalidadeEscolhida = modalidadeEscolhida;
	}


	

	public Competicao getCompeticao() {
		return competicao;
	}


	public void setCompeticao(Competicao competicao) {
		this.competicao = competicao;
	}


	public List<Modalidade> getModalidades() {
		return modalidades;
	}


	public void setModalidades(List<Modalidade> modalidades) {
		this.modalidades = modalidades;
	}
	
	
	
	


	
	
	
	
	
	
	

}
