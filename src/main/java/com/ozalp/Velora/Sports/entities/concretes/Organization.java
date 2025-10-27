package com.ozalp.Velora.Sports.entities.concretes;

import com.ozalp.Velora.Sports.entities.enums.OrganizationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "organizations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organization {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo_url")
    private String logoUrl;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "organization_status", nullable = false)
//    private OrganizationStatus organizationStatus;

}