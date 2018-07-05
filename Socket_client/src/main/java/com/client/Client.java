package com.client;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

/**
 * tcp客户端
 *
 * @auther wangli
 * @date 2018/7/5 14:47
 */
public class Client {

    public static void main(String[] args) {

        OutputStream outputStream= null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            Socket socket = new Socket("localhost",8888);


            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.print("用户名：wangli；密码：root");
            printWriter.flush();
            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String info = null;
            while((info = bufferedReader.readLine())!=null){
                System.out.println("我是客户端,服务器说: "+info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(printWriter!=null){
                printWriter.close();
            }
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
