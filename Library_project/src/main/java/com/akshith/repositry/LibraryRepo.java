package com.akshith.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshith.entity.Books;

public interface LibraryRepo extends JpaRepository<Books, Integer> {

	List<Books> findByTitleStartsWith(String title);

}
