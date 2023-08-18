package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CpfExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CrmInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EmailExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EspecialidadeInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioNaoEncontradoException;

@Service
public class ServicoMedico implements InterfaceServicoMedico {
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;

	public List<Medico> buscarTodos() {
		return colecaoMedico.findAll();
	}

	public Medico buscarPorId(long id) throws UsuarioNaoEncontradoException {
		Medico m = colecaoMedico.findById(id).orElse(null);
		if (m == null)
			throw new UsuarioNaoEncontradoException(id, "medico");
		return m;
	}

	public List<Medico> buscarPorNome(String nome) {
		return colecaoMedico.findByNome(nome);
	}
	
	public Medico buscarPorCpf(String cpf) throws UsuarioInexistenteException {
		Medico m = colecaoMedico.findByCpf(cpf);
		if (m == null)
			throw new UsuarioInexistenteException(cpf);
		return m;
	}

	public Medico buscarPorEmail(String email) throws UsuarioInexistenteException {
		Medico m = colecaoMedico.findByEmail(email);
		if (m == null)
			throw new UsuarioInexistenteException(email);
		return m;
	}

	public List<Medico> buscarPorEspecialidade(String nomeEspecialidade) throws EspecialidadeInexistenteException {
		List<Medico> m = colecaoMedico.findByEspecialidadesNome(nomeEspecialidade);
		if (m.isEmpty()) {
			throw new EspecialidadeInexistenteException(nomeEspecialidade);
		}
		return m;
	}

	public Medico buscarPorCrm(int numeroCrm) throws CrmInexistenteException {
		Medico m = colecaoMedico.findByCrmNumero(numeroCrm);
		if (m == null)
			throw new CrmInexistenteException(numeroCrm);
		return m;
	}

	public Medico salvar(Medico medico) throws EmailExistenteException, CpfExistenteException {
		// Verifica se o email já está em uso
		Medico medicoExistenteByEmail = colecaoMedico.findByEmail(medico.getEmail());
		if (medicoExistenteByEmail != null && medico.getEmail() != medicoExistenteByEmail.getEmail())
			throw new EmailExistenteException(medico.getEmail());

		// Verifica se o cpf já está em uso
		Medico medicoExistenteByCpf = colecaoMedico.findByCpf(medico.getCpf());
		if (medicoExistenteByCpf != null && medico.getCpf() != medicoExistenteByCpf.getCpf())
			throw new CpfExistenteException(medico.getCpf());

		return colecaoMedico.save(medico);
	}
	
	/* 	Verifica se o crm já está em uso
		
		List<CRM> crms = medico.getCrm();
		for (CRM crm : crms) {
			Medico medicoExistenteByCrm = colecaoMedico.findByCrmNumero(crm.getNumero());
			if (medicoExistenteByCrm != null)
				throw new CrmExistenteException(crm.getNumero());
		}
	*/

	public void remover(long id) throws UsuarioNaoEncontradoException {
		Medico m = buscarPorId(id);
		if (m == null)
			throw new UsuarioNaoEncontradoException(id, "medico");
		colecaoMedico.deleteById(m.getId());
	}

}