package com.yueny.cropui.controller.web;

import javax.servlet.http.HttpServletResponse;

import com.yueny.blog.common.BlogConstant;
import com.yueny.rapid.data.resp.pojo.response.NormalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yueny.blog.bo.article.ArticleBlogBo;
import com.yueny.blog.bo.article.ArticleBlogViewBo;
import com.yueny.blog.service.manager.IArticleQueryManageService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 【文章显示】控制器
 *
 * @author 袁洋 2015年8月25日 上午10:18:37
 *
 */
@Controller
public class ArticleBlogDetailShowActionController extends BaseController {
	@Autowired
	private IArticleQueryManageService articleQueryService;

	/**
	 * 查看 html 文章详细页面， md
	 *
	 * @param articleBlogId
	 *            文章扩展ID
	 */
	@RequestMapping(value = "/article/{articleBlogId}.html", method = { RequestMethod.GET })
	public String getArticleInfoPage(@PathVariable final String articleBlogId, final HttpServletResponse response) {
		logger.info("【查看 html 文章详细页面】入参:{}", articleBlogId);

//		return redirectAction("/article/"+articleBlogId+".htm");

		setModelAttribute(WebAttributes.ACTION, "articleInfo");

		try {
			// 从session中获取uid
			// final String uid = "yuanyang";
			final ArticleBlogViewBo articleBlogView = articleQueryService.getArticleInfo(articleBlogId);
//			setModelAttribute("articleBlogView", articleBlogView);

			// 当前博文
			final ArticleBlogBo articleBlog = articleBlogView.getArticleBlog();

			setModelAttribute("title", articleBlog.getArticleTitle());
			setModelAttribute("articleBlog", articleBlog);
			// 文章的个人分类信息
			setModelAttribute("owenerTags", articleBlogView.getOwenerTags());
			// 该博文所归属的全站文章分类
			setModelAttribute("categoriesTagList", articleBlogView.getCategoriesTagList());
			// 上一篇博文
			setModelAttribute("previousSimpleBlog", articleBlogView.getPreviousSimpleBlog());
			// 下一篇博文
			setModelAttribute("nextSimpleBlog", articleBlogView.getNextSimpleBlog());

			// 文章标签articleTagIds

		} catch (final DataVerifyAnomalyException e) {
			logger.error("【查看文章详细】出现错误!", e);
			// 文章出错,回首页
			return redirectAction("/");
		}

		return BlogConstant.WEB_PAGE_URI_PREFIX + "blog/article_blog_detail_md";
	}

	/**
	 * 查看 md 文章详细页面
	 *
	 * @param articleBlogId
	 *            文章扩展ID
	 */
	@Deprecated
	@RequestMapping(value = "/article/{articleBlogId}.htm", method = { RequestMethod.GET })
	public String getArticleInfoMdPage(@PathVariable final String articleBlogId, final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "articleInfo");

		try {
			// 从session中获取uid
			// final String uid = "yuanyang";
			final ArticleBlogViewBo articleBlogView = articleQueryService.getArticleInfo(articleBlogId);
			// 当前博文
			final ArticleBlogBo articleBlog = articleBlogView.getArticleBlog();

			setModelAttribute("title", articleBlog.getArticleTitle());
			setModelAttribute("articleBlog", articleBlog);
			// 文章的个人分类信息
			setModelAttribute("owenerTags", articleBlogView.getOwenerTags());
			// 该博文所归属的全站文章分类
			setModelAttribute("categoriesTagList", articleBlogView.getCategoriesTagList());
			// 上一篇博文
			setModelAttribute("previousSimpleBlog", articleBlogView.getPreviousSimpleBlog());
			// 下一篇博文
			setModelAttribute("nextSimpleBlog", articleBlogView.getNextSimpleBlog());

			// 文章标签articleTagIds

		} catch (final DataVerifyAnomalyException e) {
			logger.error("【查看文章详细】出现错误!", e);
			// 文章出错,回首页
			return redirectAction("/");
		}

		return BlogConstant.WEB_PAGE_URI_PREFIX + "blog/article_blog_detail_md";
	}

	/**
	 * 查看 md 文章数据
	 *
	 * @param articleBlogId
	 *            文章扩展ID
	 */
	@RequestMapping(value = "/article/{articleBlogId}.json", method = { RequestMethod.POST })
	@ResponseBody
	@Deprecated
	public NormalResponse<ArticleBlogViewBo> getArticleInfoMdData(@PathVariable final String articleBlogId, final HttpServletResponse response) {
		logger.info("【查看 md 文章数据】入参:{}", articleBlogId);

		setModelAttribute(WebAttributes.ACTION, "articleInfo");

		NormalResponse<ArticleBlogViewBo> resp = new NormalResponse<>();
		resp.setMessage("查询成功");

		try {
			// 从session中获取uid
			// final String uid = "yuanyang";
			final ArticleBlogViewBo articleBlogView = articleQueryService.getArticleInfo(articleBlogId);
			resp.setData(articleBlogView);
		} catch (final DataVerifyAnomalyException e) {
			logger.error("【查看文章详细】出现错误!", e);
			resp.setCode(e.getErrorCode());
			resp.setMessage(e.getMessage());
		}

		logger.info("【查看 md 文章数据】响应:{}", resp);
		return resp;
	}


}
