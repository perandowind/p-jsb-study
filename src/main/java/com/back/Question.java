package com.back;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String subject;
    private String content;

    /**Answer는 Many쪽(N)이므로 List로 받아준다
      mappedBy를 적은쪽은 외래키가 안생김, One쪽에 mappedBy를 적어줘야함. */
    @OneToMany(mappedBy = "question")
    private List<Answer> answerList = new ArrayList<>();


}
