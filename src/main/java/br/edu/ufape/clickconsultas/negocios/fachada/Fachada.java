package br.edu.ufape.clickconsultas.negocios.fachada;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.negocios.modelo.Agenda;
import br.edu.ufape.clickconsultas.negocios.modelo.Agendamento;
import br.edu.ufape.clickconsultas.negocios.modelo.Avaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.Consulta;
import br.edu.ufape.clickconsultas.negocios.modelo.EnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.HorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.modelo.Horarios;
import br.edu.ufape.clickconsultas.negocios.modelo.RegistroAvaliacao;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Carteira;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Deposito;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Pix;
import br.edu.ufape.clickconsultas.negocios.modelo.financeiro.Saque;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Paciente;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.PlanoDeSaude;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoAgenda;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoAgendamento;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoAvaliacao;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoConsulta;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoEnderecoMedico;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoHorarioAgendado;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoHorarios;
import br.edu.ufape.clickconsultas.negocios.servico.InterfaceServicoRegistroAvaliacao;
import br.edu.ufape.clickconsultas.negocios.servico.exception.AgendaInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.AgendamentoInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ChavePixInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CpfExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CrmExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CrmInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.DadosInsuficientesException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EmailExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EspecialidadeInexistenteException;
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

	public Paciente buscarPacientesPorEmail(String email) throws UsuarioInexistenteException {
		return servicoPaciente.buscarPorEmail(email);
	}

	public Paciente buscarPacientePorId(long id) throws UsuarioInexistenteException {
		return servicoPaciente.buscarPorId(id);
	}

	public Paciente salvarPaciente(Paciente paciente) throws EmailExistenteException, CpfExistenteException {
		return servicoPaciente.salvar(paciente);
	}

	public void removerPaciente(long id) {
		servicoPaciente.remover(id);
	}

	// --- Medico ---

	public List<Medico> buscarMedicos() {
		return servicoMedico.buscarTodos();
	}

	public Medico buscarMedicoPorId(long id) throws UsuarioInexistenteException{
		return servicoMedico.buscarPorId(id);
	}

	public List<Medico> buscarMedicoPorNome(String nome) {
		return servicoMedico.buscarPorNome(nome);
	}

	public Medico buscarMedicoPorEmail(String email) throws UsuarioInexistenteException {
		return servicoMedico.buscarPorEmail(email);
	}
	
	public List<Medico> buscarMedicosPorEspecialiade(String nomeEspecialidade) throws EspecialidadeInexistenteException{
		return servicoMedico.buscarPorEspecialidade(nomeEspecialidade);
	}
	
	public Medico buscarMedicoPorCrm(int numeroCrm) throws CrmInexistenteException {
		return servicoMedico.buscarPorCrm(numeroCrm);
	}
 
	public Medico salvarMedico(Medico medico) throws EmailExistenteException, CrmExistenteException, CpfExistenteException {
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

	public void removerPix(long id) throws ChavePixInexistenteException {
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

	// --- Agenda ---
	

	public List<Agenda> buscarAgendas() {
		return servicoAgenda.buscarTodos();
	}

	public Agenda buscarAgendaPorId(long id) throws AgendaInexistenteException {
		return servicoAgenda.buscarPorId(id);
	}

	public Agenda salvarAgenda(Agenda agenda) throws DadosInsuficientesException {
		return servicoAgenda.salvar(agenda);
	}

	public void removerAgenda(long id) throws AgendaInexistenteException {
		servicoAgenda.remover(id);
	}

	// --- Agendamento ---
	

	public List<Agendamento> buscarAgendamentos() {
		return servicoAgendamento.buscarTodos();
	}

	public Agendamento buscarAgendamentoPorId(long id) throws AgendamentoInexistenteException {
		return servicoAgendamento.buscarPorId(id);
	}

	public Agendamento salvarAgendamento(Agendamento agendamento) throws DadosInsuficientesException {
		return servicoAgendamento.salvar(agendamento);
	}

	public void removerAgendamento(long id) throws AgendamentoInexistenteException {
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

	// --- HorarioAgendado
	
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

	
	// ---RegistroAvaliacao
	
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
