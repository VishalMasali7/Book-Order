package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.order;
import com.example.demo.repo.OrderRepository;

@Service
public class OrderRepoImpls {
	@Autowired
	private OrderRepository orderrepo;

	public List<order> getAllBooks() {
		List<order> orders = new ArrayList<>();
		orderrepo.findAll().forEach(orders::add);
		return orders;
	}

	public order save(order book) {
		return orderrepo.save(book);

	}

	public void update(Integer id, order book) {
		orderrepo.save(book);
	}

	public void delete(Integer id) {
		orderrepo.deleteById(id);

	}

	public Optional<order> search(Integer id) {

		return orderrepo.findById(id);
	}
}
