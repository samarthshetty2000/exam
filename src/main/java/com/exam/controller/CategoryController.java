package com.exam.controller;

import org.aspectj.lang.annotation.DeclareMixin;
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
import com.exam.service.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")

public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	
	
	@PostMapping("/")
	public ResponseEntity<?> addCategory(@RequestBody Category category ){
	      Category category1=categoryService.addCategry(category);
	      return ResponseEntity.ok(category1);
		
	}
	
	
	
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable Long categoryId) {
		System.out.println("getCategory");
		return this.categoryService.getCategory(categoryId);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> getCatogoies(){
	return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	
	
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		Category category1=categoryService.updateCategory(category);
	      return category1;
	}
	
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}
	

}
