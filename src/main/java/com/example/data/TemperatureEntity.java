package com.example.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.microsoft.azure.storage.table.TableServiceEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class TemperatureEntity extends TableServiceEntity {
  
  public static final String PARTITIONKEY = "t";
  
  public TemperatureEntity() {
    this.partitionKey = PARTITIONKEY;
    this.rowKey = String.valueOf(System.currentTimeMillis());
  }

  private int temperature;

}
