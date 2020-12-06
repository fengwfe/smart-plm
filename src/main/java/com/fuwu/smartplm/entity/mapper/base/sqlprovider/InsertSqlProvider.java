package com.fuwu.smartplm.entity.mapper.base.sqlprovider;

import com.fuwu.smartplm.dto.InsertDTO;
import com.fuwu.smartplm.entity.BaseEntity;
import java.util.List;

public class InsertSqlProvider implements ISqlProvider{

    /**
     *
     * @param o
     *        实体类或者InsertDTO
     * @return
     *        mybatis insert sql 语句
     */
    @Override
    public String sql(Object o) {
        if(!((o instanceof BaseEntity)||(o instanceof InsertDTO))){
            throw new NotSupportedException("InsertSqlProvider 不支持该类型的参数");
        }
        try {
            StringBuffer sql = new StringBuffer();
            String tableName = null;
            List<String> fieldNames = null;
            String fieldPath = "";
            if(o instanceof BaseEntity){
                BaseEntity entity = (BaseEntity) o;
                tableName = SqlProviderUtil.getTableName(entity);
                fieldNames = getFieldNames4Insert(entity);
            }else if(o instanceof InsertDTO){
                fieldPath = SqlProviderUtil.DTO_FIELDS_PATH;
                InsertDTO dto = (InsertDTO) o;
                tableName = dto.getTableName();
                fieldNames = getFieldNames4Insert(dto);
            }
            INSERT_INTO(sql,tableName);
            VALUES(sql, fieldNames, fieldPath);
            return sql.toString();
        }catch (Exception e){
            throw new SqlGenerationException(e.getMessage());
        }
    }
    /**
     *
     * @param o
     *        实体类或者InsertDTO
     * @return List
     *        需要insert 到数据库中的字段名称
     * @throws Exception
     */
    private List<String> getFieldNames4Insert(Object o) throws Exception {
        List<String> result = null;
        if(o instanceof BaseEntity){
            result = SqlProviderUtil.getFieldNames((BaseEntity) o);
        }else if(o instanceof InsertDTO){
            result = SqlProviderUtil.getFieldNames(((InsertDTO) o).getValuesMap());
        }
        result.add(SqlProviderUtil.FIELD_CREATED_BY);
        result.add(SqlProviderUtil.FIELD_UPDATED_BY);
        return SqlProviderUtil.removeDuplicates(result);
    }
    private static void INSERT_INTO(StringBuffer sql, String tableName){
        sql.append("INSERT INTO ").append(tableName).append(" ");
    }
    private static void VALUES(StringBuffer sql, List<String> fieldNames, String fieldPath) throws Exception{
        StringBuffer columns = new StringBuffer();
        StringBuffer values = new StringBuffer();
        columns.append("(");
        values.append("values (");
        boolean isFirst = true;
        for(String fieldName: fieldNames){
            String colName = fieldName;
            String fullFieldName = fieldPath + fieldName;
            if(!isFirst){
                columns.append(", ");
                values.append(", ");
            }
            columns.append(colName);
            values.append("#{")
                    .append(fullFieldName)
                    .append("}");
            isFirst = false;
        }
        columns.append(")")
                .append(" ");
        values.append(")")
                .append(" ");
        sql.append(columns)
                .append(values);
    }
}
