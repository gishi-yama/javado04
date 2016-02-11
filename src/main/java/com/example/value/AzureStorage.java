package com.example.value;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AzureStorage {
  
  private static final String FORMAT = "DefaultEndpointsProtocol=%s;AccountName=%s;AccountKey=%s";

  private String protocol;
  private String accountName;
  private String accountKey;
  
  public final String getStoregeConnectionString() {
    return String.format(FORMAT, protocol, accountName, accountKey);
  }
  
  public CloudTable getTable(String tableName) throws InvalidKeyException, URISyntaxException, StorageException {
    return CloudStorageAccount.parse(getStoregeConnectionString()).createCloudTableClient().getTableReference(tableName);
  }

}
