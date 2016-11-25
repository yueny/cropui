/**
 *
 */
package com.yueny.blog.service.env;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.yueny.blog.service.CacheBaseBiz;

/**
 * 缓存服务
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月17日 下午2:14:14
 */
@Component
public class CacheService<T> extends CacheBaseBiz<T> {

	/**
	 * 缓存对象
	 */
	public final T cache(final ICacheExecutor<T> handler, final Long timeout, final Object... args) {
		return cache(handler, timeout, TimeUnit.SECONDS, args);
	}

	public final List<T> cacheList(final ICacheExecutor<List<T>> handler, final Long timeout, final Object... argses) {
		return cacheList(handler, timeout, TimeUnit.SECONDS, argses);
	}

}