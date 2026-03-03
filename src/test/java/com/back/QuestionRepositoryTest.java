package com.back;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    //    @Test
//    void tt() {

    // v1
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        questionRepository.save(q1);
//
//        Answer a1 = new Answer();
//        a1.setContent("sbb는 스프링부트 게시판입니다.1");
//        a1.setQuestion(q1);
//        answerRepository.save(a1);
//
//        Answer a2 = new Answer();
//        a2.setContent("sbb는 스프링부트 게시판입니다.2");
//        a2.setQuestion(q1);
//        answerRepository.save(a2);

    // v2

//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//
//        Answer a1 = new Answer();
//        a1.setContent("sbb는 스프링부트 게시판입니다.1");
//
//        Answer a2 = new Answer();
//        a1.setContent("sbb는 스프링부트 게시판입니다.2");
//
//        q1.getAnswerList().add(a1); // 부모에 자식을 연결
//        a1.setQuestion(q1); // 자식에 부모를 연결
//
//        q1.getAnswerList().add(a2); // 부모에 자식을 연결
//        a2.setQuestion(q1); // 자식에 부모를 연결
//
//        questionRepository.save(q1);

    //    }

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

    @Test
    @DisplayName("질문 삭제")
    void t5() {
        Question q1 = questionRepository.findById(1).get();
        questionRepository.delete(q1);

        assertThat(questionRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("답글 저장")
    @Transactional
    void t6() {
//        Question q1 = questionRepository.findById(2).get();
        Question q1 = questionRepository.findById(1).get();
        q1.addAnswer("답글 1");

        questionRepository.flush();

//        q1.getAnswerList().add(a1); // q1의 답글 목록에 a1 추가
//        questionRepository.save(q1);

        /** DB에 저장을 하고자 한다면, 외래키를 가지고 있는 클래스에 저장해야함 */
//        a1.setQuestion(q1); // a1이 q1을 참조하도록 설정
//        answerRepository.save(a1);
//        answerRepository.flush(); // 캐시를 비운다.(실제로는 JPA가 캐시 값을 사용하기 때문에 캐시를 비워서 JPA가 DB를 갔다오라도록 함.)


        Answer foundedAnswer = answerRepository.findById(1).get();

        assertThat(foundedAnswer.getId()).isEqualTo(1);
        assertThat(foundedAnswer.getContent()).isEqualTo("답글 1");

    }

    @Test
    @DisplayName("질문 삭제 with 답글")
    void t7() {
        //        Answer a1 = answerRepository.findById(1).get();
//        answerRepository.delete(a1);
//        Answer a2 = answerRepository.findById(2).get();
//        answerRepository.delete(a2);

        Question q1 = questionRepository.findById(1).get();
        questionRepository.delete(q1);
    }
}
