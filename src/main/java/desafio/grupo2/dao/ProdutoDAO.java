package desafio.grupo2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import desafio.grupo2.models.Produto;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Integer>{
}