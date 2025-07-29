package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class chat{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String content;
    public chat(){

    }
    public chat(String author,String content){
        this.username=author;
        this.content=content;
    }

    String getUsername(){
        return this.username;
    }
    String getContent(){
        return this.content;
    }
}