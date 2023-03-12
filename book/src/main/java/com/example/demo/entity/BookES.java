package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="bookss",createIndex=true)
public class BookES {
	@Id
	private Integer id;
	private String name;
	private String description;
	private double price;
}

