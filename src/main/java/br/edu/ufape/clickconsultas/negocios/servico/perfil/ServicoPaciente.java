package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoPaciente;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoPaciente implements InterfaceServicoPaciente {
	@Autowired
	private InterfaceColecaoPaciente colecaoPaciente;

	public List<Paciente> buscarTodos() {
		return colecaoPaciente.findAll();
	}

	public Paciente buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Paciente p = colecaoPaciente.findById(id).orElse(null);
		if (p == null)
			throw new ObjetoNaoEncontradoException("o", "paciente");
		return p;
	}

	public List<Paciente> buscarPorNome(String nome) {
		return colecaoPaciente.findByNomeContainingIgnoreCase(nome);
	}

	public Paciente buscarPorEmail(String email) throws ObjetoNaoEncontradoException {
		Paciente p = colecaoPaciente.findByEmailContainingIgnoreCase(email);
		if (p == null)
			throw new ObjetoNaoEncontradoException("o", "paciente");
		return p;
	}

	public Paciente buscarPorCpf(String cpf) throws ObjetoNaoEncontradoException {
		Paciente p = colecaoPaciente.findByCpf(cpf);
		if (p == null)
			throw new ObjetoNaoEncontradoException("o", "paciente");
		return p;
	}

	public Paciente salvar(Paciente paciente) throws ObjetoEmUsoException {
		Paciente pacienteExistenteByEmail = colecaoPaciente.findByEmailContainingIgnoreCase(paciente.getEmail());
		if (pacienteExistenteByEmail != null && paciente.getId() != pacienteExistenteByEmail.getId())
			throw new ObjetoEmUsoException("o", "e-mail");

		Paciente pacienteExistenteByCpf = colecaoPaciente.findByCpf(paciente.getCpf());
		if (pacienteExistenteByCpf != null && paciente.getId() != pacienteExistenteByCpf.getId())
			throw new ObjetoEmUsoException("o", "CPF");

		return colecaoPaciente.save(paciente);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Paciente p = buscarPorId(id);
		if (p == null)
			throw new ObjetoNaoEncontradoException("o", "paciente");
		colecaoPaciente.deleteById(p.getId());
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

	public Carteira buscarCarteiraPorPacienteId(long pacienteId) throws ObjetoNaoEncontradoException {
		return buscarPorId(pacienteId).getCarteira();
	}

	public Carteira salvarCarteira(long pacienteId, Carteira carteira) throws ObjetoNaoEncontradoException {
		Paciente p = buscarPorId(pacienteId);
		p.setCarteira(carteira);
		return colecaoPaciente.save(p).getCarteira();
	}

	public Pix buscarPixPorId(long pacienteId, long pixId) throws ObjetoNaoEncontradoException {
		Carteira c = buscarPorId(pacienteId).getCarteira();
		for (Pix pix : c.getChavesPix())
			if (pix.getId() == pixId)
				return pix;
		throw new ObjetoNaoEncontradoException("o", "pix");
	}

	public List<Pix> salvarPixCarteira(long pacienteId, Pix pix) throws ObjetoNaoEncontradoException {
		Carteira c = buscarPorId(pacienteId).getCarteira();
		List<Pix> lp = c.getChavesPix();
		lp.add(pix);
		c.setChavesPix(lp);
		return salvarCarteira(pacienteId, c).getChavesPix();
	}

	public void removerPixCarteira(long pacienteId, long pixId) throws ObjetoNaoEncontradoException {
		Carteira c = buscarPorId(pacienteId).getCarteira();
		List<Pix> lp = c.getChavesPix();
		Pix pix = buscarPixPorId(pacienteId, pixId);
		lp.remove(pix);
		c.setChavesPix(lp);
		salvarCarteira(pacienteId, c);
	}

}