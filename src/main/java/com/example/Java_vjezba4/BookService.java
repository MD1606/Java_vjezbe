package com.example.Java_vjezba4;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;



@Service
public class BookService {

    private final BookRepo repo;

    @Autowired
    public BookService (BookRepo repo){
        this.repo=repo;
    }

   public List<Book> getBooks()
    {
        return repo.findAll();
    }

    public List<Book> getSort(Pageable pageable)
    {
        return repo.findAll(pageable).getContent();
    }


    public void deleteBook(Long id){
        repo.deleteById(id);
    }

    public Optional<Book> getById(Long id){
        return repo.findById(id);
    }

    public List<Book> getByTitle(String title){
        return repo.findByTitle(title);
    }

    public List<Book> getByGenre(String genre){
        return repo.findByGenre(genre);
    }

    public List<Book> getByPublishedYear(int publishedYear){ return repo.findByPublishedYear(publishedYear); }

    public List<Book> getByAuthor(String author){
        return repo.findByAuthor(author);
    }

    public void saveB(Book book){
        repo.save(book);
    }

    public List<Book> getByTitleOrAuthor(String title, String author) {
        return repo.findByTitleOrAuthor(title, author);
    }
    public List<Book> findBooksByFilters(@Param("title") String title,
                                        @Param("author") String author,
                                        @Param("genre") String genre,
                                        @Param("publishedYear") Integer publishedYear,
                                        Pageable pageable)
    {
        return repo.findBooksByFilters(title,author,genre,publishedYear,pageable);
    }


}
