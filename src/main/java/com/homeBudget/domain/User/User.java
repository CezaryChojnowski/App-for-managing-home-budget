package com.homeBudget.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Setter
    @NotEmpty(message = "{user.first_name.notEmpty}")
    private String firstName;

    @Setter
    @NotEmpty(message = "{user.last_name.notEmpty}")
    private String lastName;

    @Setter
    @Column(name = "pass")
    @NotEmpty(message = "{user.password.notEmpty}")
    private String password;

    @Setter
    @NotEmpty(message = "{user.email.notEmpty}")
    @Email(message = "{user.email.Email}")
    private String email;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("User{" +
                "id=" + idUser +
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

    public static final class Builder {
        private int idUser;
        private String firstName;
        private String lastName;
        private String password;
        private String email;

        public Builder idUser(int idUser) {
            this.idUser = idUser;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }


        public User build() {
            User goal = new User();
            goal.idUser = this.idUser;
            goal.firstName = this.firstName;
            goal.lastName = this.lastName;
            goal.password = this.password;
            goal.email = this.email;
            return goal;
        }
    }
}
