package com.example.samta.api;

public class NextQuestion {

	private Long questionId;
	private String question;

	public NextQuestion(Long questionId, String question) {
		super();
		this.questionId = questionId;
		this.question = question;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
