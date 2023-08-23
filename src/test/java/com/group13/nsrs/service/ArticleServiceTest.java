package com.group13.nsrs.service;

import com.group13.nsrs.NsrsApplication;
import com.group13.nsrs.model.vo.ArticleVo;
import com.group13.nsrs.util.result.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/22
 */
@SpringBootTest(classes = NsrsApplication.class)
@RunWith(SpringRunner.class)
public class ArticleServiceTest {
    @Resource
    private ArticleService articleService;

    @Test
    public void testComputeHotArticles() {
        articleService.computeHotArticle();
    }

    @Test
    public void testListHotArticles() {
        Result<List<ArticleVo>> listResult = articleService.listHotArticles();
        System.out.println(listResult.getData().get(0).getId());
    }
}
