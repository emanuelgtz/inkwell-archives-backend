package com.inkwell.archives.service.interfaces;

import com.inkwell.archives.model.BookEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface BookService {
  List<BookEntity> findAll();
  BookEntity findByBookId(int bookId);
  BookEntity findByTitle(String bookTitle);
  BookEntity updateByBookId(int bookId, BookEntity details);
  BookEntity save(BookEntity theBook);
  void deleteByBookId(int id);
}
