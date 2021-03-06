// package com.yueny.blog.service.integration.impl;
//
// import java.util.Collections;
// import java.util.List;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
//
// import com.yueny.blog.service.integration.ICmsConfigQueryIntegration;
// import com.yueny.cms.api.response.ro.FunctionOpenRo;
// import com.yueny.cms.api.response.ro.SubSystemRo;
// import com.yueny.cms.api.service.IOpenQueryService;
// import com.yueny.cms.api.service.ISystemQueryService;
// import com.yueny.rapid.data.resp.pojo.response.ListResponse;
//
/// **
// * 外部服务
// *
// * @author yueny09 <deep_blue_yang@163.com>
// *
// * @DATE 2017年1月24日 上午11:05:23
// * @since
// */
// @Service
// public class CmsConfigQueryIntegrationImpl implements
// ICmsConfigQueryIntegration {
// /**
// * Logger available to subclasses.
// */
// private final Logger logger = LoggerFactory.getLogger(getClass());
//
// @Autowired(required = false)
// private IOpenQueryService openQueryService;
// /** 系统编号 */
// @Value("${app.system.code}")
// private String systemCode;
// @Autowired(required = false)
// private ISystemQueryService systemQueryService;
//
// /*
// * (non-Javadoc)
// *
// * @see com.yueny.blog.service.integration.ICmsConfigQueryIntegration#
// * queryFunctionOpens()
// */
// @Override
// public List<FunctionOpenRo> queryFunctionOpens() {
// if (openQueryService == null) {
// logger.warn("服务未发现：{}！", openQueryService);
// return Collections.emptyList();
// }
//
// // 查询系统的功能开放配置
// final ListResponse<FunctionOpenRo> resp =
// openQueryService.queryAll(systemCode);
//
// logger.info("获取功能开放状态配置信息：{}！", resp);
// if (resp == null) {
// return Collections.emptyList();
// }
//
// return resp.getData();
// }
//
// @Override
// public List<SubSystemRo> querySubSystemList() {
// if (systemQueryService == null) {
// logger.warn("服务未发现：{}！", systemQueryService);
// return Collections.emptyList();
// }
//
// final ListResponse<SubSystemRo> resp = systemQueryService.queryAll();
//
// logger.info("获取所有子系统配置信息：{}！", resp);
// if (resp == null) {
// return Collections.emptyList();
// }
//
// return resp.getData();
// }
//
// }
