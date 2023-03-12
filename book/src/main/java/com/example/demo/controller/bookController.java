package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookES;
import com.example.demo.repository.BookRepoES;
import com.example.demo.repository.BookRepoImpls;
import com.example.demo.service.SequenceGeneratorService;

@RestController
@EnableCaching

public class bookController {
	@Autowired
	private SequenceGeneratorService service;

	@Autowired
	private BookRepoImpls repository;
   @Autowired
  public BookRepoES bookrepoES;

	@PostMapping("/saveBook")
	public Book save(@RequestBody Book book) {
		// generate sequence
		book.setId(service.getSequenceNumber(Book.SEQUENCE_NAME));
		return repository.save(book);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/saveBook/{id}")
	public void update(@PathVariable Integer id, @RequestBody Book book) {
		repository.update(id, book);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteBook/{id}")
	@CacheEvict(key="#id",value="books")
	public void delete(@PathVariable Integer id) {
		repository.delete(id);
	}

	@GetMapping("/books")
	
	public List<Book> getAllBooks() {
		return repository.getAllBooks();
	}

	@GetMapping("/searchBook/{id}")
	@Cacheable(key="#id",value="books")
	public Optional<Book> getBook(@PathVariable Integer id) {
		// System.out.println(repository.search(id));
		return repository.search(id);
	}
	
	
	@PostMapping("/saveBookES")
	public BookES save(@RequestBody BookES bookis) {
		// generate sequence
		//book.setId(service.getSequenceNumber(Book.SEQUENCE_NAME));
		return bookrepoES.save(bookis);
	}
	@GetMapping("/getBooks")
	public Iterable<BookES> FindAll()
	{
		return bookrepoES.findAll();
	}
}
