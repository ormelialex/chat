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
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name="USER")
@Data
@Entity
    public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(unique = true,name = "user_id")
    private Long id;
    private String login;
    @Column(length = 60)
    private String password;
    private byte age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDay;
    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role_id")
    Set<Role> role;

    public Collection<? extends GrantedAuthority> getRoles() {
        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority(Role.USER.toString()));
        list.add(new SimpleGrantedAuthority(Role.ADMIN.toString()));
        return list;
    }
}



