package com.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * tcp模拟服务器
 *
 * @auther wangli
 * @date 2018/7/5 14:30
 */
public class Server {

    private static ServerSocket serverSocket;
    private static Socket socket;

    public static void createServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            int count = 0;
            System.out.println("***服务器启动中，等待客户机链接*******");
            while(true){
                socket = serverSocket.accept();

                ServerThread serverThread = new ServerThread(socket);

                serverThread.start();

                count++;
                System.out.println("当前客户端的数量："+count);
                InetAddress address = socket.getInetAddress();

                System.out.println("当前客户端的IP："+address.getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createServer();
    }
}
