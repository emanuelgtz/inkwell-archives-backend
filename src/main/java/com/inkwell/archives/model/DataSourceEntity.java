package com.inkwell.archives.model;


import jakarta.persistence.*;
import lombok.*;

import java.awt.print.Book;

@Entity
@Table(name = "data_source")
public class DataSourceEntity {

  public DataSourceEntity(String sourceName) {
    this.sourceName = sourceName;
  }

  public DataSourceEntity() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "data_source_id")
  private int id;

  @Column(name = "source_name")
  private String sourceName;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSourceName() {
    return sourceName;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  @Override
  public String toString() {
    return "DataSourceEntity{" +
            "id=" + id +
            ", sourceName='" + sourceName + '\'' +
            '}';
  }
}
