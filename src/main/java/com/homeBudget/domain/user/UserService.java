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

    public UserDTO changingUserData(UserDTO userDTO, String email){
        User user = userRepository.findUserByEmail(email).get();
        if(userDTO.getFirstName() == null){
            user.setLastName(userDTO.getLastName());
        }
        if(userDTO.getLastName() == null){
            user.setFirstName(userDTO.getFirstName());
        }
        else{
            user.setFirstName(userDTO.getLastName());
            user.setLastName(userDTO.getLastName());
        }
        userRepository.save(user);
        return userDTO;
    }

    public UserDTO registerUserAccount(User user){
        if (isEmailExists(user.getEmail())) {
            throw new RecordExistsException(env.getProperty("recordExists") + " " + user.getEmail());
        } else {
            UserDTO userDTO = new UserDTO(user);
            createUser(user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
            return userDTO;
        }
    }

    public User getUserByAuthentication(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return findUserByEmail(email);
    }

    public String getEmailByAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
