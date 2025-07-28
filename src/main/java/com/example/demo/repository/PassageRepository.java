package com.example.demo.repository;

import com.example.demo.entity.Passage;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface PassageRepository extends JpaRepository<Passage, Long> {
    @Modifying
    @Transactional
    @Query(value="insert into passage(title,author,picture,content) values(:title,:author,:picture,:content)",nativeQuery = true)
    void insertVideo(@Param("title")String title,@Param("author")String author,@Param("picture")String picture,@Param("content")String content);
    @Query(value="select * from passage where passage.picture=:picture",nativeQuery = true)
    Passage GetVideo(@Param("picture")String picture);

    
}