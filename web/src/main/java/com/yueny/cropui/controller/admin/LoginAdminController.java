package com.yueny.cropui.controller.admin;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yueny.blog.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueny.blog.common.BlogConstant;
import com.yueny.blog.common.util.CurrentUserUtils;
import com.yueny.blog.console.vo.user.UserDisplayVo;
import com.yueny.blog.service.admin.manager.ILoginManager;
import com.yueny.blog.service.admin.manager.IUserDisplayManager;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.data.resp.pojo.response.JsonNormalResponse;
import com.yueny.rapid.lang.enums.BaseErrorType;
import com.yueny.rapid.lang.exception.invalid.InvalidException;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 后台admin控制器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月12日 下午8:17:40
 */
@Controller
@RequestMapping(value = BlogConstant.ADMIN_URL_PREFIX)
public class LoginAdminController extends BaseController {
	@Autowired
	private ILoginManager loginManager;
	@Autowired
	private IUserDisplayManager userDisplayManager;
	@Autowired
	private IProfileService profileService;

	/**
	 * 登录行为post
	 */
	@RequestMapping(value = "/login/dologin", method = { RequestMethod.POST })
	@ResponseBody
	public JsonNormalResponse<Boolean> dologin(@RequestParam(value = "username") final String username,
			@RequestParam(value = "password") final String password, final HttpServletResponse response) {
		final JsonNormalResponse<Boolean> res = new JsonNormalResponse<>();
		res.setData(false);

		try {
			final HttpSession session = getSession();
			if (session.getAttribute(CurrentUserUtils.LOGIN_DIST_NAME) != null) {
				// 已登录
				res.setData(true);
			} else {
				final boolean getMatch = loginManager.login(username, password);

				if (getMatch) {
					final UserDisplayVo dis = userDisplayManager.display(username);
					session.setAttribute(CurrentUserUtils.LOGIN_DIST_NAME, dis);
				}

				res.setData(getMatch);
			}
		} catch (final InvalidException e) {
			res.setCode(e.getErrorCode());
			res.setMessage(e.getErrorMsg());
		} catch (final Exception e) {
			res.setCode(BaseErrorType.SYSTEM_BUSY.getCode());
			res.setMessage(BaseErrorType.SYSTEM_BUSY.getMessage());

			logger.error("用户登陆异常：", e);
		}

		return res;
	}

	/**
	 * 退出登录行为
	 */
	@RequestMapping(value = "/login/dologout", method = { RequestMethod.POST })
	@ResponseBody
	public JsonNormalResponse<Boolean> dologout(final HttpServletResponse response) {
		final JsonNormalResponse<Boolean> res = new JsonNormalResponse<>();

		final HttpSession session = getSession();
		session.removeAttribute(CurrentUserUtils.LOGIN_DIST_NAME);

		res.setData(true);

		return res;
	}

	/**
	 * 后台登录页
	 */
	@RequestMapping(value = "/login/login.html")
	public String login(final HttpServletResponse response) {
		final HttpSession session = getSession();
		if (session.getAttribute(CurrentUserUtils.LOGIN_DIST_NAME) != null) {
			// 已登录，重定向到主页
			return redirectAction(BlogConstant.ADMIN_URL_PREFIX + "/welcome.html");
		}

		setModelAttribute(WebAttributes.ACTION, "LOGIN");
		setModelAttribute("title", "登录");
		setModelAttribute("profile", profileService.profileType().getDesc());

		// 定向到页面
		return BlogConstant.ADMIN_PAGE_URI_PREFIX + "login";
	}

}
