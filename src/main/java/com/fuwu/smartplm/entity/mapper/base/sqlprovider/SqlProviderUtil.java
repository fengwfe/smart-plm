package com.fuwu.smartplm.entity.mapper.base.sqlprovider;

import com.fuwu.smartplm.entity.BaseEntity;
import java.lang.reflect.Field;
import java.util.*;

public class SqlProviderUtil {
    public static final String DTO_FIELDS_PATH = "valuesMap.";
    public static final String FIELD_ID = "id";
    public static final String FIELD_VERSION = "version";
    public static final String FIELD_CREATED_BY = "createdBy";
    public static final String FIELD_UPDATED_BY = "updatedBy";

    /**
     * @param entity
     *        实体类
     * @return List
     *         该实体所包含的字段名称,不包含父类中定义的字段名称
     * @throws Exception
     */
    public static List<String> getFieldNames(BaseEntity entity) throws Exception {
        List<String> result = new ArrayList<>();
        Class<?> cls = entity.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            result.add(fieldName);
        }
        return result;
    }
    /**
     * @param map
     *        a HashMap
     * @return List
     *         map中所有的key
     * @throws Exception
     */
    public static List<String> getFieldNames(Map<String, Object> map) throws Exception {
        List<String> result = new ArrayList<>();
        Set<String> keys = map.keySet();
        for(String key : keys){
            result.add(key);
        }
        return result;
    }

    /**
     *
     * @param entity
     *        实体类
     * @return
     *        该实体类对应的数据库表名
     */
    public static String getTableName(BaseEntity entity){
        String simpleName = entity.getClass().getSimpleName();
        return camelCase2Underscore(simpleName);
    }
    private static String camelCase2Underscore(String s) {
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c >= 'A' && c <= 'Z'){
                char lowChar = (char)(c + 32);
                bf.append("_").append(lowChar);
            }else{
                bf.append(c);
            }
        }
        return  bf.substring(1);
    }

    /**
     *
     * @param sql
     *        要构建的sql 语句
     * @param colName
     *        数据库column名称
     * @param fieldPath
     *        java类中字段的来源,
     *        1. BaseEntity时 filePat为""
     *        2. DTO时 filePath为 SqlProviderUtil.DTO_FIELDS_PATH
     */
    public static void WHERE(StringBuffer sql, String colName, String fieldPath){
        String fieldName = fieldPath + colName;
        sql.append(" WHERE ")
                .append(colName)
                .append(" = ")
                .append("#{")
                .append(fieldName)
                .append("}")
                .append(" ");
    }

    /**
     *
     * @param sql
     *        要构建的sql 语句
     * @param colName
     *        数据库column名称
     * @param fieldPath
     *        java类中字段的来源,
     *        1. BaseEntity时 filePat为""
     *        2. DTO时 filePath为 SqlProviderUtil.DTO_FIELDS_PATH
     */
    public static void AND(StringBuffer sql, String colName, String fieldPath){
        String fieldName = fieldPath + colName;
        sql.append("AND ")
                .append(colName)
                .append(" = ")
                .append("#{")
                .append(fieldName)
                .append("}")
                .append(" ");
    }

    public static List<String> removeDuplicates(List<String> list){
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(String s : list){
            set.add(s);
        }
        for(String s : set){
            result.add(s);
        }
        return result;
    }

}
