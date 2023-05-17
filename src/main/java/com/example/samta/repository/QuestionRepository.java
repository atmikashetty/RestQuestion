package com.example.samta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.samta.entity.Questions;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {
	Questions findFirstByOrderByIdAsc();

	@Query("SELECT q FROM Questions q WHERE q.questionId = ?1")
	List<Questions> findByQuestionId(Long questionId);
}
