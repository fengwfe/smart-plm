package com.fuwu.smartplm.entity.mapper.base.sqlprovider;

import com.fuwu.smartplm.dto.UpdateDTO;
import com.fuwu.smartplm.entity.BaseEntity;

import java.util.List;

public class UpdateSqlProvider implements ISqlProvider{
    @Override
    public String sql(Object o) {
        if(!(o instanceof UpdateDTO)){
            throw new NotSupportedException("UpdateSqlProvider 不支持该类型的参数");
        }
        try {
            StringBuffer sql = new StringBuffer();
            String tableName = null;
            List<String> fieldNames = null;
            tableName = ((UpdateDTO)o).getTableName();
            fieldNames = getFieldNames4Update(o);
            UPDATE(sql,tableName);
            SET_VALUES(sql, fieldNames, SqlProviderUtil.DTO_FIELDS_PATH);
            SET_VERSION(sql);
            SqlProviderUtil.WHERE(sql, SqlProviderUtil.FIELD_ID, "");
            SqlProviderUtil.AND(sql, SqlProviderUtil.FIELD_VERSION, "");
            return sql.toString();
        }catch (Exception e){
            throw new SqlGenerationException(e.getMessage());
        }
    }
    /**
     *
     * @param o
     *        实体类或者UpdateDTO
     * @return List
     *        需要update 到数据库中的字段名称
     * @throws Exception
     */
    public static List<String> getFieldNames4Update(Object o) throws Exception {
        List<String> result = null;
        if(o instanceof BaseEntity){
            result = SqlProviderUtil.getFieldNames((BaseEntity) o);
        }else if(o instanceof UpdateDTO){
            result = SqlProviderUtil.getFieldNames(((UpdateDTO) o).getValuesMap());
        }
        result.add(SqlProviderUtil.FIELD_UPDATED_BY);
        return SqlProviderUtil.removeDuplicates(result);
    }
    public static StringBuffer UPDATE(StringBuffer sql, String tableName){
        sql.append("update ")
                .append(tableName)
                .append(" ")
                .append("set ");
        return sql;
    }
    public static void SET_VALUES(StringBuffer sql, List<String> fieldNames, String fieldPath) throws Exception{
        boolean isFirst = true;
        for(String fieldName: fieldNames){
            String colName = fieldName;
            String fullFieldName = fieldPath + fieldName;
            if(!isFirst){
                sql.append(", ");
            }
            sql.append(colName);
            sql.append(" = ");
            sql.append("#{");
            sql.append(fullFieldName);
            sql.append("}");
            isFirst = false;
        }
    }
    public static void SET_VERSION(StringBuffer sql) throws Exception{
        sql.append(", ")
                .append("version = version + 1");
    }
}
