package com.razborka.service.Impl;

import com.razborka.dao.NewsDao;
import com.razborka.model.News;
import com.razborka.service.NewsService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.05.2015.
 */
@Service("newsService")
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    public void saveNews(News news) {
        news.setDate(LocalDateTime.now());
        newsDao.save(news);
    }

    public void updateNews(News news) {
        newsDao.update(news);
    }

    public void deleteNews(int id) {
        newsDao.deleteById(id);
    }

    public News getNews(int id) {
        return newsDao.get(id);
    }

    public List<News> getAllNews() {
        return newsDao.getAll();
    }

    public List<News> getAllPublicNews() {
        return newsDao.getAllPublicNews();
    }
}
