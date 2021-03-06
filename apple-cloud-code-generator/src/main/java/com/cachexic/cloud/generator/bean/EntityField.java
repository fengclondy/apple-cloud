package com.cachexic.cloud.generator.bean;

import org.apache.commons.lang3.StringUtils;

/**
 * 字段，数据库字段
 *
 * @author tangmin
 * @date 2016年2月26日
 */
public class EntityField {

  /**
   * entity字段
   */
  private String fieldName;

  /**
   * 数据库字段
   */
  private String columnName;

  /**
   * 首字母大写，用于模板的get  SET方法
   */
  private String supFiledName;

  /**
   * 用于判断class java.util.Date double
   */
  private String fieldTypeClassName;

  /**
   * 简单的类名 Date
   */
  private String simpleTypeName;

  /**
   * mysql 定义一个column的语句
   */
  private String mysqlFieldStr;

  /**
   * 字段描述
   */
  private String columnComment;

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
    this.supFiledName = StringUtils.capitalize(this.fieldName);
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getSupFiledName() {
    return supFiledName;//首字母大写
  }

  public void setSupFiledName(String supFiledName) {
    this.supFiledName = supFiledName;
  }

  public String getFieldTypeClassName() {
    return fieldTypeClassName;
  }

  public void setFieldTypeClassName(String fieldTypeClassName) {
    this.fieldTypeClassName = fieldTypeClassName;
  }

  public String getMysqlFieldStr() {
    return mysqlFieldStr;
  }

  public void setMysqlFieldStr(String mysqlFieldStr) {
    this.mysqlFieldStr = mysqlFieldStr;
  }

  public String getSimpleTypeName() {
    return simpleTypeName;
  }

  public void setSimpleTypeName(String simpleTypeName) {
    this.simpleTypeName = simpleTypeName;
  }

  public String getColumnComment() {
    return columnComment;
  }

  public void setColumnComment(String columnComment) {
    this.columnComment = columnComment;
  }

  @Override
  public String toString() {
    return "EntityField{" +
        "fieldName='" + fieldName + '\'' +
        ", columnName='" + columnName + '\'' +
        ", supFiledName='" + supFiledName + '\'' +
        ", fieldTypeClassName='" + fieldTypeClassName + '\'' +
        '}';
  }
}