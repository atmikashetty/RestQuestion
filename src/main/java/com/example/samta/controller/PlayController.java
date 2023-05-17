package com.example.samta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.samta.entity.Questions;
import com.example.samta.repository.QuestionRepository;

@RestController
public class PlayController {

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/play")
	public ResponseEntity<Map<String, Object>> getQuestion() {
		Questions questions = getOneQuestion();
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("question_id", questions.getQuestionId());
		responseMap.put("question", questions.getQuestion());
		return ResponseEntity.ok(responseMap);
	}

	private Questions getOneQuestion() {
		List<Questions> allList = questionRepository.findAll();
		if (allList.isEmpty()) {
			throw new NoSuchElementException("NA");
		}
		Random random = new Random();
		int randomInd = random.nextInt(allList.size());
		return allList.get(randomInd);
	}
}
