package com.omni.aurora.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ApplicationUser implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "The field 'username' is mandatory")
    private String username;

    @Column(nullable = false)
    @ToString.Exclude
    @NotNull(message = "The field 'username' is mandatory")
    private String password;

    @Column(nullable = false)
    @NotNull(message = "The field 'username' is mandatory")
    private String role = "USER";

    public ApplicationUser(@NotNull ApplicationUser applicationUser) {
        this.username = applicationUser.getUsername();
        this.password = applicationUser.getPassword();
        this.role = applicationUser.getRole();
    }
}