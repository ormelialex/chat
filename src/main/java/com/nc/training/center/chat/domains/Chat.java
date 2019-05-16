package com.nc.training.center.chat.domains;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<User> users;
}
