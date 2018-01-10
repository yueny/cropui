package com.yueny.blog.service.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.yueny.blog.service.disruptor.event.LogEvent;

/**
 * 事件发生处理<br>
 *
 * Disruptor 定义的事件处理接口，由用户实现，用于处理事件，是 Consumer 的真正实现。
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年12月20日 上午10:04:48
 *
 */
public class LogEventHandler implements EventHandler<LogEvent> {
	/*
	 * (non-Javadoc)
	 *
	 * @see com.lmax.disruptor.EventHandler#onEvent(java.lang.Object, long, boolean)
	 */
	@Override
	public void onEvent(final LogEvent event, final long sequence, final boolean endOfBatch) {
		final SyntonyExecute syntonyExecute = event.getSyntonyExecute();

		if (syntonyExecute != null) {
			syntonyExecute.execute();
		}
	}

}
