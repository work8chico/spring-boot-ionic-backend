package com.chico.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.chico.cursomc.domain.Categoria;
import com.chico.cursomc.repositories.CategoriaRepository;
import com.chico.cursomc.services.exceptions.DataIntegrityException;
import com.chico.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {

		obj.setId(null);
		return repo.save(obj);

	}

	public Categoria update(Categoria obj) {

		find(obj.getId());
		return repo.save(obj);

	}

	public void delete(Integer id) {

		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			
			throw new DataIntegrityException("Não é possível excliuir uma categoria que possua produtos associados");			
		}

	}

}
