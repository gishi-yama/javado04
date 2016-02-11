package com.example.repository;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.data.TemperatureEntity;
import com.example.value.AzureStorage;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;

@Slf4j
@Repository
public class TableStorageRepository implements ITableStorageRepository {

  private static final String TEMPERATURE_TABLE = "temperature";

  @Autowired
  private AzureStorage azureStorage;

  public void createTable(String tableName) throws InvalidKeyException, StorageException, URISyntaxException {
    if (azureStorage.getTable(tableName).createIfNotExists()) {
      log.info("table is created : " + tableName);
    }
  }

  @Override
  public void insertTemperature(TemperatureEntity temperatureEntity) throws InvalidKeyException, URISyntaxException, StorageException {
    createTable(TEMPERATURE_TABLE);
    azureStorage.getTable(TEMPERATURE_TABLE).execute(TableOperation.insert(temperatureEntity));
  }

  @Override
  public List<TemperatureEntity> selectTemperatures() throws InvalidKeyException, URISyntaxException, StorageException {
    createTable(TEMPERATURE_TABLE);
    val query = TableQuery.from(TemperatureEntity.class);
    val entities = azureStorage.getTable(TEMPERATURE_TABLE).execute(query);
    return StreamSupport.stream(entities.spliterator(), false).collect(Collectors.toList());
  }

}
