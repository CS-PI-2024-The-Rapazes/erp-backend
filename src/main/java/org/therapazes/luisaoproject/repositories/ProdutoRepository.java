package org.therapazes.luisaoproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.therapazes.luisaoproject.entities.Produto;
import org.therapazes.luisaoproject.services.ProdutoService;

import java.util.Set;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT p FROM Produto p WHERE p.idProduto IN :produtoIds")
    Set<Produto> findAllByIdProduto(@Param("produtoIds") Set<Integer> produtoIds);
}
