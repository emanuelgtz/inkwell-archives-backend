package com.inkwell.archives.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class PurchaseEntity {

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

}
