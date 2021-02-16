package br.dev.codein.clientes.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.dev.codein.clientes.model.entity.Cliente;
import br.dev.codein.clientes.model.entity.ServicoPrestado;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query(" select s from ServicoPrestado s "
			+ "join s.cliente c "
			+ "where upper( c.nome ) like upper( :nome ) "
			+ "and MONTH(s.data) =:mes ")
	List<ServicoPrestado> findByNomeClienteAndMes( @Param("nome") String nome, @Param("mes") Integer mes);

}
