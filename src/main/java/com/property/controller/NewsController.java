package com.property.controller;

import com.property.controller.result.Result;
import com.property.pojo.News;
import com.property.service.NewsService;
import com.property.service.UserService;
import com.property.util.Md5;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Chichiu Yeung
 * Created in 2019/2/27 11:28
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Value("${resource.static.url}")
    private String url;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private NewsService newsService;
    private UserService userService;

    @Autowired
    public NewsController(NewsService newsService, UserService userService) {
        this.newsService = newsService;
        this.userService = userService;
    }

    /**
     * 图片上传
     * */
    @PostMapping("/uploadImage")
    public Result uploadImage(@RequestParam(value = "image") MultipartFile image) {
       return uploadFile(image, "images");
    }

    /**
     * 新闻海报上传
     * */
    @PostMapping("/uploadPoster")
    public Result uploadPoster(@RequestParam(value = "file") MultipartFile image) {
        return uploadFile(image, "posters");
    }

    private Result uploadFile(MultipartFile file, String path) {
        // String filepath = File.separator + "job" + File.separator + "file" + File.separator;
        String filepath = "D:" + File.separator + "file";
        logger.debug("filepath = " + filepath);
        String rootPath = filepath + path;
        // 文件路径不存在则需要创建文件路径
        File filePath=new File(rootPath);
        if (!filePath.exists()) {
            if (!filePath.mkdirs()) {
                return Result.error("服务器路径创建失败");
            }
        }
        // 文件名根据文件内容hash一下
        String originalName = file.getOriginalFilename();
        String fileName;
        try {
            fileName = Md5.digest(Arrays.toString(file.getBytes()))
                    + Objects.requireNonNull(originalName).substring(Objects.requireNonNull(originalName).lastIndexOf('.'));
            File realFile = new File(rootPath + File.separator + fileName);
            FileUtils.copyInputStreamToFile(file.getInputStream(), realFile);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return Result.error("服务器上传异常");
        }
        Result result = Result.success("上传成功");
        result.add("url", url + "/file/" + path +"/" + fileName);
        return result;
    }

    /**
     * 发表新闻
     * */
    @PostMapping("/publish")
    public Result publish(HttpServletRequest request) {
        String markdown = request.getParameter("markdown");
        String html = request.getParameter("html");
        String title = request.getParameter("title");
        String summary = request.getParameter("summary");
        String author = request.getParameter("author");
        String id = request.getParameter("id");
        String posterUrl = request.getParameter("posterUrl");
        Integer userId = userService.getUserIdByUsername(author);
        News news = new News.Builder()
                .html(html)
                .markdown(markdown)
                .title(title)
                .summary(summary)
                .author(userId.toString())
                .posterUrl(posterUrl)
                .build();
        try {
            if (id == null) {
                newsService.insertNews(news);
            } else {
                news.setId(Integer.parseInt(id));
                newsService.updateNews(news);
            }
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR + "(新闻插入出错)");
        }
        return Result.success();
    }

    /**
     * 检查新闻标题
     * */
    @GetMapping("/checkNewsTitle")
    public Result checkEssayTitle(@RequestParam("title") String title) {
        return newsService.findNewsByTitle(title) == null ? Result.success() : Result.error("标题已被占用");
    }

    /**
     * 分页检索新闻
     */
    @GetMapping("/getNewsByPaging")
    public Result getNewsByPaging(@RequestParam("page") Integer page, @RequestParam("status") Integer status) {
        List<News> list;
        Integer total;
        try {
            list = newsService.findNewsByPaging(status, page);
            total = newsService.getNewsCount(status);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR + "(查询新闻出错)");
        }
        Result result = Result.success();
        result.add("news", list);
        result.add("total", total);
        return result;
    }

    /**
     * 获取最新10条新闻
     * */
    @GetMapping("/getHottestNews")
    public Result getLatestNews() {
        try {
            return Result.success().add("news", newsService.getHottestNews());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    /**
     * 移动新闻到回收站
     * */
    @PostMapping("moveNews2Trash")
    public Result moveNews2Trash(@RequestParam("id") Integer id) {
        return newsService.moveNews2Trash(id) == 1 ? Result.success("删除成功") : Result.error("删除失败，请重试");
    }

    /**
     * 删除新闻
     * */
    @DeleteMapping("/deleteNews")
    public Result deleteNews(@RequestParam("id") Integer id) {
        return newsService.deleteNews(id) == 1 ? Result.success("删除成功") : Result.error("删除失败，请重试");
    }

    /**
     * 还原新闻
     * */
    @PostMapping("/restoreNews")
    public Result restoreNews(@RequestParam("id") Integer id) {
        return newsService.restoreNews(id) == 1 ? Result.success("还原成功") : Result.error("还原失败，请重试");
    }

    /**
     * 按关键字查询职位信息
     * */
    @GetMapping("/getNewsByKeyword")
    public Result getNewsByKeyword(@RequestParam("keyword") String keyword) {
        try {
            return Result.success().add("news", newsService.findNewsByKeyword(keyword));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }
}
