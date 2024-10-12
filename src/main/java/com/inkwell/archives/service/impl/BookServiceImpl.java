package com.inkwell.archives.service.impl;

import com.inkwell.archives.model.BookEntity;
import com.inkwell.archives.repository.BookRepository;
import com.inkwell.archives.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService
{
  private BookRepository bookRepository;

  @Autowired
  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public List<BookEntity> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public BookEntity findByBookId(int bookId) {
    Optional<BookEntity> result = bookRepository.findById(bookId);

    BookEntity theBook = null;

    if(result.isPresent()) {
      theBook = result.get();
    } else {
      throw new RuntimeException("Finding the requested book was not possible" + bookId);
    }
    return theBook;
  }

  @Override
  public BookEntity findByTitle(String bookTitle) {
    Optional<BookEntity> result = bookRepository.findByBookTitle(bookTitle);

    BookEntity theBookTitle = null;

    if(result.isPresent()) {
      theBookTitle = result.get();
    } else {
      throw new RuntimeException("Finding the requested book title was not possible" + bookTitle);
    }
    return theBookTitle;

  }

  @Override
  public BookEntity save(BookEntity theBook) {
    return bookRepository.save(theBook);
  }

  @Override
  public void deleteByBookId(int id) {
    bookRepository.deleteById(id);
  }
}
