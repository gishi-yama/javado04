package com.example.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.data.TemperatureEntity;
import com.example.repository.ITableStorageRepository;
import com.microsoft.azure.storage.StorageException;

@Slf4j
@Service
public class TableStorageService implements ITableStorageService {

  @Autowired
  private ITableStorageRepository tableStorageRepository;

  @Override
  public void addTemperature(int temperature) throws InvalidKeyException, URISyntaxException, StorageException {
    val entity = new TemperatureEntity();
    entity.setTemperature(temperature);
    tableStorageRepository.insertTemperature(entity);
  }

  @Override
  public List<Integer> getTemperatures(int size) throws InvalidKeyException, URISyntaxException, StorageException {
    val temperatures = tableStorageRepository.selectTemperatures().stream()
        .sorted(Comparator.comparing(TemperatureEntity::getTimestamp).reversed())
        .limit(20)
        .map(TemperatureEntity::getTemperature)
        .collect(Collectors.toList());
    if (temperatures.size() < size) {
      int supplementSize = size - temperatures.size();
      val supplements = IntStream.range(0, supplementSize)
          .boxed()
          .map(i -> 0)
          .collect(Collectors.toList());
      supplements.addAll(temperatures);
      return supplements;
    }
    return temperatures;
  }

  @Override
  public int getLastTemperature() throws InvalidKeyException, URISyntaxException, StorageException {
    return tableStorageRepository.selectTemperatures().stream()
        .sorted(Comparator.comparing(TemperatureEntity::getTimestamp).reversed())
        .map(TemperatureEntity::getTemperature)
        .findFirst().orElseThrow(() -> new NullPointerException("No Data"));
  }

}
