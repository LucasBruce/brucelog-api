package com.bruce.brucelog.domain.service;

import org.springframework.stereotype.Service;

import com.bruce.brucelog.domain.exception.EntidadeNaoEncontradaException;
import com.bruce.brucelog.domain.model.Entrega;
import com.bruce.brucelog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return this.entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
		
	}
}
