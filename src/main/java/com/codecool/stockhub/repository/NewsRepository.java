package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("FROM News WHERE category LIKE :categoryName")
    @Modifying(clearAutomatically = true)
    List<News> getNewsByCategory(@Param("categoryName") String categoryName);

}
