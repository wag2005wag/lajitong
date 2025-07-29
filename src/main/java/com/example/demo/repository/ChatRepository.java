package com.example.demo.repository;
import com.example.demo.entity.chat;
import com.example.demo.entity.favourite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ChatRepository extends JpaRepository<chat, Long> {
    @Query(value="select * from chat order by id asc limit :page,10",nativeQuery = true)
    List<chat> getChat(@Param("page") int page);
    @Modifying
    @Transactional
    @Query(value="insert into chat(username,content) values(:username,:content)",nativeQuery = true)
    void insertChat(@Param("username") String username,@Param("content") String content);
}
