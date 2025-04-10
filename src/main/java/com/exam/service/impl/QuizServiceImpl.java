package com.exam.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.QuizService;


@Service
public class QuizServiceImpl implements QuizService {
	
	@Autowired
    private QuizRepository quizRepository;
    
    
	@Override
	public Quiz addQuiz(Quiz quiz) {
	
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		return this.quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> quizes() {
//		
		return new LinkedHashSet<>(this.quizRepository.findAll());
	}
	

	
	@Override
	public Set<Quiz> activequizes() {
		
		return new LinkedHashSet<>(this.quizRepository.findByActive(true));
	}


	@Override
	public Quiz getQuiz(Long quizId) {
	
		return this.quizRepository.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long id) {
		this.quizRepository.deleteById(id);
		
		
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Long id) {
		// TODO Auto-generated method stub
		return this.quizRepository.findByCategoryCidAndActive(id,true);
	}


}
