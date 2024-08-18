package com.inkwell.archives.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchase")
public class PurchaseEntity {

  public PurchaseEntity(Date purchaseDate, UserEntity purchaseUser, List<BookEntity> books) {
    this.purchaseDate = purchaseDate;
    this.purchaseUser = purchaseUser;
    this.books = books;
  }

  public PurchaseEntity() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "purchase_id")
  private int id;

  @Column(name = "purchase_date")
  private Date purchaseDate;

  // Unidirectional relationship with UserEntity
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "purchase_user_fk")
  private UserEntity purchaseUser;

  // Bidirectional ManyToMany relationship between purchase and books
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinTable(
          name = "purchased_books",
          joinColumns = @JoinColumn(name = "book_id"),
          inverseJoinColumns = @JoinColumn(name = "purchase_id")
  )
  private List<BookEntity> books = new ArrayList<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(Date purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public UserEntity getPurchaseUser() {
    return purchaseUser;
  }

  public void setPurchaseUser(UserEntity purchaseUser) {
    this.purchaseUser = purchaseUser;
  }

  public List<BookEntity> getBooks() {
    return books;
  }

  public void setBooks(List<BookEntity> books) {
    this.books = books;
  }

  @Override
  public String toString() {
    return "PurchaseEntity{" +
            "id=" + id +
            ", purchaseDate=" + purchaseDate +
            ", purchaseUser=" + purchaseUser +
            ", books=" + books +
            '}';
  }
}
