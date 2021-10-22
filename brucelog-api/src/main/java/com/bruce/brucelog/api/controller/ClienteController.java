package com.bruce.brucelog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruce.brucelog.domain.model.Cliente;
import com.bruce.brucelog.domain.repository.ClienteRepository;
import com.bruce.brucelog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;
    private CatalogoClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar() {

		return this.clienteRepository.findAll();
	}

	@GetMapping("/{idCliente}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long idCliente) {

		return this.clienteRepository.findById(idCliente)
//				.map(cliente -> ResponseEntity.ok(cliente))
				.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {

		return this.clienteService.salvar(cliente);
	}

	@PutMapping("/{idCliente}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long idCliente, @Valid @RequestBody Cliente cliente) {
		if (!this.clienteRepository.existsById(idCliente)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(idCliente);
		cliente = this.clienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Object> remover(@PathVariable Long idCliente){
      if(!(this.clienteRepository.existsById(idCliente))) {
    	  return ResponseEntity.notFound().build();
      }
      this.clienteService.excluir(idCliente);
      
      return ResponseEntity.noContent().build();
	}
}
