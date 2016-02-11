package com.example.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

public interface ITableStorageService {

  public void addTemperature(int temperature) throws InvalidKeyException, URISyntaxException, StorageException;

  public List<Integer> getTemperatures(int size) throws InvalidKeyException, URISyntaxException, StorageException;

  public int getLastTemperature() throws InvalidKeyException, URISyntaxException, StorageException;

}
