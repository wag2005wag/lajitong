package com.example.demo.entity;

public class Video {
    private String picture;
    private String title;
    private String author;

    public Video(String picture,String title,String author){
        this.picture=picture;
        this.title=title;
        this.author=author;
    }

    public String getPicture(){
        return this.picture;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setPicture(String picture){
        this.picture=picture;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setAuthor(String author){
        this.author=author;
    }
}
