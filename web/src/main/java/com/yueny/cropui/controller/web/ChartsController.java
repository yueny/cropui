package com.yueny.cropui.controller.web;

import javax.servlet.http.HttpServletResponse;

import com.yueny.blog.common.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueny.blog.bo.model.document.ChartData;
import com.yueny.blog.service.manager.ITagChartManagerService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.data.resp.pojo.response.JsonNormalResponse;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 报表控制器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月12日 下午8:17:40
 *
 */
@Controller
public class ChartsController extends BaseController {
	@Autowired
	private ITagChartManagerService tagQueryService;

	/**
	 * 报表
	 */
	@RequestMapping(value = "/charts/", method = { RequestMethod.GET })
	public String articleWritePage(@RequestParam(value = "type", defaultValue = "1") final String type,
			final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "fks_chart");

		return BlogConstant.WEB_PAGE_URI_PREFIX + "charts/fks_chart";
	}

	/**
	 * 报表数据
	 */
	@RequestMapping(value = "/charts/front-end.json", method = { RequestMethod.POST })
	@ResponseBody
	@CrossOrigin // 允许跨域
	public JsonNormalResponse<ChartData> chartsFrontEndJson(final HttpServletResponse response) {
		final JsonNormalResponse<ChartData> res = new JsonNormalResponse<>();
		try {
			// 从session中获取uid
			final String uid = "yuanyang";

			final ChartData chartData = tagQueryService.getChartData(uid);
			res.setData(chartData);
		} catch (final DataVerifyAnomalyException e) {
			res.setCode(e.getErrorCode());
			res.setMessage(e.getErrorMsg());
		}
		return res;
	}

}
