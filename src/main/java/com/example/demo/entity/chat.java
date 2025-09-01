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
    private String url;
    public chat(){

    }
    public chat(String username,String content,String url){
        this.username=username;
        this.content=content;
        this.url=url;
    }

    public String getUsername(){
        return this.username;
    }
    public String getContent(){
        return this.content;
    }
    public String getUrl(){
        return this.url;
    }
}