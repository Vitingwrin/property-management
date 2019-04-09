package com.property.mapper;

import com.property.pojo.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/3/6 11:53
 */
@Component
public interface NewsMapper {

    int insertNews(News news);
    News findNewsByTitle(String title);
    Integer getNewsCount(Integer status);
    List<News> findNewsByPaging(@Param("status") Integer status, @Param("offset") Integer offset);
    News findNewsById(Integer id);
    int updateNews(News news);
    int deleteNews(Integer id);
    int moveNews2Trash(Integer id);
    int restoreNews(Integer id);
    List<News> getHottestNews();
    List<News> findNewsByKeyword(String keyword);
}
