package com.example.samta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.samta.api.QuestionData;
import com.example.samta.entity.Questions;
import com.example.samta.repository.QuestionRepository;

@Service
public class FetchData {

	private static final int COUNT = 5;
	private final RestTemplate restTemplate;

	@Autowired
	private QuestionRepository questionRepository;

	public FetchData() {
		this.restTemplate = new RestTemplate();
	}

	public List<QuestionData> fetchQuestion() {
		System.out.println("In fetch question.");

		String url = "https://jservice.io/api/random";
		List<QuestionData> data = new ArrayList<>();

		for (int i = 0; i < COUNT; i++) {
			ResponseEntity<QuestionData[]> response = restTemplate.getForEntity(url, QuestionData[].class);
			if (response.getStatusCode().is2xxSuccessful()) {
				QuestionData[] datas = response.getBody();
				if (datas != null) {
					for (QuestionData questionData : datas) {
						QuestionData filterData = new QuestionData();
						filterData.setId(questionData.getId());
						filterData.setQuestion(questionData.getQuestion());
						filterData.setAnswer(questionData.getAnswer());
						data.add(filterData);
					}
				}
			}
		}
		return data;
	}

	public void saveData() {
		System.out.println("In save data.");
		List<QuestionData> datas = fetchQuestion();
		for (QuestionData row : datas) {
			Questions questions = new Questions();
			questions.setQuestionId(row.getId());
			questions.setQuestion(row.getQuestion());
			questions.setAnswer(row.getAnswer());
			System.out.println("Question ID: " + questions.getQuestionId());
			System.out.println("Question: " + questions.getQuestion());
			System.out.println("Answer: " + questions.getAnswer());
			questionRepository.save(questions);
		}
		System.out.println("Out save data.");

	}

	public void deleteAllData() {
		questionRepository.deleteAll();
	}
}
