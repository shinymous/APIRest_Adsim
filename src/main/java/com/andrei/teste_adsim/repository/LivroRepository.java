package com.andrei.teste_adsim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrei.teste_adsim.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
	
}