package com.nc.training.center.chat.domains;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String msg;
    @OneToOne
    private User sender;
    private LocalDateTime sendDate;
    @ManyToOne(cascade = {CascadeType.PERSIST})//Maybe +  CascadeType.MERGE and it isn't a fact, that's i'm right
    private Chat chat;
}
