package com.example.repository;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.example.data.TemperatureEntity;
import com.microsoft.azure.storage.StorageException;

public interface ITableStorageRepository {
  
  public void insertTemperature(TemperatureEntity temperatureEntity) throws InvalidKeyException, URISyntaxException, StorageException;
  
  public List<TemperatureEntity> selectTemperatures() throws InvalidKeyException, URISyntaxException, StorageException;

}
