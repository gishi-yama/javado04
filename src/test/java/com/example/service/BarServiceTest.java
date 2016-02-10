package com.example.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.DemoApplication;
import com.example.repository.IBazRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DemoApplication.class })
public class BarServiceTest {

  @InjectMocks
  private BarService sut;

  @Mock
  private IBazRepository dao;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void fetchMessageでBazDAOのfetchMessageの返値を返す() {
    when(sut.fetchMessage()).thenReturn("OK");
    assertThat(sut.fetchMessage(), is("OK"));
  }
  
}
