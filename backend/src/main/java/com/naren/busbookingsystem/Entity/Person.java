package com.naren.busbookingsystem.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Person")
@Table(
        name = "person",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "person_email_unique",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "person_username_unique",
                        columnNames = "user_name"
                )

        }
)
@NoArgsConstructor
@Getter
@Setter
public class Person implements UserDetails {

    @Id
    @SequenceGenerator(name = "person_id_seq",
            sequenceName = "person_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_id_seq")
    private Long id;

    @Column(
            name = "user_name",
            nullable = false
    )
    private String userName;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String name;

    @Column(
            nullable = false
    )
    @Email
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "[A-Za-z0-9_\\-\\.]+[@][a-z]+[\\.][a-z]{2,3}"
    )
    private String email;

    @Column(
            nullable = false
    )
    private String password;

    @Column(
            nullable = false
    )
    private int age;

    @Column(
            nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;



    public Person(Long id, String userName, String name, String email,
                  String password, int age, Gender gender) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public Person(String userName,
                  String name,
                  String email, String password,
                  int age, Gender gender) {
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new
                SimpleGrantedAuthority("ROLE_USER")
        );
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return userName;
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
