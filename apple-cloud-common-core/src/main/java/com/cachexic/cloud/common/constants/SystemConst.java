package com.cachexic.cloud.common.constants;

/**
 * @author tangmin
 * @Description: 系统常量
 * @date 2017-08-26 12:19:36
 */
public class SystemConst {

  public static final long DEFAULT_PAGE_SIZE = 10;
  public static final long MAX_PAGE_SIZE = 100;

  /**
   * 切面设置requestId，异常拦截的时候，获取requestId，请求参数
   */
  public final static String REQUEST_ID = "requestId";
  public final static String REQUEST_ARGS = "requestArgs";

  private SystemConst() {
    throw new IllegalAccessError("Utility class");
  }
}
