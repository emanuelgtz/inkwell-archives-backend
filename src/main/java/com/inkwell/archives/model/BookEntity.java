package com.inkwell.archives.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

// HINT: Major of properties defined in this entity were based on the need to scrape data from specific source.
@Entity
@Table(name = "books")
public class BookEntity {

  public BookEntity(String bookName, String bookTitle,
                    int bookPrice, int bookStock, String upcCode,
                    String bookCategory, DataSourceEntity dataSource, List<PurchaseEntity> purchases) {
    this.bookName = bookName;
    this.bookTitle = bookTitle;
    this.bookPrice = bookPrice;
    this.bookStock = bookStock;
    this.upcCode = upcCode;
    this.bookCategory = bookCategory;
    this.dataSource = dataSource;
    this.purchases = purchases;
  }

  public BookEntity() {}

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




  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public int getBookPrice() {
    return bookPrice;
  }

  public void setBookPrice(int bookPrice) {
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

  public List<PurchaseEntity> getPurchases() {
    return purchases;
  }

  public void setPurchases(List<PurchaseEntity> purchases) {
    this.purchases = purchases;
  }

  @Override
  public String toString() {
    return "BookEntity{" +
            "id=" + id +
            ", bookName='" + bookName + '\'' +
            ", bookTitle='" + bookTitle + '\'' +
            ", bookPrice=" + bookPrice +
            ", bookStock=" + bookStock +
            ", upcCode='" + upcCode + '\'' +
            ", bookCategory='" + bookCategory + '\'' +
            ", dataSource=" + dataSource +
            ", purchases=" + purchases +
            '}';
  }
}
