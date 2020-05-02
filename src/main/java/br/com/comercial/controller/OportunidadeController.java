package br.com.comercial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.comercial.model.Oportunidade;
import br.com.comercial.repository.OportunidadeRepository;

@CrossOrigin("http://localhost:4200")//Necessario para o angular conseguir fazer requisicao rodando localmente, se nao tiver o local permite de qualquer lugar
@RestController
@RequestMapping("/oportunidade")
public class OportunidadeController {

	@Autowired
	private OportunidadeRepository repository;

	@GetMapping
	public List<Oportunidade> listar() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> find(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade =  repository.findById(id);
		
		if(oportunidade.isPresent())
			return ResponseEntity.ok(oportunidade.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade create(@Valid @RequestBody Oportunidade oportunidade) {
		Optional<Oportunidade> oport = repository.findByDescricaoAndNome(oportunidade.getDescricao(), oportunidade.getNome());
		
		if(oport.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ja existe oportunidade");
			
		return repository.save(oportunidade);
		
	}
	
	//@PostMapping("/atualizar")
	@PutMapping("/atualizar")
	public Oportunidade update(@Valid @RequestBody Oportunidade oportunidade) {
		
		Optional<Oportunidade> oport = repository.findById(oportunidade.getId());
		
		if(!oport.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nao existe oportunidade");
			
		
		return repository.save(oportunidade);
		
	}
	
	//@PostMapping("/delete/{id}")
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		Optional<Oportunidade> oport = repository.findById(id);
		
		if(!oport.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nao existe oportunidade");
			
		repository.delete(oport.get());
		
		return "Oportunidade deletada com sucesso" + oport.get();
		
	}
}
