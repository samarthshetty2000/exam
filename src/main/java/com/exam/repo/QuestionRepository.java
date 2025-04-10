package com.exam.repo;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
public interface QuestionRepository extends JpaRepository<Question,Long>{
	
	public Set<Question> findByQuiz(Quiz quiz);
	@Modifying
	@Query("delete from Question where quesId= :id")
	 public void deleteById(@Param("id") Long id);


}
