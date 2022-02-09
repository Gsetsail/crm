package com.hz.crm.tests;

import com.hz.crm.utils.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMain {

    public static void main(String[] args) {

            // 验证失效时间
        String str = "2019-10-10 10:10:10";

        String currentTime = DateTimeUtil.getSysTime();

        System.out.println(str.compareTo(currentTime));




    }
}
