package com.fuwu.smartplm.entity.mapper.base;

import com.fuwu.smartplm.dto.BulkDeleteDTO;
import com.fuwu.smartplm.dto.InsertDTO;
import com.fuwu.smartplm.dto.UpdateDTO;
import com.fuwu.smartplm.entity.BaseEntity;
import com.fuwu.smartplm.entity.mapper.base.sqlprovider.BulkDeleteSqlProvider;
import com.fuwu.smartplm.entity.mapper.base.sqlprovider.DeleteSqlProvider;
import com.fuwu.smartplm.entity.mapper.base.sqlprovider.InsertSqlProvider;
import com.fuwu.smartplm.entity.mapper.base.sqlprovider.UpdateSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.UpdateProvider;

public interface BaseCRUDMapper{
//    Object findById(Long id);

    @InsertProvider(type = InsertSqlProvider.class, method = "sql")
    @Options(keyProperty="id",keyColumn="id",useGeneratedKeys=true)
    void insert(InsertDTO dto);

    @UpdateProvider(type = UpdateSqlProvider.class, method = "sql")
    int update(UpdateDTO dto);

    @UpdateProvider(type = DeleteSqlProvider.class, method = "sql")
    int delete(BaseEntity entity);

    @UpdateProvider(type = BulkDeleteSqlProvider.class, method = "sql")
    int bulkDelete(BulkDeleteDTO dto);


}
