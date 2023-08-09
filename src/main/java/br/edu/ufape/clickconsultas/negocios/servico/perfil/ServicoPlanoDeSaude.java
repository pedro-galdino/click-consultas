package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPlanoDeSaude;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;

@Service
public class ServicoPlanoDeSaude {
	@Autowired
	private InterfaceColecaoPlanoDeSaude colecaoPlanoDeSaude;
	
	public List<PlanoDeSaude> buscarTodos(){
		return colecaoPlanoDeSaude.findAll();
	}
	
	public List<PlanoDeSaude> buscarOperadora(String operadora){
		return colecaoPlanoDeSaude.findByOperadora(operadora);
	}
	
	public PlanoDeSaude buscarPorNumero(int numero) {
		return colecaoPlanoDeSaude.findByNumero(numero);
	}
	
	public PlanoDeSaude buscarPorId(long id) {
		return colecaoPlanoDeSaude.findById(id).orElse(null);
	}
	
	public PlanoDeSaude salvar(PlanoDeSaude planoDeSaude) {
		return colecaoPlanoDeSaude.save(planoDeSaude);
	}
	
	public void remover(long id) {
		colecaoPlanoDeSaude.deleteById(id);
	}
}
