package com.example.samta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.samta.api.NextQuestion;
import com.example.samta.api.NextQuestionRequest;
import com.example.samta.api.NextQuestionResponse;
import com.example.samta.entity.Questions;
import com.example.samta.repository.QuestionRepository;

@RestController
@RequestMapping("/next")
public class NextQuestionController {

	private final QuestionRepository questionRepository;

	@Autowired
	public NextQuestionController(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	@PostMapping
	public ResponseEntity<NextQuestionResponse> getNextQuestion(@RequestBody NextQuestionRequest request) {
		System.out.println("In Post");
		Long questionId = request.getQuestion_id();
		String answer = request.getAnswer();
		System.out.println("Question id: " + questionId + " answer: " + answer);
		List<Questions> currentQuestion = questionRepository.findByQuestionId(questionId);
		if (!currentQuestion.isEmpty()) {
			Questions current = currentQuestion.get(0);
			NextQuestionResponse response = new NextQuestionResponse();

			if (current.getAnswer().equalsIgnoreCase(answer)) {
				response.setCorrectAnswer(current.getAnswer());
			}

			Questions nextQuestion = questionRepository.findFirstByOrderByIdAsc();

			response.setNextQuestion(new NextQuestion(nextQuestion.getId(), nextQuestion.getQuestion()));

			System.out.println("Ok Post!");
			return ResponseEntity.ok(response);
		} else {
			throw new IllegalArgumentException("Invalid question ID");
		}

	}
}
