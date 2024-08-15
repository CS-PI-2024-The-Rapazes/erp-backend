package org.therapazes.luisaoproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.therapazes.luisaoproject.entities.ForgotPassword;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {

}
