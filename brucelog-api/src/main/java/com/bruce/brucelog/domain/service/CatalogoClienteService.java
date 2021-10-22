package com.bruce.brucelog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruce.brucelog.domain.exception.NegocioException;
import com.bruce.brucelog.domain.model.Cliente;
import com.bruce.brucelog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	private ClienteRepository clienteRepository;
	
	public Cliente busca(Long clienteId) {
		return this.clienteRepository.findById(clienteId)
		.orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = this.clienteRepository.findByEmail(cliente.getEmail())
		  .stream()
		  .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
		}
		return this.clienteRepository.save(cliente);
	}
	@Transactional
	public void excluir(Long id) {
		this.clienteRepository.deleteById(id);
	}
	
}
