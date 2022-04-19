package com.yemreu.main.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode
public class User {
    // Unique identifier of user model.
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    // Name of user.
    private String name;
    // Username of user.
    private String username;
    // Password of user.
    private String password;
    // Surname of user.
    private String surname;
    // Role of user. Can be patient, admin or laboratory.
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="users_roles", joinColumns=@JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    private String tc;

    public User(String name, String username, String password, String surname, List<Role> roles, String tc) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.roles = roles;
        this.tc = tc;
    }
}
