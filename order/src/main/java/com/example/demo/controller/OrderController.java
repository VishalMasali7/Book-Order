package com.example.demo.controller;

import java.util.List;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.OrderApplication;
import com.example.demo.entity.order;

import com.example.demo.service.OrderRepoImpls;
import com.example.demo.service.SequenceGenerator;

@RestController

public class OrderController {
	@Autowired
	private SequenceGenerator service;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OrderRepoImpls repository;

	org.slf4j.Logger log = LoggerFactory.getLogger(OrderApplication.class);

	@PostMapping("/saveOrder")
	public order save(@RequestBody order order) {
		// generate sequence
		order.setId(service.getSequenceNumber(com.example.demo.entity.order.SEQUENCE_NAME));
		// int bookid=order.getBid();
		// String uri = "http://localhost:9191/searchBook/{bookid}";
		// Map<String,Integer> uriparam = new HashMap<>();
		// uriparam.put("bookid", order.getBid());
		// List books =this.restTemplate.getForObject(uri,List.class, uriparam );
//        //List books = this.restTemplate.getForObject("http://localhost:9191/searchBook/"+bookid, List.class);
		// order.setBooks(books);

		return repository.save(order);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/saveOrder/{id}")
	public void update(@PathVariable Integer id, @RequestBody order order) {
		repository.update(id, order);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteOrder/{id}")
	public void delete(@PathVariable Integer id) {
		repository.delete(id);
	}

	@GetMapping("/orders")
	public List<order> getAllBooks() {
		return repository.getAllBooks();
	}

	@GetMapping("/searchOrder/{id}")
	public order getBook(@PathVariable Integer id) {

		order orders = repository.search(id).get();

		int bookid = orders.getBid();

		// String uri = "http://localhost:9191/searchBook/{bookid}";
//	      Map<String,Integer> uriparam = new HashMap<>();
//	        
//	      uriparam.put("bookid", orders.getBid());

		Optional books = this.restTemplate.getForObject("http://localhost:9191/searchBook/" + bookid, Optional.class);
		if (books.isPresent()) {
			System.out.println(books.get()); // a
			log.info("book data recieved of ID " + bookid);
		}

//	    List i=List.valueOf(books);
//		  System.out.println(books);
		orders.setBooks(books.get());
		return orders;
	}

}

// Subject sub = subjectservice.search(id,order).get();
// String depid=sub.getDepid();
//
//
// uriparam.put("depid", sub.getDepid());
// RestTemplate restTemplate = new RestTemplate();
// Depbean res = restTemplate.getForObject(uri,Depbean.class, uriparam );
//// logger.info(res.toString());
// DepSub om = new DepSub();
// om.setId(sub.getId());
// om.setSname(sub.getSname());
// om.setDepid(res.getId());
// om.setDname(res.getDname());
// return om;
