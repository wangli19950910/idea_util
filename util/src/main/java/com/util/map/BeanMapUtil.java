package com.util.map;

import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Description:    Map-->Bean工具类的使用
 * @Package:        com.util.map
 * @ClassName:      BeanMapUtil
 * @Author:         wangli
 * @CreateDate:     2018/7/3 20:35
 */
public class BeanMapUtil {

    /**
     * Map--bean 使用org.apache.commons.beanutils 工具类实现 Map-bean
     * @param map
     * @param object
     */
    public static void transMapToBean(Map map,Object object){
        if(map==null||object==null){
            return;
        }
        try {
            BeanUtils.populate(object,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static void transMaptoBean2(Map map,Object object){
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor property:propertyDescriptors){
                String key = property.getName();
                if(map.containsKey(key)){
                    Object value =map.get(key);
                    Method setter = property.getWriteMethod();
                    setter.invoke(object,value);
                }
            }

        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return;
    }


    public static void main(String[] args) {
        User user = new User();
        Map map = new HashMap();
        map.put("userId","1");
        map.put("userName","wl");
        map.put("password","123456");

//        transMapToBean(map,user);
//        System.out.println(user.toString());
        transMaptoBean2(map,user);
        System.out.println(user.toString());
    }
}
