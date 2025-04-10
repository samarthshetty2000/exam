package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import com.exam.service.impl.QuestionServiceImpl;





@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		
		System.out.println("question");
		return ResponseEntity.ok(this.questionService.addQuestion(question));
		
	}
	

	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.updateQuestion(question));	
	}
	
	
	
	
	@GetMapping("/{id}")
	public Question getQuestion(@PathVariable Long id) {
		return this.questionService.getQuestion(id);
	
	}
	@DeleteMapping("/{id}")
	public void deleteQuestion(@PathVariable Long id) {
		this.questionService.deleteQuestion(id);
	
	}
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestions(@PathVariable Long qid) { 
		
//		Quiz quiz=new Quiz();
//		quiz.setqId(qid);
//		
//		return ResponseEntity.ok(this.questionService.getQuestionsOfQuiz(quiz));
		
		Quiz quiz=quizService.getQuiz(qid);
		Set<Question> questions=quiz.getQuestions();
		List<Question> list =new ArrayList(questions);
		Collections.shuffle(list);
		if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())) {
			list=list.subList(1, Integer.parseInt(quiz.getNumberOfQuestions()+1));
		}
		
		list.forEach((q)->{
			q.setAnswer("");
		});
		return ResponseEntity.ok(list);
	}

	
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsAdmin(@PathVariable Long qid) {
		
//		Quiz quiz=new Quiz();
//		quiz.setqId(qid);
//		
//		return ResponseEntity.ok(this.questionService.getQuestionsOfQuiz(quiz));
		
		Quiz quiz=quizService.getQuiz(qid);
		Set<Question> questions=quiz.getQuestions();
		List list =new ArrayList(questions);
		
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
		
		double marksGot=0;
		Integer correctAnswers=0;
		Integer attempted=0;
		
		for(Question q:questions) {
			Question question=this.questionService.getQuestion(q.getQuesId());
			if(q.getGivenAnswer()!=null && q.getGivenAnswer().equals(question.getAnswer())) {
				correctAnswers++;
			}
			
			if(q.getGivenAnswer()!=null) {
				attempted++;
			}
			
		}
		  
		
		marksGot=correctAnswers*Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
		
		
		System.out.println(correctAnswers);
		System.out.println(marksGot);
		
	   Map<Object,Object> of=Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
		
		return ResponseEntity.ok(of); 
		
	}


}
