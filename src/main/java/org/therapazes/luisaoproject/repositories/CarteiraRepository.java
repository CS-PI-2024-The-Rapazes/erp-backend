package org.therapazes.luisaoproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.therapazes.luisaoproject.entities.Carteira;
import org.therapazes.luisaoproject.entities.ForgotPassword;

@Repository
public interface CarteiraRepository  extends JpaRepository<Carteira, Integer> {
}
