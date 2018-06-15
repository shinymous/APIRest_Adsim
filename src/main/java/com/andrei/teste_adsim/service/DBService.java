package com.andrei.teste_adsim.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrei.teste_adsim.domain.Cliente;
import com.andrei.teste_adsim.domain.ItemPedido;
import com.andrei.teste_adsim.domain.Livro;
import com.andrei.teste_adsim.domain.Pedido;
import com.andrei.teste_adsim.repository.ClienteRepository;
import com.andrei.teste_adsim.repository.ItemPedidoRepository;
import com.andrei.teste_adsim.repository.LivroRepository;
import com.andrei.teste_adsim.repository.PedidoRepository;

@Service
public class DBService {
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateDataBase() {
		Livro l1 = new Livro(null, "Crônicas de Gelo e Fogo", "Bantam Spectra", 2000, 49.99);
		Livro l2 = new Livro(null, "Livro de Teste", "Editora Prototipo", 2018, 99.99);
		Livro l3 = new Livro(null, "Livro de Final", "Editora Acabou", 2020, 19.99);
		livroRepository.saveAll(Arrays.asList(l1, l2, l3));
		
		Cliente cli1 = new Cliente(null, "Jon", "44727718022");
		cli1.getTelefones().addAll(Arrays.asList("123123123123","12312312312"));
		Cliente cli2 = new Cliente(null, "Daenerys", "07243747019");
		cli2.getTelefones().addAll(Arrays.asList("993124122153","96312312310"));
		Cliente cli3 = new Cliente(null, "Tyrion", "27286312049");
		cli3.getTelefones().addAll(Arrays.asList("813123123155","81312312399"));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		
		Pedido p1 = new Pedido(null, cli1);
		cli1.getPedidos().add(p1);
		pedidoRepository.saveAll(Arrays.asList(p1));
		ItemPedido ip = new ItemPedido(p1, l1, 2, l1.getValor());
		itemPedidoRepository.saveAll(Arrays.asList(ip));
	}
}
