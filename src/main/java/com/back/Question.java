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

    /**
     * Answer는 Many쪽(N)이므로 List로 받아준다
     * mappedBy를 적은쪽은 외래키가 안생김, One쪽에 mappedBy를 적어줘야함.
     * REMOVE는 부모(Question)삭제할 때 자식(Answer)도 함께 삭제되도록 해줌
     */
    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Answer> answerList = new ArrayList<>();

    void addAnswer(String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(this); // DB 저장을 위한 필수구문
        this.answerList.add(answer); // Question에도 리스트로 저장하기 위함
    }

}
