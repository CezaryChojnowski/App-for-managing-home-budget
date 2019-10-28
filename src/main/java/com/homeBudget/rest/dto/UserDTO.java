package com.homeBudget.rest.dto;

import com.homeBudget.domain.user.User;
import lombok.*;


@Value
@Data
@RequiredArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;

    public UserDTO(User user){
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
    }
}
