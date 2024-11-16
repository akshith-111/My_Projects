package com.akshith.entity;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BorrowRequests {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer userId;	
	private String title;
	private Integer bookId;
	private Date date;
	private String status;
	
	
	public BorrowRequests(Integer userId,String title ,Integer bookId, Date date, String status) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.date = date;
		this.status = status;
		this.title=title;
	}
}
		