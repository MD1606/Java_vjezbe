package com.example.Java_vjezba4;

import jakarta.persistence.*;

@Entity
@Table
public class Book{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String genre;
    @Column
    private int publishedYear;

    public Book(){}

    public Book(int id, String title, String author,String genre, int publishedYear) {
        this.id = id;
        this.title = title;
        this.author= author;
        this.genre=genre;
        this.publishedYear=publishedYear;
    }

    public Book(String title, String author,String genre, int publishedYear) {
        this.title = title;
        this.author= author;
        this.genre=genre;
        this.publishedYear=publishedYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublishedYear() {
        return publishedYear;
    }
    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }



    @Override
    public String toString() {
        return "model{" +
                "id=" + id +
                " title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre +
                "publishedYear=" + publishedYear +
                '}';
    }
}

