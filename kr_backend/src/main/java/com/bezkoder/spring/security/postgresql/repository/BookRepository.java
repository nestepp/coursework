package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Boolean existsBookByTitleAndAuthorNameAndPublisher(String title, String authorName, String Publisher);
    @Query("FROM Book b WHERE b.title LIKE %:searchWord% OR b.authorName LIKE %:searchWord% OR b.publisher LIKE %:searchWord%")
    List<Book> getBooksSearch(String searchWord);
}
