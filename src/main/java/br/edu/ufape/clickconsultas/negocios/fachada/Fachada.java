package br.edu.ufape.clickconsultas.negocios.fachada;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.negocios.modelo.*;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.*;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.*;
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
	private InterfaceServicoHorarioAgendado servicoHorarioAgendado;
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

	public Usuario logar(String email, String senha) throws ObjetoNaoEncontradoException, SenhaIncorretaException {
		Usuario usuario = servicoUsuario.logar(email, senha);
		return usuario;
	}

	// --- Paciente ---
	
	public Paciente logarPaciente(String email, String senha) throws ObjetoNaoEncontradoException, SenhaIncorretaException  {
		Usuario usuario = logar(email, senha);
		return buscarPacientePorId(usuario.getId());
	}

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

	public Medico logarMedico(String email, String senha) throws ObjetoNaoEncontradoException, SenhaIncorretaException {
		Usuario usuario = logar(email, senha);
		return buscarMedicoPorId(usuario.getId());
	}
	
	public Medico buscarMedicoPorId(long medicoId) throws ObjetoNaoEncontradoException {
		return servicoMedico.buscarPorId(medicoId);
	}

	public Medico cadastrarMedico(Medico medico) throws ObjetoEmUsoException {
		medico.setCarteira(new Carteira());
		RegistroAvaliacao registro = new RegistroAvaliacao();

		medico.setRegistroAvaliacao(registro);
		salvarRegistroAvaliacao(registro);

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

	public List<CRM> buscarCrms(long medicoId) throws ObjetoNaoEncontradoException, ListaVaziaException {
		return servicoMedico.buscarCrms(medicoId);
	}

	public CRM buscarCrmPorId(long medicoId, Long crmId) throws ObjetoNaoEncontradoException, ListaVaziaException {
		return servicoMedico.buscarCrmPorId(medicoId, crmId);
	}

	public List<CRM> salvarCrm(long medicoId, CRM crm) throws ObjetoNaoEncontradoException, ObjetoEmUsoException {
		return servicoMedico.salvarCrm(medicoId, crm);
	}

	public void removerCrm(long medicoId, long crmId)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, ListaVaziaException {
		servicoMedico.removerCrm(medicoId, crmId);
	}

	// --- Especialidade ---

	public List<Especialidade> buscarEspecialidades(long medicoId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		return servicoMedico.buscarEspecialidades(medicoId);
	}

	public Especialidade buscarEspecialidadePorId(long medicoId, Long especialidadeId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		return servicoMedico.buscarEspecialidadePorId(medicoId, especialidadeId);
	}

	public List<Especialidade> salvarEspecialidade(long medicoId, Especialidade especialidade)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, EspecialidadesExcedidasException,
			ListaVaziaException {
		return servicoMedico.salvarEspecialidade(medicoId, especialidade);
	}

	public void removerEspecialidade(long medicoId, Long especialidadeId)
			throws ObjetoNaoEncontradoException, ObjetoEmUsoException, ListaVaziaException {
		servicoMedico.removerEspecialidade(medicoId, especialidadeId);
	}

	// --- EnderecoMedico ---

	public List<EnderecoMedico> buscarEnderecosMedicos(long medicoId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		return servicoMedico.buscarEnderecos(medicoId);
	}

	public EnderecoMedico buscarEnderecoMedicoPorId(long medicoId, long enderecoId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		return servicoMedico.buscarEnderecoPorId(medicoId, enderecoId);
	}

	public List<EnderecoMedico> salvarEnderecoMedico(long medicoId, EnderecoMedico enderecoMedico)
			throws ObjetoNaoEncontradoException {
		return servicoMedico.salvarEndereco(medicoId, enderecoMedico);
	}

	public void removerEnderecoMedico(long medicoId, long enderecoId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		servicoMedico.removerEndereco(medicoId, enderecoId);
	}

	// --- Carteira ---

	public Carteira buscarCarteiraPorUsuarioId(long usuarioId) throws ObjetoNaoEncontradoException {
		return servicoUsuario.buscarCarteiraPorUsuarioId(usuarioId);
	}

	public Carteira salvarCarteira(long usuarioId, Carteira carteira) throws ObjetoNaoEncontradoException {
		return servicoUsuario.salvarCarteira(usuarioId, carteira);
	}

	// --- Pix ---

	public List<Pix> buscarPixsCarteiraPorUsuarioId(long usuarioId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		return servicoUsuario.buscarPixsPorUsuarioId(usuarioId);
	}

	public Pix buscarPixCarteiraPorId(long usuarioId, long pixId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		return servicoUsuario.buscarPixPorId(usuarioId, pixId);
	}

	public List<Pix> salvarPixCarteira(long usuarioId, Pix pix) throws ObjetoNaoEncontradoException, ObjetoEmUsoException {
		return servicoUsuario.salvarPixCarteira(usuarioId, pix);
	}

	public void removerPixCarteira(long usuarioId, long pixId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
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

	public Deposito salvarDeposito(long usuarioId, Deposito deposito) throws Exception {
		Carteira c = buscarCarteiraPorUsuarioId(usuarioId);
		deposito.setCarteira(c);

		deposito.processarTransacao();
		servicoUsuario.salvarCarteira(usuarioId, c);

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

	public Saque salvarSaque(long usuarioId, Saque saque) throws Exception {
		Carteira c = buscarCarteiraPorUsuarioId(usuarioId);
		saque.setCarteira(c);
		Pix p = buscarPixCarteiraPorId(usuarioId, saque.getChavePix().getId());
		saque.setChavePix(p);

		saque.processarTransacao();
		servicoUsuario.salvarCarteira(usuarioId, c);

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

	public List<Agenda> buscarAgendasPorMedicoId(long medicoId) throws ObjetoNaoEncontradoException {
		return servicoAgenda.buscarPorIdMedico(medicoId);
	}

	public Agenda salvarAgenda(Agenda agenda, long medicoId) throws ObjetoNaoEncontradoException {
		Medico medico = servicoMedico.buscarPorId(medicoId);
		agenda.setMedico(medico);

		return servicoAgenda.salvar(agenda);
	}

	public void removerAgenda(long id) throws ObjetoNaoEncontradoException {
		servicoAgenda.remover(id);
	}

	public List<Horarios> buscarHorariosPorAgendaId(long id) throws ObjetoNaoEncontradoException {
		Agenda agenda = servicoAgenda.buscarPorId(id);
		return agenda.getHorariosDisponiveis();
	}

	public Agenda salvarHorarios(long agendaId, Horarios horario) throws ObjetoNaoEncontradoException {
		Agenda agenda = servicoAgenda.buscarPorId(agendaId);
		List<Horarios> listaAgenda = agenda.getHorariosDisponiveis();
		listaAgenda.add(horario);
		return servicoAgenda.salvar(agenda);
	}

	public void removerHorarios(long agendaId, Horarios horario) throws ObjetoNaoEncontradoException {
		Agenda agenda = servicoAgenda.buscarPorId(agendaId);
		List<Horarios> listaAgenda = agenda.getHorariosDisponiveis();
		int indexHorarioRemovido = agenda.getHorariosDisponiveis().indexOf(horario);
		listaAgenda.remove(indexHorarioRemovido);
		agenda.setHorariosDisponiveis(listaAgenda);
		servicoAgenda.salvar(agenda);
	}

	public Agenda editarHorarios(long agendaId, long horarioId, Horarios novoHorario)
			throws ObjetoNaoEncontradoException {
		Agenda agenda = servicoAgenda.buscarPorId(agendaId);
		List<Horarios> horarios = agenda.getHorariosDisponiveis();
		for (int i = 0; i < horarios.size(); i++) {
			if (horarios.get(i).getId() == horarioId) {
				Horarios horarioExistente = horarios.get(i);
				novoHorario.setId(horarioExistente.getId());
				horarios.set(i, novoHorario);
			}
		}
		agenda.setHorariosDisponiveis(horarios);
		return servicoAgenda.salvar(agenda);
	}

	// --- Agendamento ---

	public List<Agendamento> buscarAgendamentos() {
		return servicoAgendamento.buscarTodos();
	}

	public Agendamento buscarAgendamentoPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoAgendamento.buscarPorId(id);
	}

	public List<Agendamento> buscarAgendamentoPorPacienteId(long pacienteId) throws ObjetoNaoEncontradoException {
		return servicoAgendamento.buscarPorPacienteId(pacienteId);
	}

	public Agendamento salvarAgendamento(Agendamento agendamento)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		Paciente p = buscarPacientePorId(agendamento.getPaciente().getId());
		agendamento.setPaciente(p);
		Agenda a = buscarAgendaPorId(agendamento.getAgenda().getId());
		agendamento.setAgenda(a);
		EnderecoMedico end = buscarEnderecoMedicoPorId(a.getMedico().getId(), agendamento.getLocalConsulta().getId());
		agendamento.setLocalConsulta(end);
		return servicoAgendamento.salvar(agendamento);
	}

	public void removerAgendamento(long id) throws ObjetoNaoEncontradoException {
		servicoAgendamento.remover(id);
	}

	// --- Consulta ---

	public List<Consulta> buscarConsultas() {
		return servicoConsulta.buscarTodos();
	}

	public Consulta buscarConsultaPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoConsulta.buscarPorId(id);
	}

	public List<Consulta> buscarConsultaPorPacienteId(long pacienteId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		return servicoConsulta.buscarPorPacienteId(pacienteId);
	}

	public List<Consulta> buscarConsultaPorMedicoId(long medicoId)
			throws ObjetoNaoEncontradoException, ListaVaziaException {
		return servicoConsulta.buscarPorMedicoId(medicoId);
	}

	public Consulta salvarConsulta(Consulta consulta) throws ObjetoNaoEncontradoException {
		Medico m = buscarMedicoPorId(consulta.getMedico().getId());
		consulta.setMedico(m);
		Paciente p = buscarPacientePorId(consulta.getPaciente().getId());
		consulta.setPaciente(p);
		Agendamento a = buscarAgendamentoPorId(consulta.getAgendamento().getId());
		consulta.setAgendamento(a);
		return servicoConsulta.salvar(consulta);
	}

	public void removerConsulta(long id) throws ObjetoNaoEncontradoException {
		servicoConsulta.remover(id);
	}

	// --- HorarioAgendado ---

	public List<HorarioAgendado> buscarHorariosAgendados() {
		return servicoHorarioAgendado.buscarTodos();
	}

	public List<HorarioAgendado> buscarHorarioAgendadoPorData(LocalDate data) throws ObjetoNaoEncontradoException {
		return servicoHorarioAgendado.buscarPorData(data);
	}

	public HorarioAgendado buscarHorarioAgendadoPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoHorarioAgendado.buscarPorId(id);
	}

	public HorarioAgendado buscarHorarioAgendadoPorAgendamentoId(long agendamentoId)
			throws ObjetoNaoEncontradoException {
		Agendamento a = buscarAgendamentoPorId(agendamentoId);
		return servicoHorarioAgendado.buscarPorId(a.getHorarioAgendado().getId());
	}

	public HorarioAgendado salvarHorarioAgendado(HorarioAgendado horarioAgendado) {
		return servicoHorarioAgendado.salvar(horarioAgendado);
	}

	public void removerHorarioAgendado(long id) throws ObjetoNaoEncontradoException {
		servicoHorarioAgendado.remover(id);
	}

	// --- Avaliacao ---

	public List<Avaliacao> buscarAvaliacoes() {
		return servicoAvaliacao.buscarTodos();
	}

	public Avaliacao buscarAvaliacaoPorId(long id) throws ObjetoNaoEncontradoException {
		return servicoAvaliacao.buscarPorId(id);
	}

	public List<Avaliacao> buscarAvaliacoesPorPacienteId(long pacienteId) {
		return servicoAvaliacao.buscarPorPacienteId(pacienteId);
	}

	public Avaliacao salvarAvaliacao(Avaliacao avaliacao)
			throws ObjetoNaoEncontradoException, NotaDeAvaliacaoInvalidaException {
		Paciente paciente = buscarPacientePorId(avaliacao.getPaciente().getId());
		RegistroAvaliacao registro = buscarRegistroAvaliacaoPorId(avaliacao.getRegistro().getId());
		avaliacao.setPaciente(paciente);
		avaliacao.setRegistro(registro);

		registro.adicionarNota(avaliacao.getNota());
		servicoRegistroAvaliacao.salvar(registro);

		return servicoAvaliacao.salvar(avaliacao);
	}

	public void removerAvaliacao(long id) throws ObjetoNaoEncontradoException {
		Avaliacao avaliacao = buscarAvaliacaoPorId(id);
		RegistroAvaliacao registro = buscarRegistroAvaliacaoPorId(avaliacao.getRegistro().getId());

		registro.removerNota(avaliacao.getNota());
		servicoRegistroAvaliacao.salvar(registro);

		servicoAvaliacao.remover(id);
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