package com.example;

import lombok.ToString;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.value.AzureStorage;

@Slf4j
@ToString
@Configuration
public class AzureStorageConfig {
  
  @Value("${azure.storage.connection.protocol}")
  private String protocol;

  @Value("${azure.storage.connection.accountname}")
  private String accountName;

  @Value("${azure.storage.connection.accountkey}")
  private String accountKey;
  
  @Bean
  public AzureStorage azureStorege() {
    val as = new AzureStorage(protocol, accountName, accountKey);
    log.info(as.toString());
    return as;
  }

}

