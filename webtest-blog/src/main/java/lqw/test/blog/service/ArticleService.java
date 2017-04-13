package lqw.test.blog.service;

import java.util.List;
import java.util.Map;

import lqw.test.blog.domain.Article;

/** 
 *
 * @ClassName   类名：ArticleService
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

public interface ArticleService {

    public void addAticle(Article article);

    public void delAticleById(int articleId);

    public void updateAticle(Article article);

    public List<Article> queryAticle(Map params);

    public int queryAticleCount(Map params);

    public Article queryAticleById(int articleId);

}