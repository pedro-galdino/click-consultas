package br.edu.ufape.clickconsultas.negocios.servico.financeiro;

import java.util.List;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ChavePixInexistenteException;

public interface InterfaceServicoPix {

	List<Pix> buscarTodos();

	Pix buscarPorId(long id);

	Pix salvar(Pix pix);

	void remover(long id) throws ChavePixInexistenteException;

}