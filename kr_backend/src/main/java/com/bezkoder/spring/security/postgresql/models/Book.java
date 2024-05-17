package com.bezkoder.spring.security.postgresql.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название должно быть от 2 до 100 символов длиной")
    @Column(name = "title")
    private String title;

    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов длиной")
    @Column(name = "author_name")
    private String authorName;

    @NotEmpty(message = "Издателство не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя издательства должно быть от 2 до 100 символов длиной")
    @Column(name = "publisher")
    private String publisher;

    private Integer publishYear;

    private Double price;

    private Integer count;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }

    public Book(String title, String authorName, String publisher, Integer publishYear, Double price, Integer count) {
        this.title = title;
        this.authorName = authorName;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.price = price;
        this.count = count;
    }

    public Book() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
