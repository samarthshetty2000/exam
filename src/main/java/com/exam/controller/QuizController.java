package com.exam.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuizService;
import com.exam.service.impl.QuestionServiceImpl;
import com.exam.service.impl.QuizServiceImpl;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public Quiz addQuiz(@RequestBody Quiz quiz) {
		
		return this.quizService.addQuiz(quiz);
		
	}
	@PutMapping("/")
	public Quiz updateQuiz(@RequestBody Quiz quiz) {
		
		return this.quizService.updateQuiz(quiz);
		
	}
	
	@GetMapping("/{id}")
	public Quiz getQuiz(@PathVariable Long id) {
		
		return quizService.getQuiz(id);
		
	}
	@GetMapping("/")
	public Set<Quiz> getAllQuiz() {
		
		return quizService.quizes();
		
	}
	@GetMapping("/active")
	public Set<Quiz> getActiveAllQuiz() {
		
		return quizService.activequizes();
		
	}
	
	
	
	
	@DeleteMapping("/{id}")
	public void deleteQuiz(@PathVariable Long id) {
		
		System.out.println("Delete---"+id);
		
	 quizService.deleteQuiz(id);
		
	}
	
	
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizByCat(@PathVariable Long cid){
		
     return this.quizService.getQuizzesOfCategory(cid);
     
		
	}

}
