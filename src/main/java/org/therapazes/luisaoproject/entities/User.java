package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;
}
