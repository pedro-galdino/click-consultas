package br.edu.ufape.clickconsultas.negocios.fachada;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.negocios.modelo.*;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.*;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.*;
import br.edu.ufape.clickconsultas.negocios.servico.*;
import br.edu.ufape.clickconsultas.negocios.servico.exception.*;
import br.edu.ufape.clickconsultas.negocios.servico.financeiro.*;
import br.edu.ufape.clickconsultas.negocios.servico.perfil.*;

@Service
public class Fachada {
	@Autowired
	private InterfaceServicoPaciente servicoPaciente;
	@Autowired
	private InterfaceServicoMedico servicoMedico;
	@Autowired
	private InterfaceServicoCarteira servicoCarteira;
	@Autowired
	private InterfaceServicoPix servicoPix;
	@Autowired
	private InterfaceServicoDeposito servicoDeposito;
	@Autowired
	private InterfaceServicoSaque servicoSaque;
	@Autowired
	private InterfaceServicoAgenda servicoAgenda;
	@Autowired
	private InterfaceServicoAgendamento servicoAgendamento;
	@Autowired
	private InterfaceServicoAvaliacao servicoAvaliacao;
	@Autowired
	private InterfaceServicoConsulta servicoConsulta;
	@Autowired
	private InterfaceServicoEnderecoMedico servicoEnderecoMedico;
	@Autowired
	private InterfaceServicoHorarioAgendado servicoHorarioAgendado;
	@Autowired
	private InterfaceServicoHorarios servicoHorarios;
	@Autowired
	private InterfaceServicoRegistroAvaliacao servicoRegistroAvaliacao;

	// --- Paciente ---

	public List<Paciente> buscarPacientes() {
		return servicoPaciente.buscarTodos();
	}

	public Paciente buscarPacientePorCpf(String cpf) throws ObjetoNaoEncontradoException {
		return servicoPaciente.buscarPorCpf(cpf);
	}

	public Paciente buscarPacientesPorEmail(String email) throws ObjetoNaoEncontradoException {
		return servicoPaciente.buscarPorEmail(email);
	}

	public Paciente buscarPacientePorId(long id) throws ObjetoNaoEncontradoException {
		return servicoPaciente.buscarPorId(id);
	}

	public Paciente cadastrarPaciente(Paciente paciente) throws ObjetoEmUsoException {
		salvarCarteira(paciente.getCarteira());
		return servicoPaciente.salvar(paciente);
	}
	
	public Paciente salvarPaciente(Paciente paciente) throws ObjetoEmUsoException {
		return servicoPaciente.salvar(paciente);
	}

	public void removerPaciente(long id) throws ObjetoNaoEncontradoException {
		servicoPaciente.remover(id);
	}

	public PlanoDeSaude buscarPlanoDeSaude(long pacienteId) throws ObjetoNaoEncontradoException {
		return servicoPaciente.buscarPlanoDeSaude(pacienteId);
	}

	public PlanoDeSaude salvarPlanoDeSaude(long pacienteId, PlanoDeSaude plano) throws ObjetoNaoEncontradoException {
		return servicoPaciente.salvarPlanoDeSaude(pacienteId, plano);
	}

	public void removerPlanoDeSaude(long pacienteId) throws ObjetoNaoEncontradoException {
		servicoPaciente.removerPlanoDeSaude(pacienteId);
	}

	// --- Medico ---

	public List<Medico> buscarMedicos() {
		return servicoMedico.buscarTodos();
	}

