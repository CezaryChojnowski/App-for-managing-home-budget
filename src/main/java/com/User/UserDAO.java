package com.User;

import com.error.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDAO {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    public boolean isEmailExists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    public User createUser(String firstName, String lastName, String password, String email) {
        User user = new User.Builder()
                .firstName(firstName)
                .lastName(lastName)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .build();
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new RecordNotFoundException("User not found"));
    }

    public UserDTO convertToDto(User user){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public User changingUserData(UserDTO userDTO, String email){
        User user = userRepository.findUserByEmail(email).get();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return userRepository.save(user);
    }
}
