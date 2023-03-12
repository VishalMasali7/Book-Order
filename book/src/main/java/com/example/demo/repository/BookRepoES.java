package com.example.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.entity.BookES;


public interface BookRepoES extends ElasticsearchRepository<BookES,Integer>{

}
