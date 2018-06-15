package com.andrei.teste_adsim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andrei.teste_adsim.domain.Cliente;
import com.andrei.teste_adsim.exception.ObjectNotFoundException;
import com.andrei.teste_adsim.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repo;
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert (Cliente obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setCpf(obj.getCpf());
		newObj.setNome(obj.getNome());
		newObj.setTelefones(obj.getTelefones());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new ObjectNotFoundException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
}
