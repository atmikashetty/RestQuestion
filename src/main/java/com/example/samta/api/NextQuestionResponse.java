package com.example.samta.api;

public class NextQuestionResponse {

	private String correctAnswer;
	private NextQuestion nextQuestion;

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public NextQuestion getNextQuestion() {
		return nextQuestion;
	}

	public void setNextQuestion(NextQuestion nextQuestion) {
		this.nextQuestion = nextQuestion;
	}
}
