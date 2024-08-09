package com.inkwell.archives.repository;

import com.inkwell.archives.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

  Optional<BookEntity> findBookEntityByBookname(String bookname);


}
