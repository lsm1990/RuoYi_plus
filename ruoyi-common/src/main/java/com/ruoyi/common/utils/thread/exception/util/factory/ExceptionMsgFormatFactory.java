package com.ruoyi.common.utils.thread.exception.util.factory;


import com.ruoyi.common.utils.thread.exception.util.DefaultExceptionMsgHandler;
import com.ruoyi.common.utils.thread.exception.util.ExceptionMessageFormat;
import com.ruoyi.common.utils.thread.exception.util.StackTraceMsgHandler;

/**
 * 异常信息格式化工厂
 * 
 * @author zengyuanjun
 *
 */
public class ExceptionMsgFormatFactory {

	public static final String STACK_TRACE = "StackTraceHandler";

	private ExceptionMsgFormatFactory() {
	}

	private static class SingletonHolder {
		private static final ExceptionMsgFormatFactory instance = new ExceptionMsgFormatFactory();
	}

	public static ExceptionMsgFormatFactory getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * 获取格式化工具
	 * 
	 * @param formatterName
	 * @return
	 */
	public ExceptionMessageFormat getFormatter(String formatterName) {
		switch (formatterName) {
		case STACK_TRACE:
			return StackTraceMsgHandler.getInstance();

		default:
			break;
		}
		return DefaultExceptionMsgHandler.getInstance();
	}
}
