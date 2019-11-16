package com.homeBudget.domain.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homeBudget.configuration.validator.ValidEmail;
import com.homeBudget.configuration.validator.ValidPhoneNumber;
import com.homeBudget.domain.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotEmpty(message = "{user.firstName.notEmpty}")
    private String firstName;

    @Setter
    @NotEmpty(message = "{user.firstName.notEmpty}")
    private String lastName;

    @Setter
    @NotEmpty(message = "{user.email.notEmpty}")
    @ValidEmail
    private String email;

    @Setter
    @NotEmpty(message = "{person.phoneNumber.notEmpty}")
    @ValidPhoneNumber
    private String phoneNumber;

    @Setter
    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_user")
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("event{" +
                "idEvent=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}');
        return result.toString();
    }
}
