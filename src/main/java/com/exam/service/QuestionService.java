package com.exam.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;

public interface QuestionService {
	public Question addQuestion(Question question);
	public Question updateQuestion(Question question);
	public Set<Question> questions();
	public Question getQuestion(Long quesId);
	
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	public void deleteQuestion(Long quesId);
	
	

}