	public Medico buscarMedicoPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarPorId(id);
	}

	public List<Medico> buscarMedicoPorNome(String nome) {
		return servicoMedico.buscarPorNome(nome);
	}

	public Medico buscarMedicoPorCpf(String cpf) throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarPorCpf(cpf);
	}

	public Medico buscarMedicoPorEmail(String email) throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarPorEmail(email);
	}

	public Medico buscarMedicoPorCrm(String uf, int numero) throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarPorCrm(uf, numero);
	}

	public List<Medico> buscarMedicosPorNomeDaEspecialidade(String nomeEspecialidade)
			throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarPorNomeEspecialidade(nomeEspecialidade);
	}

	public Medico buscarMedicoPorEspecialidade(String nome, int numeroRQE) throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarPorEspecialidade(nome, numeroRQE);
	}
	
	public Medico cadastrarMedico(Medico medico) throws ObjetoEmUsoException {
		salvarCarteira(medico.getCarteira());
		return servicoMedico.salvar(medico);
	}

	public Medico salvarMedico(Medico medico) throws ObjetoEmUsoException {
		return servicoMedico.salvar(medico);
	}

	public void removerMedico(long id) throws ObjetoNaoEncontradoException {
		servicoMedico.remover(id);
	}

	public List<CRM> buscarCrms(long medicoId) throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarCrms(medicoId);
	}

	public CRM buscarCrmPorId(long medicoId, Long crmId) throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarCrmPorId(medicoId, crmId);
	}

	public List<CRM> salvarCrm(long medicoId, CRM crm) throws ObjetoNaoEncontradoException, ObjetoEmUsoException {
		return servicoMedico.salvarCrm(medicoId, crm);
	}

	public void removerCrm(long medicoId, long crmId) throws ObjetoNaoEncontradoException, ObjetoEmUsoException {
		servicoMedico.removerCrm(medicoId, crmId);
	}

	public List<Especialidade> buscarEspecialidades(long medicoId) throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarEspecialidades(medicoId);
	}

	public Especialidade buscarEspecialidadePorId(long medicoId, Long especialidadeId)
			throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarEspecialidadePorId(medicoId, especialidadeId);
	}

	public List<Especialidade> salvarEspecialidade(long medicoId, Especialidade especialidade)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, EspecialidadesExcedidasException {
		return servicoMedico.salvarEspecialidade(medicoId, especialidade);
	}

	public void removerEspecialidade(long medicoId, Long especialidadeId)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException {
		servicoMedico.removerEspecialidade(medicoId, especialidadeId);
	}

	// --- Carteira ---

	public List<Carteira> buscarCarteiras() {
		return servicoCarteira.buscarTodos();
	}

	public Carteira buscarCarteiraPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoCarteira.buscarPorId(id);
	}

	public Carteira salvarCarteira(Carteira carteira) {
		return servicoCarteira.salvar(carteira);
	}

	public void removerCarteira(long carteiraId) throws ObjetoNaoEncontradoException {
		servicoCarteira.remover(carteiraId);
	}

	// --- Pix ---

	public List<Pix> buscarPixs() {
		return servicoPix.buscarTodos();
	}

	public Pix buscarPixPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoPix.buscarPorId(id);
	}

	public Pix salvarPix(Pix pix) {
		return servicoPix.salvar(pix);
	}

	public void removerPix(long id) throws ObjetoNaoEncontradoException {
		servicoPix.remover(id);
	}

	// --- Deposito ---

	public List<Deposito> buscarDepositos() {
		return servicoDeposito.buscarTodos();
	}

	public List<Deposito> buscarDepositoPorData(Date data) throws ObjetoNaoEncontradoException {
		return servicoDeposito.buscarPorData(data);
	}

	public Deposito buscarDepositoPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoDeposito.buscarPorId(id);
	}

	public Deposito salvarDeposito(Deposito deposito) {
		return servicoDeposito.salvar(deposito);
	}

	public void removerDeposito(long id) throws ObjetoNaoEncontradoException {
		servicoDeposito.remover(id);
	}

	// --- Saque ---

	public List<Saque> buscarSaques() {
		return servicoSaque.buscarTodos();
	}

	public List<Saque> buscarSaquePorData(Date data) throws ObjetoNaoEncontradoException {
		return servicoSaque.buscarPorData(data);
	}

	public Saque buscarSaquePorId(long id) throws ObjetoNaoEncontradoException {
		return servicoSaque.buscarPorId(id);
	}

	public Saque salvarSaque(Saque saque) {
		return servicoSaque.salvar(saque);
	}

	public void removerSaque(long id) throws ObjetoNaoEncontradoException {
		servicoSaque.remover(id);
	}

	// --- Agenda ---

	public List<Agenda> buscarAgendas() {
		return servicoAgenda.buscarTodos();
	}

	public Agenda buscarAgendaPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoAgenda.buscarPorId(id);
	}

	public Agenda salvarAgenda(Agenda agenda) throws DadosInsuficientesException {
		return servicoAgenda.salvar(agenda);
	}

	public void removerAgenda(long id) throws ObjetoNaoEncontradoException {
		servicoAgenda.remover(id);
	}

	// --- Agendamento ---

	public List<Agendamento> buscarAgendamentos() {
		return servicoAgendamento.buscarTodos();
	}

	public Agendamento buscarAgendamentoPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoAgendamento.buscarPorId(id);
	}

	public Agendamento salvarAgendamento(Agendamento agendamento) throws DadosInsuficientesException {
		return servicoAgendamento.salvar(agendamento);
	}

	public void removerAgendamento(long id) throws ObjetoNaoEncontradoException {
		servicoAgendamento.remover(id);
	}

	// --- Avaliacao ---

	public List<Avaliacao> buscarAvaliacoes() {
		return servicoAvaliacao.buscarTodos();
	}

	public Avaliacao buscarAvaliacaoPorId(long id) {
		return servicoAvaliacao.buscarPorId(id);
	}

	public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
		return servicoAvaliacao.salvar(avaliacao);
	}

	public void removerAvaliacao(long id) {
		servicoAvaliacao.remover(id);
	}

	// --- Consulta ---

	public List<Consulta> buscarConsultas() {
		return servicoConsulta.buscarTodos();
	}

	public Consulta buscarConsultaPorId(long id) {
		return servicoConsulta.buscarPorId(id);
	}

	public Consulta salvarConsulta(Consulta consulta) {
		return servicoConsulta.salvar(consulta);
	}

	public void removerConsulta(long id) {
		servicoConsulta.remover(id);
	}

	// --- EnderecoMedico ---

	public List<EnderecoMedico> buscarEnderecosMedicos() {
		return servicoEnderecoMedico.buscarTodos();
	}

	public EnderecoMedico buscarEnderecoMedicoPorId(long id) {
		return servicoEnderecoMedico.buscarPorId(id);
	}

	public EnderecoMedico salvarEnderecoMedico(EnderecoMedico enderecoMedico) {
		return servicoEnderecoMedico.salvar(enderecoMedico);
	}

	public void removerEnderecoMedico(long id) {
		servicoEnderecoMedico.remover(id);
	}

	// --- HorarioAgendado ---

	public List<HorarioAgendado> buscarHorariosAgendados() {
		return servicoHorarioAgendado.buscarTodos();
	}

	public List<HorarioAgendado> BuscarHorarioAgendadoPorData(LocalDate data) {
		return servicoHorarioAgendado.BuscarPorData(data);
	}

	public HorarioAgendado buscarHorarioAgendadoPorId(long id) {
		return servicoHorarioAgendado.buscarPorId(id);
	}

	public HorarioAgendado salvarHorarioAgendado(HorarioAgendado horarioAgendado) {
		return servicoHorarioAgendado.salvar(horarioAgendado);
	}

	public void removerHorarioAgendado(long id) {
		servicoHorarioAgendado.remover(id);
	}

	// --- Horario ---

	public List<Horarios> buscarHorarios() {
		return servicoHorarios.buscarTodos();
	}

	public List<Horarios> buscarHorariosPorData(LocalDate data) {
		return servicoHorarios.buscarPorData(data);
	}

	public Horarios buscarHorarioPorId(long id) {
		return servicoHorarios.buscarPorId(id);
	}

	public Horarios salvarHorario(Horarios horario) {
		return servicoHorarios.salvar(horario);
	}

	public void removerHorario(long id) {
		servicoHorarios.remover(id);
	}

	// --- RegistroAvaliacao ---

	public List<RegistroAvaliacao> buscarRegistroAvaliacao() {
		return servicoRegistroAvaliacao.buscarTodos();
	}

	public RegistroAvaliacao buscarRegistroAvaliacaoPorId(long id) {
		return servicoRegistroAvaliacao.buscarPorId(id);
	}

	public RegistroAvaliacao salvarRegistroAvaliacao(RegistroAvaliacao registroAvaliacao) {
		return servicoRegistroAvaliacao.salvar(registroAvaliacao);
	}

	public void removerRegistroAvaliacao(long id) {
		servicoRegistroAvaliacao.remover(id);
	}

}