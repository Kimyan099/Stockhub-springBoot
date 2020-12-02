package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("FROM News ORDER BY datetime DESC")
    @Modifying(clearAutomatically = true)
    List<News> getAllNewsDESC();

    @Query("FROM News ORDER BY datetime ASC")
    @Modifying(clearAutomatically = true)
    List<News> getAllNewsASC();

    @Query("FROM News WHERE category = :categoryName ORDER BY datetime DESC")
    @Modifying(clearAutomatically = true)
    List<News> getNewsByCategoryDESC(@Param("categoryName") String categoryName);

    @Query("FROM News WHERE category = :categoryName ORDER BY datetime ASC")
    @Modifying(clearAutomatically = true)
    List<News> getNewsByCategoryASC(@Param("categoryName") String categoryName);

    @Query("SELECT DISTINCT category FROM News")
    @Modifying(clearAutomatically = true)
    List<String> getAllNewsCategories();
}
