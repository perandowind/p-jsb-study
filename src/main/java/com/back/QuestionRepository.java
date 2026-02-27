package com.back;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    /**직접 언급해줘야함 & 메서드 명명규칙을 지켜야함!!*/
    Optional<Question> findBySubject(String subject);
}
