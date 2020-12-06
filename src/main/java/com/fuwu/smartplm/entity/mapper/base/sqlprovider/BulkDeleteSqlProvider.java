package com.fuwu.smartplm.entity.mapper.base.sqlprovider;

import com.fuwu.smartplm.dto.BulkDeleteDTO;

public class BulkDeleteSqlProvider implements ISqlProvider{
    @Override
    public String sql(Object o) {
        if(!(o instanceof BulkDeleteDTO)){
            throw new NotSupportedException("BulkDeleteSqlProvider 不支持该类型的参数");
        }
        StringBuffer sql = new StringBuffer();
        sql.append("<script>");
        String tableName = ((BulkDeleteDTO)o).getTableName();
        sql.append("update ")
                .append(tableName)
                .append(" set deleted = 'Y'")
                .append("where ")
                .append(ID_IN_IDS());
        sql.append("</script>");
        return sql.toString();
    }

    public String ID_IN_IDS(){
        String colName = "id";
        String fullFieldName = "ids";
        StringBuffer bf = new StringBuffer();
        bf.append(colName)
                .append(" in ")
                .append("<foreach item='item' collection='" + fullFieldName + "' open='(' separator=',' close=')'>")
                .append("#{item}")
                .append("</foreach> ");
        return bf.toString();
    }
}
