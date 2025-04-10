package com.exam.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;



public interface QuizRepository extends JpaRepository<Quiz,Long>{
	
	
	@Modifying
	@Query("delete from Quiz where qId= :id")
	 public void deleteById(@Param("id") Long id);
	
	Set<Quiz> findByActive(Boolean b);
	
//	List<Quiz> findByCategory(Category category);
	List<Quiz> findByCategoryCidAndActive(Long id,Boolean b);


}
