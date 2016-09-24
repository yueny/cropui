package com.yueny.cropui.bo.enums;

import com.yueny.superclub.api.annnotation.EnumValue;

/**
 * @category 不可预知异常信息错误码,以 300000X 开头的7位长度
 *
 * @author 袁洋 2014年8月2日
 */
public enum CropuiToolkitsErrorType {
	/** 系统异常 */
	CLASS_NOT_FOUND("3000001", "%s对应实现类不存在"),
	/** 非法的请求地址 */
	INVALID_REQUEST_URI("3000002", "非法的请求地址"),
	/** 未实现的操作 */
	NOT_ALLOW_OPERATION("3000003", "未实现的操作"),
	/** 没有对缓存键值的操作权限 */
	NOT_AUTHORITY_REDIS_CACHE_KEY("3000004", "没有对缓存键值%s的操作权限"),
	/** 数据不存在 */
	NOT_FOUND_DATA("3000005", "数据不存在"),
	/** 空指针异常 */
	NULL_POINTER_EXCEPTION("3000006", "空指针异常"),
	/** 正则表达式异常 */
	PATTERN_SYNTAX_EXCEPTION("3000007", "正则表达式异常");

	/** 错误码 */
	private final String code;
	/** 错误码描述 */
	private final String message;

	/**
	 * @param errorCode
	 *            错误码
	 * @param describe
	 *            错误码描述
	 */
	private CropuiToolkitsErrorType(final String errorCode,
			final String describe) {
		this.code = errorCode;
		this.message = describe;
	}

	@EnumValue
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
