package com.fuwu.smartplm.entity.mapper.base.sqlprovider;

import com.fuwu.smartplm.entity.BaseEntity;

public class DeleteSqlProvider implements ISqlProvider{
    @Override
    public String sql(Object o) {
        if(!(o instanceof BaseEntity)){
            throw new NotSupportedException("DeleteSqlProvider 不支持该类型的参数");
        }
        StringBuffer bf = new StringBuffer();
        String tableName = SqlProviderUtil.getTableName((BaseEntity) o);
        bf.append("update ")
                .append(tableName)
                .append(" set deleted = 'Y'")
                .append(" where id = #{id}");
        return bf.toString();
    }
}
