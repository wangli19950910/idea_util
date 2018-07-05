import com.util.http.HttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class OmcDemo {

    @Test
    public void testSelect(){
        String url = "http://localhost:8080/dep/studentSelect.do";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("Id","0001");
        String r = HttpUtil.doPost(url,map);
        System.out.println(r);
    }

    @Test
    public void testInsert(){
        String url = "http://localhost:8080/dep/studentInsert.do";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("Id","0002");
        map.put("Name","孙悟空");
        map.put("Nickname","齐天大圣");
        map.put("Course","西游记");

        String result = HttpUtil.doPost(url,map);
        System.out.println(result);

    }

    @Test
    public void testDelete(){
        String url = "http://localhost:8080/dep/studentDelete.do";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("Id","0001");
        String result = HttpUtil.doPost(url,map);
        System.out.println(result);
    }

    @Test
    public void testUpadte(){
        String url = "http://localhost:8080/dep/studentUpdate.do";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("Id","0001");
        map.put("Name","孙悟空");
        map.put("Nickname","齐天大圣");
        map.put("Course","西游记");
        String result = HttpUtil.doPost(url,map);
        System.out.println(result);
    }


    @Test
    public void testPageQuery(){
        String url = "http://localhost:8080/dep/studentPageQuery.do";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("pageNum","1");
        map.put("pageSize","3");
        String result = HttpUtil.doPost(url,map);
        System.out.println(result);
    }
}
