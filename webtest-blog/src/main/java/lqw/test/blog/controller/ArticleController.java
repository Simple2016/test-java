package lqw.test.blog.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lqw.test.blog.domain.Article;
import lqw.test.blog.service.ArticleService;
import lqw.test.blog.util.JsonHelper;
import lqw.test.blog.util.cacheHelper;

/**
 *
 * @ClassName 类名：LoginController
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2016年11月8日
 * @author 创建人： liqw
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2016年11月8日 liqw 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Controller
public class ArticleController {

	final Logger log = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "index.do", method = RequestMethod.POST)
	public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.debug("---index---");

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("group", request.getParameter("group"));
		paramsMap.put("search", request.getParameter("search"));
		int pageNumber = 0;
		int itemCount = 10;
		if (request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			paramsMap.put("start", pageNumber * itemCount - 1 > 0 ? pageNumber * itemCount - 1 : 0);
		}
		paramsMap.put("count", 10);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int pageCount = articleService.queryAticleCount(paramsMap);
		pageCount = pageCount > 0 ? (pageCount % itemCount > 0 ? pageCount / itemCount + 1 : pageCount / itemCount) : 1;

		resultMap.put("result", articleService.queryAticle(paramsMap));
		resultMap.put("pageCount", pageCount);
		resultMap.put("pageNumber", pageNumber);
		System.err.println(request.getSession().getId());
		if (cacheHelper.has(request.getSession().getId())) {
			resultMap.put("logined", "true");
		}else{
			resultMap.put("logined", "false");
		}
		String result = JsonHelper.toJson(resultMap);
		log.debug(result);
		response.getWriter().println(result);
	}

	@RequestMapping(value = "addArticle.do", method = RequestMethod.POST)
	public void addArticle(@RequestParam String json, @RequestParam String group, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		log.debug("---addArticle---");
		Article article = JsonHelper.fromJson(json, Article.class);
		article.setGroup(group);
		article.setCreateTime(new Date());
		if (cacheHelper.has(request.getSession().getId())) {
			articleService.addAticle(article);
			response.getWriter().println("success");
		}else{
			response.getWriter().println("false");
		}
	}

	@RequestMapping(value = "modifyArticle.do", method = RequestMethod.POST)
	public void modifyArticle(@RequestParam String json, @RequestParam String group, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		log.debug("---modifyArticle---");
		log.debug("---------------->" + json);

		Article article = JsonHelper.fromJson(json, Article.class);
		article.setModifyTime(new Date());
		article.setGroup(group);
		if (cacheHelper.has(request.getSession().getId())) {
			articleService.updateAticle(article);
			response.getWriter().println("success");
		}else{
			response.getWriter().println("false");
		}
	}

	@RequestMapping(value = "getArticleById.do", method = RequestMethod.POST)
	public void getArticleById(@RequestParam int articleId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.debug("---getArticleById---");
		Map<String, Article> resultMap = new HashMap<String, Article>();
		resultMap.put("result", articleService.queryAticleById(articleId));
		String result = JsonHelper.toJson(resultMap);
		response.getWriter().println(result);
	}

	@RequestMapping(value = "delArticleById.do", method = RequestMethod.POST)
	public void delArticleById(@RequestParam int Id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.debug("---delArticleById---");
		if (cacheHelper.has(request.getSession().getId())) {
			articleService.delAticleById(Id);
			response.getWriter().println("success");
		}else{
			response.getWriter().println("false");
		}
	}

}
