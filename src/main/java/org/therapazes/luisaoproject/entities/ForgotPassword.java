package org.therapazes.luisaoproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fpid;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Date expirationTime;

    @OneToOne
    private User user;

}
