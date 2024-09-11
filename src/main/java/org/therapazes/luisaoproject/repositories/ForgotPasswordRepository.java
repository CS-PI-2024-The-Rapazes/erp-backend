package org.therapazes.luisaoproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.therapazes.luisaoproject.entities.ForgotPassword;
import org.therapazes.luisaoproject.entities.User;
import org.therapazes.luisaoproject.interfaces.ForgotPasswordProjection;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {

    @Query("SELECT fp FROM ForgotPassword fp WHERE fp.code = ?1 AND fp.user = ?2")
    Optional<ForgotPassword> findByOtpAndUser(String code, User user);

    Optional<ForgotPassword> findByUserEmail(String email);

    @Query("SELECT fp.code AS code, fp.expirationTime AS expirationTime FROM ForgotPassword fp WHERE fp.user.email = ?1")
    Optional<ForgotPasswordProjection> findByUserEmailProjection(String email);
}
