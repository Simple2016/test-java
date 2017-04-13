package lqw.test.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lqw.test.blog.dao.ArticleDao;
import lqw.test.blog.domain.Article;
import lqw.test.blog.service.ArticleService;
import lqw.test.blog.util.NumberHelper;

/** 
 *
 * @ClassName   类名：ArticleServiceImpl
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年11月3日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年11月3日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void addAticle(Article article) {
        subDetail(article);
        articleDao.addArticle(article);
    }

    @Override
    public void delAticleById(int articleId) {
        articleDao.delArticleById(NumberHelper.decode(articleId));
    }

    @Override
    public void updateAticle(Article article) {
        subDetail(article);
        article.setArticleldId(NumberHelper.decode(article.getArticleldId()));
        articleDao.updateArticle(article);
    }

    @Override
    public List<Article> queryAticle(Map params) {
        return articleDao.queryArticle(params);
    }

    @Override
    public int queryAticleCount(Map params) {
        return articleDao.queryArticleCount(params);
    }

    @Override
    public Article queryAticleById(int articleId) {
        return articleDao.queryArticleById(NumberHelper.decode(articleId));
    }

    // 生成摘要
    private void subDetail(Article article) {
        String detail = article.getDetail();
        detail = detail.replaceAll("<[^>]*>", "");
        detail = detail.replaceAll("\\s+", " ");
        String content = detail;
        if (detail.length() > 30) {
            content = detail.substring(0, 27);
            if (detail.length() > content.length()) {
                content += "...";
            }
        }
        article.setContent(content);
    }

}
