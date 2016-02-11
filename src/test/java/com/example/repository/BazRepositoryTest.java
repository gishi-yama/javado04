package com.example.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.DemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DemoApplication.class })
@TestPropertySource("classpath:application.properties")
public class BazRepositoryTest {

	@InjectMocks
	private BazRepository sut;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getMessageでDAOの検索結果を返す() {
		assertThat(sut.fetchMessage(), is("Hello, Spring Boot + Wicket!"));
	}

}
