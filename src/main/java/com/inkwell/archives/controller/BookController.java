package com.inkwell.archives.controller;

import com.inkwell.archives.model.BookEntity;
import com.inkwell.archives.model.DataSourceEntity;
import com.inkwell.archives.repository.DataSourceRepository;
import com.inkwell.archives.service.impl.DataSourceServiceImpl;
import com.inkwell.archives.service.interfaces.BookService;
import com.inkwell.archives.service.interfaces.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.w3c.dom.ls.LSInput;

import java.awt.print.Book;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// User role is mandatory
@RestController
@CrossOrigin
@RequestMapping("/books")
public class BookController {
  private final BookService bookService;
  private final DataSourceService dataSourceService;
  @Autowired
  public BookController(BookService bookService, DataSourceService dataSourceService) {
    this.bookService = bookService;
    this.dataSourceService = dataSourceService;
  }

  // Get all the books -------
  @GetMapping
  public ResponseEntity<List<BookEntity>> getAllBooks() {
    List<BookEntity> books = bookService.findAll();
    return ResponseEntity.ok(books);
  }

  // Create Book ----------
  @PostMapping
  public ResponseEntity<BookEntity> createBook(@RequestBody BookEntity bookEntity) {

    // Get all the data sources stored
    List<DataSourceEntity> dataSourceList = dataSourceService.findAll();

    DataSourceEntity entry = bookEntity.getDataSource();

    DataSourceEntity dataSource = dataSourceList.stream()
            .filter(source -> source.getSourceName().equals(entry.getSourceName()))
            .findFirst().orElse(null);

    // Validation
    if(bookEntity == null) return ResponseEntity.badRequest().build();

    bookEntity.setDataSource(dataSource);

    BookEntity createdBook = bookService.save(bookEntity);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdBook.getId())
            .toUri();
    return ResponseEntity.created(location).body(createdBook);
  }

  // Update book information by book_id ------------
  @PutMapping("/{id}")
  public ResponseEntity<BookEntity> updateBookInformation(
          @PathVariable int id, @RequestBody BookEntity bookDetails) {
    // Validation
    if(bookDetails == null) return ResponseEntity.badRequest().build();

    BookEntity updateBook = bookService.updateByBookId(id, bookDetails);
    return ResponseEntity.ok(updateBook);
  }

  // Delete book --------------
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBookByID(@PathVariable int id) {
    if(bookService.findByBookId(id) != null) {
      bookService.deleteByBookId(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
