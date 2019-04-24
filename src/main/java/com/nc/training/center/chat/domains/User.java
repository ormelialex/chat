package com.nc.training.center.chat.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String login;
    private String password;
    private byte age;
    private LocalDate birthday;
    private LocalDate registrationDay;
    @Enumerated(EnumType.STRING)
    private Role role;
}
