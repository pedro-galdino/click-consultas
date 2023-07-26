package br.edu.ufape.clickconsultas.dados.suporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.clickconsultas.negocios.modelo.suporte.Mensagem;

@Repository
public interface InterfaceColecaoMensagem extends JpaRepository<Mensagem, Long> {

}
