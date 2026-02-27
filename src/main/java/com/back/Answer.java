package com.back;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    /**현재 내 클래스가 앞에옴 -> Many*/
    @ManyToOne
    private Question question;
}
