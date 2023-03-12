package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "books")
public class Book {

	@Transient
	// to annotate a property or field of an entity class, mapped superclass, or
	// embeddable class that shouldnt be used by the database
	public static final String SEQUENCE_NAME = "user_sequence";

	@Id
	private Integer id;
	private String name;
	private String description;
	private double price;
}
