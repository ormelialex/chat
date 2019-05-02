package com.nc.training.center.chat.domains;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="USER")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String login;
    private String password;
    private byte age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDay;
    @Enumerated(EnumType.STRING)
    private Role role;
}
