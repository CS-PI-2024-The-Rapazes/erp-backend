package org.therapazes.luisaoproject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.therapazes.luisaoproject.entities.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String username);

    @Query("update User u")
    void updatePassword(String email, String password);
}
