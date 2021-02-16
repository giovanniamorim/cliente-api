package br.dev.codein.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.codein.clientes.model.entity.ServicoPrestado;

public interface ServicoPrestadoRespository extends JpaRepository<ServicoPrestado, Integer> {

}
