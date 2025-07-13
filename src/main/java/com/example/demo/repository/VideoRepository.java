package com.example.demo.repository;

import com.example.demo.entity.Video;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VideoRepository extends JpaRepository<Video, Long> {
    @Modifying
    @Transactional
    @Query(value="insert into video(title,author,picture) values(:title,:author,:picture)",nativeQuery = true)
    void insertVideo(@Param("title")String title,@Param("author")String author,@Param("picture")String picture);
}