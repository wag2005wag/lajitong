package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String url;
    public favourite(){

    }
    public favourite(String username,String url){
        this.username=username;
        this.url=url;
    }
    public String getUsername(){
        return this.username;
    }
    public String getUrl(){
        return this.url;
    }

}
