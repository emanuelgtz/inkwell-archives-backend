package com.inkwell.archives.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

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
  private String bookname;

  @Column(name = "book_title")
  private String booktitle;

  @Column(name = "book_price")
  private int bookprice;

  @Column(name = "book_stock")
  private int bookstock;

  @Column(name = "book_upc_code")
  private String bookupccode;

  @Column(name = "book_category")
  private String bookcategory;

  // Unidirectional relationship to data_source
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "book_data_source_fk")
  //TODO: fix this when data_source entity is ready
  private String ChangeThis;

  // Bidirectional relationship between Purchase and Book ManyToMany
  @ManyToMany(
          fetch = FetchType.EAGER,
          cascade = CascadeType.PERSIST,
          mappedBy = "books")
  private PurchaseEntity purchases;


}
