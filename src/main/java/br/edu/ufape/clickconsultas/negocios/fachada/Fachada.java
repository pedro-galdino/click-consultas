package br.edu.ufape.clickconsultas.negocios.fachada;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Saque;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.financeiro.InterfaceServicoCarteira;
import br.edu.ufape.clickconsultas.negocios.servico.financeiro.InterfaceServicoDeposito;
import br.edu.ufape.clickconsultas.negocios.servico.financeiro.InterfaceServicoPix;
import br.edu.ufape.clickconsultas.negocios.servico.financeiro.InterfaceServicoSaque;
import br.edu.ufape.clickconsultas.negocios.servico.perfil.InterfaceServicoMedico;
import br.edu.ufape.clickconsultas.negocios.servico.perfil.InterfaceServicoPaciente;
import br.edu.ufape.clickconsultas.negocios.servico.perfil.InterfaceServicoPlanoDeSaude;

@Service
public class Fachada {
	@Autowired
	private InterfaceServicoPaciente servicoPaciente;
	@Autowired
	private InterfaceServicoMedico servicoMedico;
	@Autowired
	private InterfaceServicoPlanoDeSaude servicoPlanoDeSaude;
	@Autowired
	private InterfaceServicoCarteira servicoCarteira;
	@Autowired
	private InterfaceServicoPix servicoPix;
	@Autowired
	private InterfaceServicoDeposito servicoDeposito;
	@Autowired
	private InterfaceServicoSaque servicoSaque;

	// --- Paciente ---

	public List<Paciente> buscarPacientes() {
		return servicoPaciente.buscarTodos();
	}

	public Paciente buscarPacientesPorEmail(String email) throws UsuarioInexistenteException {
		return servicoPaciente.buscarPorEmail(email);
	}

	public Paciente buscarPacientePorId(long id) {
		return servicoPaciente.buscarPorId(id);
	}

	public Paciente salvarPaciente(Paciente paciente) {
		return servicoPaciente.salvar(paciente);
	}

	public void removerPaciente(long id) {
		servicoPaciente.remover(id);
	}

	// --- Medico ---

	public List<Medico> buscarMedicos() {
		return servicoMedico.buscarTodos();
	}

	public Medico buscarMedicoPorId(long id) {
		return servicoMedico.buscarPorId(id);
	}

	public List<Medico> buscarMedicoPorNome(String nome) {
		return servicoMedico.buscarPorNome(nome);
	}

	public Medico buscarMedicoPorEmail(String email) throws UsuarioInexistenteException {
		return servicoMedico.buscarPorEmail(email);
	}

	public Medico salvarMedico(Medico medico) {
		return servicoMedico.salvar(medico);
	}

	public void removerMedico(long id) {
		servicoMedico.remover(id);
	}

	// --- Plano de Saude ---

	public List<PlanoDeSaude> buscarPlanosDeSaude() {
		return servicoPlanoDeSaude.buscarTodos();
	}

	public PlanoDeSaude buscarPlanoDeSaudePorNumero(int numero) {
		return servicoPlanoDeSaude.buscarPorNumero(numero);
	}

	public PlanoDeSaude buscarPlanoDeSaudePorId(long id) {
		return servicoPlanoDeSaude.buscarPorId(id);
	}

	public PlanoDeSaude salvarPlanoDeSaude(PlanoDeSaude planoDeSaude) {
		return servicoPlanoDeSaude.salvar(planoDeSaude);
	}

	public void removerPlanosDeSaude(long id) {
		servicoPlanoDeSaude.remover(id);
	}

	// --- Carteira ---

	public List<Carteira> buscarCarteiras() {
		return servicoCarteira.buscarTodos();
	}

	public Carteira buscarCarteiraPorId(long id) {
		return servicoCarteira.buscarPorId(id);
	}

	public Carteira salvarCarteira(Carteira carteira) {
		return servicoCarteira.salvar(carteira);
	}

	public void removerCarteira(long id) {
		servicoCarteira.remover(id);
	}

	// --- Pix ---

	public List<Pix> buscarPixs() {
		return servicoPix.buscarTodos();
	}

	public Pix buscarPixPorId(long id) {
		return servicoPix.buscarPorId(id);
	}

	public Pix salvarPix(Pix pix) {
		return servicoPix.salvar(pix);
	}

	public void removerPix(long id) {
		servicoPix.remover(id);
	}

	// --- Deposito ---

	public List<Deposito> buscarDepositos() {
		return servicoDeposito.buscarTodos();
	}

	public List<Deposito> buscarDepositoPorData(Date data) {
		return servicoDeposito.buscarPorData(data);
	}

	public Deposito buscarDepositoPorId(long id) {
		return servicoDeposito.buscarPorId(id);
	}

	public Deposito salvarDeposito(Deposito deposito) {
		return servicoDeposito.salvar(deposito);
	}

	public void removerDeposito(long id) {
		servicoDeposito.remover(id);
	}

	// --- Saque ---

	public List<Saque> buscarSaques() {
		return servicoSaque.buscarTodos();
	}

	public List<Saque> buscarSaquePorData(Date data) {
		return servicoSaque.buscarPorData(data);
	}

	public Saque buscarSaquePorId(long id) {
		return servicoSaque.buscarPorId(id);
	}

	public Saque salvarSaque(Saque saque) {
		return servicoSaque.salvar(saque);
	}

	public void removerSaque(long id) {
		servicoSaque.remover(id);
	}

}
