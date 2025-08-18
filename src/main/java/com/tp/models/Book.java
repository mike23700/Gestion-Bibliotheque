package com.tp.models;

public class Book {
    private String Id_Book;
    private String Title;
    private String Author;
    private int Year_Publication;
    private String Category;
    private  String Description;
    private String Image_Path;

    public Book(String id_book , String title , String author , int year_publication , String category , String description , String image_path){
        this.Id_Book = id_book;
        this.Title = title;
        this.Author = author;
        this.Year_Publication = year_publication;
        this.Category = category;
        this.Description = description;
        this.Image_Path = image_path;
    }

    public String getId_Book(){
        return this.Id_Book;
    }
    public String getTitle(){
        return this.Title;
    }
    public String getAuthor(){
        return this.Author;
    }
    public int getYear_Publication(){
        return this.Year_Publication;
    }
    public String getCategory(){
        return this.Category;
    }
    public String getDescription(){
        return this.Description;
    }
    public String getImage_Path(){
        return this.Image_Path;
    }
    public void setId_Book(String id_book){
        this.Id_Book = id_book;
    }
    public void setTitle(String title){
        this.Title = title;
    }
    public void setAuthor(String author){
        this.Author = author;
    }
    public  void setYear_Publication(int year_Publication){
        this.Year_Publication = year_Publication;
    }
    public void setCategory(String category){
        this.Category = category;
    }
    public void setDescription(String description){
        this.Description = description;
    }
    public void setImage_Path(String image_Path){
        this.Image_Path = image_Path;
    }
}
