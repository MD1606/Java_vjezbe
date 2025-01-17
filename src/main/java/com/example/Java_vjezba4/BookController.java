package com.example.Java_vjezba4;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exceptions.*;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    ResponseEntity<List<Book>> searchBy(@RequestParam(value="title",required = false) String title,
                                        @RequestParam(value = "author", required = false) String author,
                                        @RequestParam(value="genre",required = false) String genre,
                                        @RequestParam(value="publishedYear",required = false) Integer publishedYear,
                                        @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam String sortBy,
                                        @RequestParam(defaultValue = "asc")  String sortDir)
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));

        List<Book> books = bookService.findBooksByFilters(title, author, genre, publishedYear,pageable);

        if (books.isEmpty()) {
            //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            throw new BookNotFoundException("No books found for the given criteria.");
        }

        return ResponseEntity.ok(books);

    }

    @GetMapping("/boo")
    ResponseEntity<List<Book>> filterByTitleOrAuthor(@RequestParam(value="title",required = false) String title,
                                      @RequestParam(value = "author", required = false) String author){

        if (title == null && author == null) {
            throw new InvalidRequestException("At least one of 'title' or 'author' must be provided.");
        }

        List<Book> books = bookService.getByTitleOrAuthor(title,author);

        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found for the given criteria.");
        }

        return ResponseEntity.ok(books);
    }


    @GetMapping
    public List<Book> findAll(){
        return bookService.getBooks();
    }


    @DeleteMapping("/Model/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book with ID " + id + " has been deleted.");
    }

    @PostMapping(value="/save")
    public ResponseEntity<String> saveBook(@RequestBody Book book){
        bookService.saveB(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book saved successfully.");
    }

   /* @PutMapping(value = "update/{id}")
    public String updateBook(@PathVariable long id, @RequestBody Book book){
        Book updateBook=bookService.getById(id).get();
        updateBook.setTitle(book.getTitle());
        updateBook.setAuthor(book.getAuthor());
        bookService.saveB(updateBook);
        return "updated...";
    }*/
}
