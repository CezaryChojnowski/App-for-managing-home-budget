package com.homeBudget.domain.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homeBudget.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String firstName;

    @Setter
    private String lastName;

    @Setter
    private String email;

    @Setter
    private String phoneNumber;

    @Setter
    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_user")
    @JsonIgnore
    private User user;

//    @Override
//    public String toString() {
//        StringBuilder result = new StringBuilder();
//        result.append("person{" +
//                "idPerson=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                '}');
//        return result.toString();
//    }
}
