package com.tp.model;

import com.tp.service.BookService;

import java.time.LocalDateTime;

public class Book {


    private String book_id;
    private String title;
    private String author;
    private int year;
    private String image;
    private String category;
    private  String description;
    private boolean is_available;
    private int loan_count;
    private LocalDateTime created_at;



    public Book(String id_book , String title , String author , int year_publication , String image, String category , String description , boolean is_available , int loan_count , LocalDateTime created_at){
        this.book_id = id_book;
        this.title = title;
        this.author = author;
        this.year = year_publication;
        this.image = image;
        this.category = category;
        this.description = description;
        this.is_available = is_available;
        this.loan_count = loan_count;
        this.created_at = created_at;
    }

    public String getId_Book(){
        return this.book_id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return this.author;
    }
    public int getYear(){
        return this.year;
    }
    public String getImage(){ return this.image; }
    public String getCategory(){
        return this.category;
    }
    public String getDescription(){return this.description;}
    public boolean getIs_available(){ return this.is_available; }
    private int getLoan_count(){ return this.loan_count; }
    private LocalDateTime getCreated_at(){return this.created_at; }
    public void setId_Book(String id_book){
        this.book_id = id_book;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public  void setYear(int year_Publication){
        this.year = year_Publication;
    }
    public void setImage(String image ){ this.image = image; }
    public void setCategory(String category){
        this.category = category;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setIs_available(boolean is_available ){ this.is_available = is_available; }
    public void setLoan_count(int loan_count ){ this.loan_count = loan_count; }

}
