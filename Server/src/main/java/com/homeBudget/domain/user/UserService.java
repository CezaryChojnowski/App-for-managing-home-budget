package com.homeBudget.domain.user;

import com.homeBudget.configuration.error.RecordExistsException;
import com.homeBudget.configuration.error.RecordNotFoundException;
import com.homeBudget.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    public boolean isEmailExists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    public User createUser(String firstName, String lastName, String password, String email) {
        User user = new User.UserBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .build();
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new RecordNotFoundException("user not found"));
    }

    public UserDTO changingUserData(String firstName, String lastName, String email){
        User user = userRepository.findUserByEmail(email).get();
        if(firstName.equals(" ")){
            user.setLastName(lastName);
        }
        if(lastName.equals(" ")){
            user.setFirstName(firstName);
        }
        else{
            user.setFirstName(firstName);
            user.setLastName(lastName);
        }
        userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO registerUserAccount(User user){
        if (isEmailExists(user.getEmail())) {
            throw new RecordExistsException(env.getProperty("recordExists") + " " + user.getEmail());
        } else {
            createUser(user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
            return new UserDTO(user);
        }
    }

    public User getUserByAuthentication(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return findUserByEmail(email);
    }

    public String getEmailByAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public UserDTO changingEmail(String newEmail, String currentEmail){
        User user = findUserByEmail(currentEmail);
        user.setEmail(newEmail);
        if (isEmailExists(user.getEmail())) {
            throw new RecordExistsException(env.getProperty("recordExists") + " " + user.getEmail());
        } else {
        userRepository.save(user);
        return new UserDTO(user);
        }
    }
}
