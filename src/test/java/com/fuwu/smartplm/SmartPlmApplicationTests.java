package com.fuwu.smartplm;

import com.fuwu.smartplm.dto.UpdateDTO;
import com.fuwu.smartplm.entity.MetaNode;
import com.fuwu.smartplm.entity.mapper.MetaNodeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SmartPlmApplicationTests {

    @Autowired
    MetaNodeMapper mapper;

//    @Test
//    void contextLoads() {
//        MetaNode node = new MetaNode();
//        node.setLabel("系统设置2");
//        node.setApiCode("sys_settings2");
//        node.setSeq(1l);
//        node.setDescription("系统设置节点2");
//        mapper.insert(node);
//        System.out.println("====hahah======"+node.getId());
//    }
//    @Test
//    void delete() {
//        MetaNode node = new MetaNode();
//        node.setId(10l);
//        int count = mapper.delete(node);
//        System.out.println("=====ooooo====" + count);
//
//    }
    @Test
    void update() {

        Date yesterday = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 48);
        UpdateDTO dto = new UpdateDTO();
        dto.setId(1l);
        Map<String, Object> map = new HashMap<>();
        map.put("label", "Hello Label999");
        map.put("updated", yesterday);
        dto.setValuesMap(map);
        dto.setTableName("meta_node");
        int count = mapper.update(dto);
        System.out.println("=====ooooo====" + count);

    }

}
