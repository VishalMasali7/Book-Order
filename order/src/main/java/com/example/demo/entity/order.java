package com.example.demo.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "orders")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class order {

	@Transient
	public static final String SEQUENCE_NAME = "order_sequence";

	@Id
	private int id;
	private String name;
	private String branch;
	private Date date;
	private int bid;
	Object books = new Object();

}
