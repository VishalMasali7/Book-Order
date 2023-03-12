package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Book;

@Service
public class BookRepoImpls {
	@Autowired
	private BookRepository bookrepo;

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		bookrepo.findAll().forEach(books::add);
		return books;
	}

	public Book save(Book book) {
		return bookrepo.save(book);

	}

	public void update(Integer id, Book book) {
		bookrepo.save(book);

	}

	public void delete(Integer id) {
		bookrepo.deleteById(id);

	}

	public Optional<Book> search(Integer id) {
		return bookrepo.findById(id);
	}
}

// List<Book> bookss= books.stream().toList();
// System.out.println(books);
// System.out.println(bookss);
//	Gson g = new Gson();
//	String bookss= g.toJson(books, Book.class);
// return b.c.test()
// .map(value -> new ArrayList<A>(Arrays.asList(value)))
// .orElseGet(() -> new ArrayList<A>());
