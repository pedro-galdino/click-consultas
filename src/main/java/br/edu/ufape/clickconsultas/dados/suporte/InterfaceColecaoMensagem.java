package br.edu.ufape.clickconsultas.dados.suporte;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.suporte.Mensagem;

@Repository
public interface InterfaceColecaoMensagem extends JpaRepository<Mensagem, Long> {
	public List<Mensagem> findByIdAutor(long id);
	public List<Mensagem> findByAutor(String autor);
	public List<Mensagem> findByDatahora(LocalDateTime datahora);
}
