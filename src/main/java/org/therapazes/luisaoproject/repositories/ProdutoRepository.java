package org.therapazes.luisaoproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.therapazes.luisaoproject.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


}
