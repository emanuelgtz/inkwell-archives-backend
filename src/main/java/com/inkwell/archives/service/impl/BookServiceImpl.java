package com.inkwell.archives.service.impl;

import com.inkwell.archives.model.BookEntity;
import com.inkwell.archives.model.DataSourceEntity;
import com.inkwell.archives.repository.BookRepository;
import com.inkwell.archives.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
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

  // Update information of the book
  @Override
  public BookEntity updateByBookId(int bookId, BookEntity details) {
    // Retrieve the existing book from the repository
    BookEntity currentBook =
            bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

    // Update the books properties with the new values
    currentBook.setBookTitle(details.getBookTitle());
    currentBook.setBookPrice(details.getBookPrice());
    currentBook.setBookStock(details.getBookStock());
    currentBook.setUpcCode(details.getUpcCode());
    currentBook.setBookCategory(details.getBookCategory());
    currentBook.setDataSource(details.getDataSource());
    return bookRepository.save(currentBook);
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
