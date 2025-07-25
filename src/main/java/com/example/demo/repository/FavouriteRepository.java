package com.example.demo.repository;

import com.example.demo.entity.favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface FavouriteRepository extends JpaRepository<favourite, Long> {
    @Query(value="select * from favourite where favourite.username=:username",nativeQuery = true)
    List<favourite> findFavourite(@Param("username") String username);
    @Modifying
    @Transactional
    @Query(value="insert into favourite(username,url) values(:username,:url)",nativeQuery = true)
    void insertFavourite(@Param("username") String username,@Param("url") String url);
}