package org.therapazes.luisaoproject.entities;

import jakarta.persistence.OneToOne;

public class User {
    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;
}
