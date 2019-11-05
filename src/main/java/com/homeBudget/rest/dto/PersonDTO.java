package com.homeBudget.rest.dto;

import com.homeBudget.domain.person.Person;
import lombok.*;

@Value
@Data
public class PersonDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public PersonDTO(Person person){
        this.firstName=person.getFirstName();
        this.lastName=person.getLastName();
        this.email=person.getEmail();
        this.phoneNumber=person.getPhoneNumber();
    }
}
