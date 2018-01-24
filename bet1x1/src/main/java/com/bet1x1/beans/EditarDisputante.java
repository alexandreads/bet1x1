package com.bet1x1.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.disputantes.DisputanteServices;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.utilitarios.EnderecoPaginas;




@ViewScoped
@ManagedBean
public class EditarDisputante extends AbstractBean{
	
	//services
	private CompeticaoServices competicaoServices = new CompeticaoServices();
	private ModalidadeServices modalidadeServices = new ModalidadeServices();
	private DisputanteServices disputanteServices = new DisputanteServices();
	
	//variaveis
	private Modalidade modalidadeEscolhida;
	
	private Disputante disputante;

	

	
	
	// arrays
	
	private List<Modalidade> modalidades = new ArrayList<Modalidade>();
	
	
	
	
	
	public void init() {
		
		//setando a modalidade
		loadModalidades();
		
	}
	
	
	public void loadModalidades() {
		modalidades = modalidadeServices.retornarTodasEmOrdemAlfabetica();
		
	}
	
	
	public String editar() {
		
			
		disputanteServices.update(disputante);
		
		reportarMensagemDeSucesso("Disputante atualizado com sucesso!");
		
		
		return EnderecoPaginas.MENU_DISPUTANTE+EnderecoPaginas.FACES_REDIRECT;
		
		
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





	public List<Modalidade> getModalidades() {
		return modalidades;
	}


	public void setModalidades(List<Modalidade> modalidades) {
		this.modalidades = modalidades;
	}


	public Disputante getDisputante() {
		return disputante;
	}


	public void setDisputante(Disputante disputante) {
		this.disputante = disputante;
	}
	
	
	
	


	
	
	
	
	
	
	

}

