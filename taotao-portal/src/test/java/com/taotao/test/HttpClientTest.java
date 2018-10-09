package com.taotao.test;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 黄运锐
 * @Date: 18-4-28 下午4:44
 * @Description:
 */
public class HttpClientTest {
    @Test
    public void doGet() throws Exception{
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个GET请求
        HttpGet httpGet = new HttpGet("http://www.sogou.com");
        //执行请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity,"utf-8");
        System.out.println(string);
        response.close();
        httpClient.close();
    }

    @Test
    public void doGetWithParm () throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URIBuilder builder = new URIBuilder("http://www.sogou.com/web");
        builder.addParameter("query","花千骨");
        HttpGet httpGet = new HttpGet(builder.build());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity,"utf-8");
        System.out.println(string);
    }

    @Test
    public void doPost () throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8084/httpclient/post.html");
        CloseableHttpResponse response =  httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity);
        System.out.println(string);
        httpClient.close();
    }


    @Test
    public void doPostWithParm () throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> kvList = new ArrayList<>();
        kvList.add( new BasicNameValuePair("username","zhagnsa"));
        kvList.add(new BasicNameValuePair("password","zhagnsa"));
        HttpPost httpPost = new HttpPost("http://localhost:8084/httpclient/post.action");
        StringEntity entity = new UrlEncodedFormEntity(kvList,"utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response =  httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        String string = EntityUtils.toString(responseEntity,"utf-8");
        System.out.println(string);
        httpClient.close();
    }
}
