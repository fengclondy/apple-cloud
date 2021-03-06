package com.cachexic.cloud.provider.order.dao;

import com.cachexic.cloud.common.base.dao.BaseDao;
import com.cachexic.cloud.config.mybatis.annotation.MybatisDao;
import com.cachexic.cloud.feign.order.entity.Teacher;
import com.cachexic.cloud.feign.order.entity.query.TeacherQuery;

/**
 * 教师测试实体管理
 *
 * @author tangmin
 * @date 2017-09-27 14:42:38
 */
@MybatisDao
public interface TeacherDao extends BaseDao<Teacher, TeacherQuery> {

}
