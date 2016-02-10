package com.example.repository;

import org.springframework.stereotype.Service;

@Service
public class BazRepository implements IBazRepository {

	@Override
	public String fetchMessage() {
		return "Hello, Spring Boot + Wicket!";
	}

}
