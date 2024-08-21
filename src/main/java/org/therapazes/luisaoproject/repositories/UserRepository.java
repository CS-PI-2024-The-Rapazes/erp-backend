package org.therapazes.luisaoproject.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.therapazes.luisaoproject.entities.User;

import java.util.Optional;


@Repository
public interface UserRepository {
    Optional<User> findByEmail(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = ?2 WHERE u.email = ?1")
    void updatePassoword(String email, String password);
}
