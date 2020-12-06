package com.fuwu.smartplm;

import com.fuwu.smartplm.dto.BulkDeleteDTO;
import com.fuwu.smartplm.dto.InsertDTO;
import com.fuwu.smartplm.dto.UpdateDTO;
import com.fuwu.smartplm.entity.MetaNode;
import com.fuwu.smartplm.entity.mapper.MetaNodeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class MapperTests {


    @Autowired
    MetaNodeMapper metaNodeMapper;

//    @Test
    void insert(){
        InsertDTO dto = new InsertDTO();
        dto.setTableName("meta_node");
        Map<String, Object> map = new HashMap<>();
        map.put("label", "AAA");
        map.put("apiCode", "C_01");
        map.put("seq", 99l);
        map.put("description", "测试一下");
        map.put("createdBy", 666l);
        map.put("updatedBy", 666l);
        dto.setValuesMap(map);
        metaNodeMapper.insert(dto);
        System.out.println(dto.getId());
    }

//    @Test
    void delete(){
        MetaNode node = new MetaNode();
        node.setId(3l);
        System.out.println(metaNodeMapper.delete(node));
    }

//    @Test
    void update(){
        UpdateDTO dto = new UpdateDTO();
        dto.setVersion(1l);
        dto.setId(3l);
        dto.setTableName("meta_node");
        Map<String, Object> map = new HashMap<>();
        map.put("deleted", "N");
        map.put("updatedBy", 9l);
        dto.setValuesMap(map);
        System.out.println(metaNodeMapper.update(dto));
    }

    @Test
    void bulkDelete(){
        BulkDeleteDTO dto = new BulkDeleteDTO();
        dto.setTableName("meta_node");
        dto.setIds(Arrays.asList(new Long[]{1l,3l,4l}));
        System.out.println(metaNodeMapper.bulkDelete(dto));

    }


}
