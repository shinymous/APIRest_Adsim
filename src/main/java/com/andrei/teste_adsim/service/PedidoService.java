package com.andrei.teste_adsim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrei.teste_adsim.domain.ItemPedido;
import com.andrei.teste_adsim.domain.Pedido;
import com.andrei.teste_adsim.exception.ObjectNotFoundException;
import com.andrei.teste_adsim.repository.ItemPedidoRepository;
import com.andrei.teste_adsim.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository repo;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	LivroService livroService;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj = repo.save(obj);
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setLivro(livroService.find(ip.getLivro().getId()));
			ip.setPreco(ip.getLivro().getValor());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
	
	public List<Pedido> findAll(){
		return repo.findAll();
	}
}
