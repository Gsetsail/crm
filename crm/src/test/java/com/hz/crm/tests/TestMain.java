package com.hz.crm.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hz.crm.utils.DateTimeUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TestMain {

     @Test
    public void TestInsertCustomerAndContactsByClueId(){
         HashMap<String,String> pMap = new HashMap<>();
         ResourceBundle bundle = ResourceBundle.getBundle("Stage2Possibility");
         Enumeration<String> keys = bundle.getKeys();
         while(keys.hasMoreElements()){
             // 阶段
             String key = keys.nextElement();
             // 可能性
             String value = bundle.getString(key);

             pMap.put(key,value);
         }
         ObjectMapper om = new ObjectMapper();
         try {
             System.out.println(om.writeValueAsString(pMap));
         } catch (JsonProcessingException e) {
             e.printStackTrace();
         }
     }
     }

