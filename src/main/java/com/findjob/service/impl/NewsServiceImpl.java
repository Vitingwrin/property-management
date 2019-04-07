package com.findjob.service.impl;

import com.findjob.mapper.NewsMapper;
import com.findjob.pojo.News;
import com.findjob.service.NewsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/3/6 11:55
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NewsServiceImpl implements NewsService {

    private NewsMapper newsMapper;

    @Autowired
    public NewsServiceImpl(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @Override
    public int insertNews(News news) {
        return newsMapper.insertNews(news);
    }

    @Override
    public News findNewsByTitle(String title) {
        return newsMapper.findNewsByTitle(title);
    }

    @Override
    public Integer getNewsCount(Integer status) {
        return newsMapper.getNewsCount(status);
    }

    @Override
    public List<News> findNewsByPaging(Integer status, Integer page) {
        return newsMapper.findNewsByPaging(status, (page - 1) * 10);
    }

    @Override
    public News findNewsById(Integer id) {
        return newsMapper.findNewsById(id);
    }

    @Override
    public int updateNews(News news) {
        return newsMapper.updateNews(news);
    }

    @Override
    public int deleteNews(Integer id) {
        return newsMapper.deleteNews(id);
    }

    @Override
    public int moveNews2Trash(Integer id) {
        return newsMapper.moveNews2Trash(id);
    }

    @Override
    public int restoreNews(Integer id) {
        return newsMapper.restoreNews(id);
    }

    @Override
    public List<News> getHottestNews() {
        return newsMapper.getHottestNews();
    }

    @Override
    public List<News> findNewsByKeyword(String keyword) {
        return newsMapper.findNewsByKeyword(keyword);
    }


}
