package com.roy.test;

import org.jboss.netty.handler.codec.http.HttpResponse;
import org.springframework.http.HttpEntity;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserControllerTest {

    public static void main(String[] args) {
        StringBuffer sbf = new StringBuffer();
        String result = null;
        String httpUrl = "http://192.168.1.118:8090";
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "gbk"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            System.out.println("result:"+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
