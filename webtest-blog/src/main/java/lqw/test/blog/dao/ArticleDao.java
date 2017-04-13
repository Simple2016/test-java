package lqw.test.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import lqw.test.blog.domain.Article;

/** 
 *
 * @ClassName   类名：ArticleDao
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年10月25日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年10月25日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public interface ArticleDao {

    void addArticle(Article article);

    void updateArticle(Article article);

    void delArticleById(@Param("articleId") int articleId);

    Article queryArticleById(@Param("articleId") int articleId);

    List<Article> queryArticle(Map<String, Object> params);

    int queryArticleCount(Map<String, Object> params);

}
