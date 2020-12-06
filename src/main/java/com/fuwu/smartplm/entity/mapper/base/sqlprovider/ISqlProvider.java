package com.fuwu.smartplm.entity.mapper.base.sqlprovider;

/**
 * 约定:
 * 1. Java Entity的类名采用驼峰格式, 数据库Table name以下划线连接, Example: MetaNode.java -- > meta_node
 * 2. Java Entity类的字段名称和数据库表的column名称保持一致,
 *    这样的好处:不需要提供result map 的情况下mybatis就能自动把数据库结果集映射到Entity类上面
 */
public interface ISqlProvider {
    String sql(Object o);
}
