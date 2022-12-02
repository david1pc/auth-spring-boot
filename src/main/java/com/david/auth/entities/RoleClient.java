package com.david.auth.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Table(name = "roleClient")
@Entity(name = "RoleClient")
public class RoleClient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @OneToOne
    private Role role;

    @ManyToOne
    private Client client;

    public RoleClient(Role role, Client client) {
        this.role = role;
        this.client = client;
    }
}
