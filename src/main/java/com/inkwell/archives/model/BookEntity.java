package com.inkwell.archives.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// HINT: Major of properties defined in this entity were based on the need to scrape data from specific source.
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id")
  private int id;
  @Column(name = "book_name")
  private String bookName;

  @Column(name = "book_title")
  private String bookTitle;

  @Column(name = "book_price")
  private int bookPrice;

  @Column(name = "book_stock")
  private int bookStock;

  @Column(name = "book_upc_code")
  private String upcCode;

  @Column(name = "book_category")
  private String bookCategory;

  // Unidirectional relationship to data_source
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "book_data_source_fk")
  private DataSourceEntity dataSource;

  // Bidirectional relationship between Purchase and Book ManyToMany
  @ManyToMany(
          fetch = FetchType.EAGER,
          cascade = CascadeType.PERSIST,
          mappedBy = "books")
  private List<PurchaseEntity> purchases = new ArrayList<>();
}
