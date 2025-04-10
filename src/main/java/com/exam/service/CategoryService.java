package com.exam.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.entity.exam.Category;

public interface CategoryService {
	public Category addCategry(Category category);
	public Category updateCategory(Category category);
	public Set<Category> getCategories();
	public Category getCategory(Long categoryId);
	public void deleteCategory(Long categoryId);

}
