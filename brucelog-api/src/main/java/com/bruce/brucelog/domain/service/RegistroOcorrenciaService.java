package com.bruce.brucelog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bruce.brucelog.domain.exception.NegocioException;
import com.bruce.brucelog.domain.model.Entrega;
import com.bruce.brucelog.domain.model.Ocorrencia;
import com.bruce.brucelog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private EntregaRepository entregaRepository;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = this.entregaRepository.findById(entregaId)
				.orElseThrow(() -> new NegocioException("Entrega não encontrada"));
		return entrega.adicionarOcorrencia(descricao);
	}
}
