package com.homeBudget.domain.person;

import com.homeBudget.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findPersonById(Long id);
    List<Person> findAllByUser(User user);
}
