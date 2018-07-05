package com.util.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Wang Li
 * @Date: 2018/5/28 09:26
 * @Description: java模拟http发送请求，支持get和post两种方式
 * 功能描述:
 *      1. 支持http协议的两种请求方式;支持map和json两种格式数据调用
 * 扩展：
 *      2.支持Https协议支持，射击SSL认证体系
 *
 */
@SuppressWarnings("AlibabaClassMustHaveAuthor")
public class HttpUtil {

    private static final String  CHARSET = "UTF-8";

    /**
     * Http--Get    有参Map
     * @param url
     * @param params  map格式的参数数据
     * @return
     */
    public static String doGet(String url, Map<String,Object> params){

        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for(String key:params.keySet()){
            if(i==0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl+=param;
        String result = null;
        HttpClient httpClient = new HttpClient();
        try{
            GetMethod getMethod = new GetMethod(apiUrl);
            getMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
            getMethod.getParams().setContentCharset("UTF-8");
            int statusCode = httpClient.executeMethod(getMethod);
            if(statusCode!=HttpStatus.SC_OK){
                System.out.println("请求出错。错误的返回码是"+getMethod.getStatusCode());
                return null;
            }
            byte[] responseBody = getMethod.getResponseBody();

            result = new String(responseBody,CHARSET);

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Http--Get    无参
     * @param url
     * @return
     */
    public static String doGet(String url){
        return doGet(url,new HashMap<String, Object>());
    }

    /**
     * Http--Post   有参Map
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String,Object> params){
        //DefaultHttpClient client = new DefaultHttpClient();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String result = null;

        try{
            List<NameValuePair> pairList = new ArrayList<NameValuePair>();
            for(Map.Entry<String,Object> entry:params.entrySet()){
                NameValuePair pair = new BasicNameValuePair(entry.getKey(),entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(CHARSET)));
            response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()== org.apache.http.HttpStatus.SC_OK){
                result = EntityUtils.toString(response.getEntity());
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //资源关闭
            if(response!=null){
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * Http--Post   无参
     * @param url
     * @return
     */
    public static String doPost(String url){
        return doPost(url,new HashMap<String, Object>());
    }

    /**
     * Http--Post   有参Json
     * @param url
     * @param params
     * @return
     */
    public static JSONObject doPost(String url, JSONObject params){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String result = null;
        JSONObject jsonObject = null;

        try{

            StringEntity stringEntity = new StringEntity(params.toString(),CHARSET);

            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json;charset=UTF-8");
            httpPost.setEntity(stringEntity);
            response  = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()== org.apache.http.HttpStatus.SC_OK){
                result = EntityUtils.toString(response.getEntity());
                jsonObject = JSONObject.parseObject(result);
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //资源关闭
            if(response!=null){
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }


    public static void main(String[] args) {
        /**
         * 测试调用百度地图位置，调用百度的api测试使用
         */
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("ak","STai60SY2FKuIWcK7o4TgtsgGtBFcBVP");
        map.put("address", "无为");
        String result = doPost("http://api.map.baidu.com/geocoder/v2/",map);
        System.out.println(result);

    }
}
