package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer idUser;
    @Column(name = "password")
    private String password;
    private String email;


    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;
}
