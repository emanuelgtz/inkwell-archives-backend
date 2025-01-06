package com.inkwell.archives.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// HINT: Major of properties defined in this entity were based on the need to scrape data from specific source.
@Entity
@Table(name = "books")
public class BookEntity {
  public BookEntity(
          String bookTitle,
          int bookPrice, int bookStock, String upcCode,
          String bookCategory, DataSourceEntity dataSource) {
    this.bookTitle = bookTitle;
    this.bookPrice = bookPrice;
    this.bookStock = bookStock;
    this.upcCode = upcCode;
    this.bookCategory = bookCategory;
    this.dataSource = dataSource;
  }

  public BookEntity() {}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id")
  private int id;

  @Column(name = "book_title")
  private String bookTitle;

  @Column(name = "book_price")
  private float bookPrice;

  @Column(name = "book_stock")
  private int bookStock;

  @Column(name = "book_upc_code")
  private String upcCode;

  @Column(name = "book_category")
  private String bookCategory;

  // Unidirectional relationship to data_source
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "book_data_source_fk")
  private DataSourceEntity dataSource;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public float getBookPrice() {
    return bookPrice;
  }

  public void setBookPrice(float bookPrice) {
    this.bookPrice = bookPrice;
  }

  public int getBookStock() {
    return bookStock;
  }

  public void setBookStock(int bookStock) {
    this.bookStock = bookStock;
  }

  public String getUpcCode() {
    return upcCode;
  }

  public void setUpcCode(String upcCode) {
    this.upcCode = upcCode;
  }

  public String getBookCategory() {
    return bookCategory;
  }

  public void setBookCategory(String bookCategory) {
    this.bookCategory = bookCategory;
  }

  public DataSourceEntity getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSourceEntity dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public String toString() {
    return "BookEntity{" +
            "id=" + id +
            ", bookTitle='" + bookTitle + '\'' +
            ", bookPrice=" + bookPrice +
            ", bookStock=" + bookStock +
            ", upcCode='" + upcCode + '\'' +
            ", bookCategory='" + bookCategory + '\'' +
            ", dataSource=" + dataSource +
            '}';
  }
}
