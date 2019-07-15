package com.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
public class usufructuary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usufructuary")
    private int id_usufructuary;

    @Column(name = "login")
    private String login;

    @Column(name = "pass")
    private String password;

    @Column(name = "email")
    private String email;
}
