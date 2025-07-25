
package com.example.demo.service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.example.demo.entity.favourite;
import org.springframework.stereotype.Service;
import com.example.demo.repository.FavouriteRepository;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class FavouriteService {
    private FavouriteRepository favouriteRepository;
    public FavouriteService(FavouriteRepository favouriteRepository){
        this.favouriteRepository=favouriteRepository;
    }
    public List<favourite>getFavourite(String username){
        return favouriteRepository.findFavourite(username);
    }
    public void insertFavourite(String username,String url){
        favouriteRepository.insertFavourite(username, url);
    }
}
