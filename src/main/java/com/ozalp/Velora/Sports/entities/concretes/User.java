package com.ozalp.Velora.Sports.entities.concretes;

import com.ozalp.Velora.Sports.entities.abstracts.BaseEntity;
import com.ozalp.Velora.Sports.entities.enums.Gender;
import com.ozalp.Velora.Sports.entities.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "user_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> roles;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Athlete athlete;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Coach coach;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roles == null)
            return new HashSet<>();

        return (Collection<? extends GrantedAuthority>) roles.stream()
                .map(UserRole::getRole)
                .toList();

    }
}
