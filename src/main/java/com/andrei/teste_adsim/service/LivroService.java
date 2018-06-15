package com.andrei.teste_adsim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrei.teste_adsim.domain.Livro;
import com.andrei.teste_adsim.exception.ObjectNotFoundException;
import com.andrei.teste_adsim.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	LivroRepository repo;
	
	public Livro find(Integer id) {
		Optional<Livro> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Livro.class.getName()));
	}
	
	public List<Livro> findAll(){
		return repo.findAll();
	}
	
	public Livro insert (Livro obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Livro update(Livro obj) {
		Livro newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Livro newObj, Livro obj) {
		newObj.setNome(obj.getNome());
		newObj.setAnoLancamento(obj.getAnoLancamento());
		newObj.setEditora(obj.getEditora());
		newObj.setValor(obj.getValor());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(Exception e) {
			throw new ObjectNotFoundException(
					"Objeto não pode ser excluído");
		}
	}
}
