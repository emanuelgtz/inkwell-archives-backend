package com.inkwell.archives.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "data_source")
public class DataSourceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "data_source_id")
  private int id;

  @Column(name = "source_name")
  private String sourcename;



}
