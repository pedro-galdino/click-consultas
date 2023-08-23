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
	private InterfaceServicoUsuario servicoUsuario;
	@Autowired
	private InterfaceServicoPaciente servicoPaciente;
	@Autowired
	private InterfaceServicoMedico servicoMedico;
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

	// --- Usuario ---

	public List<Usuario> buscarUsuarios() {
		return servicoUsuario.buscarTodos();
	}

	public Usuario buscarUsuarioPorCpf(String cpf) throws ObjetoNaoEncontradoException {
		return servicoUsuario.buscarPorCpf(cpf);
	}

	public Usuario buscarUsuarioPorEmail(String email) throws ObjetoNaoEncontradoException {
		return servicoUsuario.buscarPorEmail(email);
	}

	public Usuario buscarUsuarioPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoUsuario.buscarPorId(id);
	}

	public Usuario salvarUsuario(Usuario usuario) throws ObjetoEmUsoException {
		return servicoUsuario.salvar(usuario);
	}

	public void removerUsuario(long id) throws ObjetoNaoEncontradoException {
		servicoUsuario.remover(id);
	}

	// --- Paciente ---

	public Paciente buscarPacientePorId(long pacienteId) throws ObjetoNaoEncontradoException {
		return servicoPaciente.buscarPorId(pacienteId);
	}

	public Paciente cadastrarPaciente(Paciente paciente) throws ObjetoEmUsoException {
		paciente.setCarteira(new Carteira());
		return (Paciente) servicoUsuario.salvar(paciente);
	}

	public Paciente salvarPaciente(Paciente paciente) {
		return servicoPaciente.salvar(paciente);
	}

	// --- Plano de Saúde ---
	
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

	public Medico buscarMedicoPorId(long medicoId) throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarPorId(medicoId);
	}

	public Medico cadastrarMedico(Medico medico) throws ObjetoEmUsoException {
		medico.setCarteira(new Carteira());
		return (Medico) servicoUsuario.salvar(medico);
	}

	public Medico salvarMedico(Medico medico) {
		return servicoMedico.salvar(medico);
	}

	public List<Medico> buscarMedicosPorNomeDaEspecialidade(String nomeEspecialidade)
			throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarPorNomeEspecialidade(nomeEspecialidade);
	}

	public Medico buscarMedicoPorEspecialidade(String nome, int numeroRQE) throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarPorEspecialidade(nome, numeroRQE);
	}
	
	// --- CRM ---

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
	
	// --- Especialidade ---

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
	
	public Carteira buscarCarteiraPorUsuarioId(long usuarioId) throws ObjetoNaoEncontradoException {
		return servicoUsuario.buscarCarteiraPorUsuarioId(usuarioId);
	}
	
	public Carteira salvarCarteira(long usuarioId, Carteira carteira) throws ObjetoNaoEncontradoException {
		return servicoUsuario.salvarCarteira(usuarioId, carteira);
	}
	
	// --- Pix ---

	public Pix buscarPixCarteiraPorId(long usuarioId, long pixId) throws ObjetoNaoEncontradoException {
		return servicoUsuario.buscarPixPorId(usuarioId, pixId);
	}

	public List<Pix> salvarPixCarteira(long usuarioId, Pix pix) throws ObjetoNaoEncontradoException {
		return servicoUsuario.salvarPixCarteira(usuarioId, pix);
	}

	public void removerPixCarteira(long usuarioId, long pixId) throws ObjetoNaoEncontradoException {
		servicoUsuario.removerPixCarteira(usuarioId, pixId);
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

	public List<Deposito> buscarDepositosPorCarteiraId(long carteiraId) throws ObjetoNaoEncontradoException {
		return servicoDeposito.buscarPorCarteiraId(carteiraId);
	}

	public Deposito salvarDeposito(long usuarioId, Deposito deposito) throws ObjetoNaoEncontradoException {
		Carteira c = buscarCarteiraPorUsuarioId(usuarioId);
		deposito.setCarteira(c);
		return servicoDeposito.salvar(deposito);
	}

	public void removerDeposito(long depositoId) throws ObjetoNaoEncontradoException {
		servicoDeposito.remover(depositoId);
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

	public List<Saque> buscarSaquesPorCarteiraId(long carteiraId) throws ObjetoNaoEncontradoException {
		return servicoSaque.buscarPorCarteiraId(carteiraId);
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

	public Avaliacao buscarAvaliacaoPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoAvaliacao.buscarPorId(id);
	}

	public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
		return servicoAvaliacao.salvar(avaliacao);
	}

	public void removerAvaliacao(long id) throws ObjetoNaoEncontradoException {
		servicoAvaliacao.remover(id);
	}

	// --- Consulta ---

	public List<Consulta> buscarConsultas() {
		return servicoConsulta.buscarTodos();
	}

	public Consulta buscarConsultaPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoConsulta.buscarPorId(id);
	}

	public Consulta salvarConsulta(Consulta consulta) {
		return servicoConsulta.salvar(consulta);
	}

	public void removerConsulta(long id) throws ObjetoNaoEncontradoException {
		servicoConsulta.remover(id);
	}

	// --- EnderecoMedico ---

	public List<EnderecoMedico> buscarEnderecosMedicos() {
		return servicoEnderecoMedico.buscarTodos();
	}

	public EnderecoMedico buscarEnderecoMedicoPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoEnderecoMedico.buscarPorId(id);
	}

	public EnderecoMedico salvarEnderecoMedico(EnderecoMedico enderecoMedico) {
		return servicoEnderecoMedico.salvar(enderecoMedico);
	}

	public void removerEnderecoMedico(long id) throws ObjetoNaoEncontradoException {
		servicoEnderecoMedico.remover(id);
	}

	// --- HorarioAgendado ---

	public List<HorarioAgendado> buscarHorariosAgendados() {
		return servicoHorarioAgendado.buscarTodos();
	}

	public List<HorarioAgendado> BuscarHorarioAgendadoPorData(LocalDate data) throws ObjetoNaoEncontradoException {
		return servicoHorarioAgendado.BuscarPorData(data);
	}

	public HorarioAgendado buscarHorarioAgendadoPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoHorarioAgendado.buscarPorId(id);
	}

	public HorarioAgendado salvarHorarioAgendado(HorarioAgendado horarioAgendado) {
		return servicoHorarioAgendado.salvar(horarioAgendado);
	}

	public void removerHorarioAgendado(long id) throws ObjetoNaoEncontradoException {
		servicoHorarioAgendado.remover(id);
	}

	// --- Horario ---

	public List<Horarios> buscarHorarios() {
		return servicoHorarios.buscarTodos();
	}

	public List<Horarios> buscarHorariosPorData(LocalDate data) throws ObjetoNaoEncontradoException {
		return servicoHorarios.buscarPorData(data);
	}

	public Horarios buscarHorarioPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoHorarios.buscarPorId(id);
	}

	public Horarios salvarHorario(Horarios horario) {
		return servicoHorarios.salvar(horario);
	}

	public void removerHorario(long id) throws ObjetoNaoEncontradoException {
		servicoHorarios.remover(id);
	}

	// --- RegistroAvaliacao ---

	public List<RegistroAvaliacao> buscarRegistroAvaliacao() {
		return servicoRegistroAvaliacao.buscarTodos();
	}

	public RegistroAvaliacao buscarRegistroAvaliacaoPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoRegistroAvaliacao.buscarPorId(id);
	}

	public RegistroAvaliacao salvarRegistroAvaliacao(RegistroAvaliacao registroAvaliacao) {
		return servicoRegistroAvaliacao.salvar(registroAvaliacao);
	}

	public void removerRegistroAvaliacao(long id) throws ObjetoNaoEncontradoException {
		servicoRegistroAvaliacao.remover(id);
	}

}