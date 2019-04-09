package com.property.service;

import com.property.pojo.News;

import java.util.List;

public interface NewsService {

    int insertNews(News news);
    News findNewsByTitle(String title);
    Integer getNewsCount(Integer status);
    List<News> findNewsByPaging(Integer status, Integer page);
    News findNewsById(Integer id);
    int updateNews(News news);
    int deleteNews(Integer id);
    int moveNews2Trash(Integer id);
    int restoreNews(Integer id);
    List<News> getHottestNews();
    List<News> findNewsByKeyword(String keyword);
}
