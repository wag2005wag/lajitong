package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Passage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String picture;
    private String title;
    private String author;
    private String content;
    public Passage() {
    }
    public Passage(String picture,String title,String author,String content){
        this.picture=picture;
        this.title=title;
        this.author=author;
        this.content=content;
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

    public String getContent(){
        return this.content;
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

    public void setContent(String content){
        this.content=content;
    }
}
