package com.example.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.ServiceTestor;
import com.example.repository.IBazRepository;


public abstract class BarServiceTest extends ServiceTestor {

  @InjectMocks
  private BarService sut;

  @Mock
  private IBazRepository dao;

  @Test
  public void fetchMessageでBazDAOのfetchMessageの返値を返す() {
    when(sut.fetchMessage()).thenReturn("OK");
    assertThat(sut.fetchMessage(), is("OK"));
  }
  
}
