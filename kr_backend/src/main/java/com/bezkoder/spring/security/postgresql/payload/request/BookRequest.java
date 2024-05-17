package com.bezkoder.spring.security.postgresql.payload.request;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BookRequest {
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

    public BookRequest() {
    }

    public BookRequest(String title, String authorName, String publisher, Integer publishYear, Double price, Integer count) {
        this.title = title;
        this.authorName = authorName;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.price = price;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
