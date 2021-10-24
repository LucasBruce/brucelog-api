package com.bruce.brucelog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruce.brucelog.api.assembler.EntregaAssembler;
import com.bruce.brucelog.api.model.EntregaModel;
import com.bruce.brucelog.api.model.input.EntregaInput;
import com.bruce.brucelog.domain.model.Entrega;
import com.bruce.brucelog.domain.repository.EntregaRepository;
import com.bruce.brucelog.domain.service.FinalizacaoEntregaService;
import com.bruce.brucelog.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaAssembler entregaAssembler;
	private FinalizacaoEntregaService finalizacaoEntregaService;

	@GetMapping
	public List<EntregaModel> listarEntregas() {
		return this.entregaAssembler.toCollectionModel(this.entregaRepository.findAll());
	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscarEntrega(@PathVariable Long entregaId) {
		return this.entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(this.entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		this.finalizacaoEntregaService.finalizar(entregaId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = this.entregaAssembler.toEntity(entregaInput);
		Entrega entregaSolicitada = this.solicitacaoEntregaService.solicitar(novaEntrega);
		return this.entregaAssembler.toModel(entregaSolicitada);
	}
}
