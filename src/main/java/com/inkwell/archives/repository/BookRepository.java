package com.inkwell.archives.repository;

import com.inkwell.archives.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSInput;

import javax.swing.*;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
  Optional<BookEntity> findByBookTitle(String bookTitle);
  List<BookEntity> findAll();
}
