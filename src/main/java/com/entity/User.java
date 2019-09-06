package com.entity;

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
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int idUser;

    @Column(name = "first_name")
    @NotEmpty(message = "{user.first_name.notEmpty}")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "{user.last_name.notEmpty}")
    private String lastName;

    @Column(name = "pass")
    @NotEmpty(message = "{user.password.notEmpty}")
    private String password;

    @Column(name = "email")
    @NotEmpty(message = "{user.email.notEmpty}")
    @Email(message = "{user.email.Email}")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Wallet> wallets;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("User{" +
                "id=" + idUser +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", wallets=" + wallets +
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
        private List<Wallet> wallets;

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

        public Builder wallets(List<Wallet> wallets) {
            this.wallets = wallets;
            return this;
        }

        public User build() {
            User goal = new User();
            goal.idUser = this.idUser;
            goal.firstName = this.firstName;
            goal.lastName = this.lastName;
            goal.password = this.password;
            goal.email = this.email;
            goal.wallets = this.wallets;
            return goal;
        }
    }
}
