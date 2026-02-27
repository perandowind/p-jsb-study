package com.back;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class QuestionRepositoryTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    /*@Test
    void t1() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다");
        questionRepository.save(q1);

        Answer a1 = new Answer();
        a1.setContent("sbb는 스프링부트 게시판입니다.1");
        a1.setQuestion(q1);
        answerRepository.save(a1);

        Answer a2 = new Answer();
        a1.setContent("sbb는 스프링부트 게시판입니다.2");
        a1.setQuestion(q1);
        answerRepository.save(a2);
    }*/



    /*@Test
    @Transactional
    void t2() {
        Question q1 = questionRepository.findById(1).get();
        System.out.println(q1.getSubject());
        System.out.println(q1.getContent());

        // q1 질문에 대한 답글
        List<Answer> answers = q1.getAnswerList();
        for (Answer a : answers) {
            System.out.println(a.getContent());
        }
        //answerRepository.findById() // select * from answer where question_id = 1;
    }*/

    @Test
    void t1() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        System.out.println(all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());

        System.out.println(q.getSubject());
    }

    @Test
    void t2() {
        Question q1 = questionRepository.findById(1).get(); //Optional 객체로 반환 -> get()

//        assertEquals("sbb가 무엇인가요?", q1.getSubject());
        assertThat(q1.getSubject()).isEqualTo("sbb가 무엇인가요?");
    }

    @Test
    void t3() {
        /**유저가 임의로 생성하는 변수들은 JPA가 미리 알아서 생성할 수 없다!! */
        Question q1 = questionRepository.findBySubject("sbb가 무엇인가요?").get();
        // select * from question where subject = 'sbb가 무엇인가요?'

        assertThat(q1.getId()).isEqualTo(1);
        assertThat(q1.getContent()).isEqualTo("sbb에 대해서 알고 싶습니다.");

    }

    @Test
    @DisplayName("질문 수정")
    void t4() {
        Question q1 = questionRepository.findById(1).get();

        q1.setSubject("sbb가 무엇인가요? - 수정"); // 객체 수준에서 변경
        questionRepository.save(q1);  // 실제 DB에 반영은 save() 로 진행
        questionRepository.flush();   // 변경 내용을 DB에 즉시 반영

        Question q1_2 = questionRepository.findById(1).get();
        assertThat(q1.getSubject()).isEqualTo("sbb가 무엇인가요? - 수정");

    }
}
