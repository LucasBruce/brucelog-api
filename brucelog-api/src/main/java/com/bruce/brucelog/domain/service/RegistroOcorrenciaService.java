package com.bruce.brucelog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bruce.brucelog.domain.model.Entrega;
import com.bruce.brucelog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {


	private BuscaEntregaService buscaEntregaRepository;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = this.buscaEntregaRepository.buscar(entregaId);
		return entrega.adicionarOcorrencia(descricao);
	}
}
