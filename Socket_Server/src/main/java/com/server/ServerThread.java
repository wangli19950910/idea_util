package com.server;

import java.io.*;
import java.net.Socket;

/**
 * 服务器线程
 *
 * @auther wangli
 * @date 2018/7/5 14:36
 */
public class ServerThread extends Thread {

    Socket socket ;

    public ServerThread(Socket socket){
        this.socket = socket;
    }



    @Override
    public void run() {
        super.run();

        InputStream inputStream = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;

        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            while((info=bufferedReader.readLine())!=null){
                System.out.println("我是服务器:客户端说"+info);
            }
            socket.shutdownInput();

            outputStream  = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.print("欢迎访问：");
            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(printWriter!=null){
                printWriter.close();
            }
        }
    }
}
