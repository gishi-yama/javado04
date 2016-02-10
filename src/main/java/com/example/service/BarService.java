package com.example.service;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.IBazRepository;

@Slf4j
@Service
public class BarService implements IBarService {

  @Autowired
  private IBazRepository repo;
  
  private List<Integer> temperatures = new ArrayList<>();

  @Override
  public String fetchMessage() {
    return repo.fetchMessage();
  }

  @Override
  public void addTemperature(int temperature) {
    temperatures.add(temperature);
    log.info(temperatures.toString());
  }


}
