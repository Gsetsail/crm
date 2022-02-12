package com.hz.crm.web.listener;

import com.hz.crm.settings.dao.DicTypeDao;
import com.hz.crm.settings.dao.DicValueDao;
import com.hz.crm.settings.domain.DicValue;
import com.hz.crm.settings.service.DicService;
import com.hz.crm.settings.service.impl.DicServiceImpl;
import com.hz.crm.utils.ServiceFactory;
import com.hz.crm.utils.SqlSessionUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysInitListener implements ServletContextListener {

    /*
            event: 该参数可以取到监听的对象,监听的是什么对象,就可以通过该参数取得什么对象.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("上下文域对象创建了!");
        ServletContext application = event.getServletContext();

        DicService service = (DicService) ServiceFactory.getService(new DicServiceImpl());
        /*
            应该和业务层要7个List,每个list封装了一种类型分别有几条记录
            可以打包称为一个map
                业务层:
                    map.put("applicationList",dicList1)  key:种类,value:根据这个种类查到的数据条数
         */
        Map<String, List<DicValue>> map = service.selectDicValueByType();
        // 将map解析为上下文域对象中保存的键值对
        Set<String> set = map.keySet();
        for(String key: set){
            List<DicValue> dicValues = map.get(key);
            application.setAttribute(key,dicValues);
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}
