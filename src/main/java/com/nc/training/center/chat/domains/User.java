package com.nc.training.center.chat.domains;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name="USER")
@Data
@Entity
    public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(unique = true)
    private Long id;
    private String login;
    @Column(length = 60)
    private String password;
    private byte age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDay;
    /*@OneToMany(mappedBy="user")
    private Collection<Role> roles;*/
    @Enumerated(EnumType.STRING)
    private Role role;//Переделать на Set или List , котоыре SHOOT BADLY NON FUNZIONA

    public Collection<? extends GrantedAuthority> getRoles() {
        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority(Role.USER.toString()));
        list.add(new SimpleGrantedAuthority(Role.ADMIN.toString()));
        return list;
    }
}



