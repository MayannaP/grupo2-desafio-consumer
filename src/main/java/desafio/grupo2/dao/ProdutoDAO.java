package desafio.grupo2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import desafio.grupo2.models.Produto;

public interface ProdutoDAO extends JpaRepository<Produto, Integer>{
}