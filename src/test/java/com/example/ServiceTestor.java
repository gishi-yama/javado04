package com.example;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DemoApplication.class })
@TestPropertySource("classpath:application.properties")
public abstract class ServiceTestor {
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }
  
}
