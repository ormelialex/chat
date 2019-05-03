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
    private Long id;
    private String login;
    @Column(length = 70)
    private String password;
    private byte age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDay;
    /*@OneToMany(mappedBy="user")
    private Collection<Role> roles;*/
    @Enumerated(EnumType.STRING)
    private Role role;

    public Collection<? extends GrantedAuthority> getRoles() {
        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority("ROLE_" + Role.USER));
        list.add(new SimpleGrantedAuthority("ROLE_" + Role.ADMIN));
        return list;
    }
}



