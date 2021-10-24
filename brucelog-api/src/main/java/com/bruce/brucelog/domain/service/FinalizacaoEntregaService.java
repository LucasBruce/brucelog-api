package com.bruce.brucelog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruce.brucelog.domain.model.Entrega;
import com.bruce.brucelog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	private EntregaRepository entregaRepository;
	private BuscaEntregaService buscarEntregaService;
	
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = this.buscarEntregaService.buscar(entregaId);
		
		entrega.finalizar();
		this.entregaRepository.save(entrega);
	}
}
