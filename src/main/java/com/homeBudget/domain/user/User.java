package com.homeBudget.domain.user;

import com.homeBudget.configuration.validator.ValidEmail;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @NotEmpty(message = "{user.first_name.notEmpty}")
    private String firstName;

    @Setter
    @NotEmpty(message = "{user.last_name.notEmpty}")
    private String lastName;

    @Setter
    @Column(name = "pass")
    @NotEmpty(message = "{user.password.notEmpty}")
//    @ValidPassword
    private String password;

    @Setter
    @NotEmpty(message = "{user.email.notEmpty}")
    @ValidEmail
    private String email;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("user{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}');
        return result.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
