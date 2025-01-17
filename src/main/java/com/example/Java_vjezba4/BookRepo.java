package com.example.Java_vjezba4;
import java.util.List;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;



@org.springframework.stereotype.Repository
public interface BookRepo extends JpaRepository<Book,Long> {

    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(String genre);
    List<Book> findByPublishedYear(int publishedYear);
    List<Book> findByTitleOrAuthor(String title, String author);

    @Query("SELECT b FROM Book b WHERE " +
            "( :title IS NULL OR b.title LIKE %:title%) AND " +
            "( :author IS NULL OR b.author LIKE %:author%) AND " +
            "( :genre IS NULL OR b.genre LIKE %:genre%) AND " +
            "( :publishedYear IS NULL OR b.publishedYear = :publishedYear) ")
    List<Book> findBooksByFilters(@Param("title") String title,
                                  @Param("author") String author,
                                  @Param("genre") String genre,
                                  @Param("publishedYear") Integer publishedYear,
                                  Pageable pageable);


}
