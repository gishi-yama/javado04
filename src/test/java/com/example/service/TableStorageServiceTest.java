package com.example.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;

import lombok.val;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.ServiceTestor;
import com.example.data.TemperatureEntity;
import com.example.repository.ITableStorageRepository;
import com.microsoft.azure.storage.StorageException;

public class TableStorageServiceTest extends ServiceTestor {

  @InjectMocks
  private TableStorageService sut;

  @Mock
  private ITableStorageRepository mock;
  
  @Override
  public void setUp() {
    super.setUp();
    try {
      doNothing().when(mock).insertTemperature(anyObject());
    } catch (InvalidKeyException | URISyntaxException | StorageException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getTemperaturesがsize分のオブジェクトを返す() throws Exception {
    val returning = new ArrayList<TemperatureEntity>();
    when(mock.selectTemperatures()).thenReturn(returning);

    val actual = sut.getTemperatures(20);

    assertThat(actual.size(), is(20));
  }
  
  @Test
  public void getTemperaturesがsize分の0を返す() throws Exception {
    val returning = new ArrayList<TemperatureEntity>();
    when(mock.selectTemperatures()).thenReturn(returning);
    Integer[] expected = {0,0,0,0,0};

    val actual = sut.getTemperatures(5);
    
    assertThat(actual, contains(expected));
  }
  
  @Test
  public void getTemperaturesが0埋めのListを返す() throws Exception {
    val returning = new ArrayList<TemperatureEntity>();
    val entity = new TemperatureEntity();
    entity.setTemperature(8);
    returning.add(entity);
    when(mock.selectTemperatures()).thenReturn(returning);
    Integer[] expected = {0,0,0,0,8};

    val actual = sut.getTemperatures(5);
    
    assertThat(actual, contains(expected));
  }
  
  

}
