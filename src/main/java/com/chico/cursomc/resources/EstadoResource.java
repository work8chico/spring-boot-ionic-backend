package com.chico.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chico.cursomc.domain.Cidade;
import com.chico.cursomc.domain.Estado;
import com.chico.cursomc.dto.CidadeDTO;
import com.chico.cursomc.dto.EstadoDTO;
import com.chico.cursomc.services.CidadeService;
import com.chico.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = service.findAll();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/{estado_id}/cidades")
	public ResponseEntity<List<CidadeDTO>> findAll(@PathVariable Integer estado_id) {
		List<Cidade> list = cidadeService.findByEstado(estado_id);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}
	
	

}
