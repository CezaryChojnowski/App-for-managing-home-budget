package com.homeBudget.domain.person;

import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserRepository;
import com.homeBudget.rest.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    public final PersonRepository personRepository;
    public final UserRepository userRepository;

    public PersonDTO createPerson(String firstName, String lastName, String personEmail, String phoneNumber, String userEmail){
        Person person = new Person.PersonBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .email(personEmail)
                .phoneNumber(phoneNumber)
                .user(userRepository.findUserByEmail(userEmail).get())
                .build();
        personRepository.save(person);
        return new PersonDTO(person);
    }

    public List<Person> getPersons(User user){
        return personRepository.findAllByUser(user);
    }
}
