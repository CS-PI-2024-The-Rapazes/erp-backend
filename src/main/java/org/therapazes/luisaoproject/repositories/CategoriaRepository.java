package org.therapazes.luisaoproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.therapazes.luisaoproject.entities.Categoria;
@Repository

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
