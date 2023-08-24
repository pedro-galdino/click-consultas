package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoPaciente implements InterfaceServicoPaciente {
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;

	public Paciente buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Paciente p = colecaoPaciente.findById(id).orElse(null);
		if (p == null)
			throw new ObjetoNaoEncontradoException("o", "paciente");
		return p;
	}

	public Paciente salvar(Paciente paciente) {
		return colecaoPaciente.save(paciente);
	}
	
	// --- Plano de Saúde ---

	public PlanoDeSaude buscarPlanoDeSaude(long pacienteId) throws ObjetoNaoEncontradoException {
		Paciente p = buscarPorId(pacienteId);
		if (p.getPlano() == null)
			throw new ObjetoNaoEncontradoException("o", "plano de saúde");
		return p.getPlano();
	}

	public PlanoDeSaude salvarPlanoDeSaude(long pacienteId, PlanoDeSaude plano) throws ObjetoNaoEncontradoException {
		Paciente p = buscarPorId(pacienteId);
		p.setPlano(plano);
		return colecaoPaciente.save(p).getPlano();
	}

	public void removerPlanoDeSaude(long pacienteId) throws ObjetoNaoEncontradoException {
		Paciente p = buscarPorId(pacienteId);
		if (p.getPlano() == null)
			throw new ObjetoNaoEncontradoException("o", "plano de saúde");
		p.setPlano(null);
		colecaoPaciente.save(p);
	}

}