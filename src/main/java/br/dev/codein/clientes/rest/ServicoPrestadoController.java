package br.dev.codein.clientes.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.dev.codein.clientes.dto.ServicoPrestadoDto;
import br.dev.codein.clientes.model.entity.Cliente;
import br.dev.codein.clientes.model.entity.ServicoPrestado;
import br.dev.codein.clientes.model.repository.ClienteRepository;
import br.dev.codein.clientes.model.repository.ServicoPrestadoRespository;
import br.dev.codein.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {
	
	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRespository servicoPrestadoRespository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody ServicoPrestadoDto dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();
		
		Cliente cliente = 
				clienteRepository
					.findById(idCliente)
					.orElseThrow(() -> new ResponseStatusException(
							HttpStatus.BAD_REQUEST, "Cliente inexistente"));
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData( data );
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return servicoPrestadoRespository.save(servicoPrestado);
	}
	
	@GetMapping
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "mes", required = false) Integer mes
			){
				return clienteRepository.findByNomeClienteAndMes( "%" + nome + "%", mes);
			}
	
}