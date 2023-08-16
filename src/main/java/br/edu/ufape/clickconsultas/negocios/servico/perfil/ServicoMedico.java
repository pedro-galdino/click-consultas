package br.edu.ufape.clickconsultas.negocios.servico.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.clickconsultas.dados.perfil.InterfaceColecaoMedico;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.CRM;
import br.edu.ufape.clickconsultas.negocios.modelo.perfil.Medico;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CpfExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CrmExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.CrmInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EmailExistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.EspecialidadeInexistenteException;
import br.edu.ufape.clickconsultas.negocios.servico.exception.UsuarioInexistenteException;

@Service
public class ServicoMedico implements InterfaceServicoMedico {
	@Autowired
	private InterfaceColecaoMedico colecaoMedico;

	public List<Medico> buscarTodos() {
		return colecaoMedico.findAll();
	}

	public Medico buscarPorId(long id) {
		return colecaoMedico.findById(id).orElse(null);
	}

	public List<Medico> buscarPorNome(String nome) {
		return colecaoMedico.findByNome(nome);
	}

	public Medico buscarPorEmail(String email) throws UsuarioInexistenteException {
		Medico m = colecaoMedico.findByEmail(email);
		if (m == null) 
			throw new UsuarioInexistenteException(email);
		return m;
	}
	
	public List<Medico> buscarPorEspecialidade(String nomeEspecialidade) throws EspecialidadeInexistenteException{
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
	
	public Medico buscarPorCpf(String cpf) {
		return colecaoMedico.findByCpf(cpf);
	}

	public Medico salvar(Medico medico) throws EmailExistenteException, CrmExistenteException, CpfExistenteException {
		//Verifica se o crm já está em uso
		List<CRM> crms = medico.getCrm();
		for (CRM crm : crms) {
		    Medico medicoExistenteByCrm = colecaoMedico.findByCrmNumero(crm.getNumero());
		    if (medicoExistenteByCrm != null) 
		        throw new CrmExistenteException(crm.getNumero());
		}
		
		//Verifica se o email já está em uso
	    Medico medicoExistenteByEmail = colecaoMedico.findByEmail(medico.getEmail());
	    if (medicoExistenteByEmail != null) 
	        throw new EmailExistenteException(medico.getEmail());
	    
	    
	    //Verifica se o cpf já está em uso
	    Medico medicoExistenteByCpf = colecaoMedico.findByCpf(medico.getCpf());
	    if (medicoExistenteByCpf != null) 
	        throw new CpfExistenteException(medico.getCpf());
	    
	    
		return colecaoMedico.save(medico);
	}

	public void remover(long id) {
		colecaoMedico.deleteById(id);
	}

}