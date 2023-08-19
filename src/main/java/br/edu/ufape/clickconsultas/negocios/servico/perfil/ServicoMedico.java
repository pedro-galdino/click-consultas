package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.h2.expression.function.ToCharFunction.Capitalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Especialidade;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Usuario;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoEmUsoException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.ObjetoNaoEncontradoException;

@Service
public class ServicoMedico implements InterfaceServicoMedico {
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;

	public List<Medico> buscarTodos() {
		return colecaoMedico.findAll();
	}

	public Medico buscarPorId(long id) throws ObjetoNaoEncontradoException {
		Medico m = colecaoMedico.findById(id).orElse(null);
		if (m == null)
			throw new ObjetoNaoEncontradoException("o", "medico");
		return m;
	}

	public List<Medico> buscarPorNome(String nome) {
		return colecaoMedico.findByNome(Usuario.formatar(Usuario.capitalizarNome(nome)));
	}

	public Medico buscarPorCpf(String cpf) throws ObjetoNaoEncontradoException {
		Medico m = colecaoMedico.findByCpf(cpf);
		if (m == null)
			throw new ObjetoNaoEncontradoException("o", "medico");
		return m;
	}

	public Medico buscarPorEmail(String email) throws ObjetoNaoEncontradoException {
		Medico m = colecaoMedico.findByEmail(Medico.formatar(email).toLowerCase());
		if (m == null)
			throw new ObjetoNaoEncontradoException("o", "medico");
		return m;
	}

	public List<Medico> buscarPorEspecialidade(String nomeEspecialidade) throws ObjetoNaoEncontradoException {
		nomeEspecialidade = Especialidade.formatar(Especialidade.capitalizarNome(nomeEspecialidade));
		List<Medico> m = colecaoMedico.findByEspecialidadesNome(nomeEspecialidade);
		if (m.isEmpty()) {
			throw new ObjetoNaoEncontradoException("a", "especialidade");
		}
		return m;
	}

	public Medico buscarPorCrm(int numeroCrm) throws ObjetoNaoEncontradoException {
		Medico m = colecaoMedico.findByCrmNumero(numeroCrm);
		if (m == null)
			throw new ObjetoNaoEncontradoException("o", "medico");
		return m;
	}

	public Medico salvar(Medico medico) throws ObjetoEmUsoException {
		
		//Verifica se o CRM já está em uso
		List<CRM> listaCRM = medico.getCrm();
		if(listaCRM != null && !listaCRM.isEmpty()) {
			for(CRM crm : listaCRM) {
				Medico medicoExistenteByCRM = colecaoMedico.findByCrmNumero(crm.getNumero());
				if(medicoExistenteByCRM != null)
					throw new ObjetoEmUsoException("o", "CRM");

			}
		}
		
		// Verifica se o email já está em uso
		Medico medicoExistenteByEmail = colecaoMedico.findByEmail(medico.getEmail());
		if (medicoExistenteByEmail != null)
			throw new ObjetoEmUsoException("o", "e-mail");

		// Verifica se o cpf já está em uso
		Medico medicoExistenteByCpf = colecaoMedico.findByCpf(medico.getCpf());
		if (medicoExistenteByCpf != null)
			throw new ObjetoEmUsoException("o", "CPF");

		return colecaoMedico.save(medico);
	}

	public void remover(long id) throws ObjetoNaoEncontradoException {
		Medico m = buscarPorId(id);
		if (m == null)
			throw new ObjetoNaoEncontradoException("o", "medico");
		colecaoMedico.deleteById(m.getId());
	}

}