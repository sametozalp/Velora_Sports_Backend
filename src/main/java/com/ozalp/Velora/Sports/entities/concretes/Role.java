package com.ozalp.Velora.Sports.entities.concretes;

import com.ozalp.Velora.Sports.entities.abstracts.BaseEntity;
import com.ozalp.Velora.Sports.entities.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", unique = true, nullable = false)
    private RoleEnum name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}