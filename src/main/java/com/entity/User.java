package com.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usufructuary")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "{user.first_name.notEmpty}")
    private String first_name;

    @Column(name = "last_name")
    @NotEmpty(message = "{user.last_name.notEmpty}")
    private String last_name;

    @Column(name = "pass")
    @NotEmpty(message = "{user.password.notEmpty}")
    private String password;

    @Column(name = "email")
    @NotEmpty(message = "{user.email.notEmpty}")
    @Email(message = "{user.email.Email}")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Wallet> wallets;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", wallets=" + wallets +
                '}';
    }
}
