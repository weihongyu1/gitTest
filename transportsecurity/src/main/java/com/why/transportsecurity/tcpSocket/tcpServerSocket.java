package com.why.transportsecurity.tcpSocket;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
/**
 * @Description TODO socket服务端
 * @Author why
 * @Date 2021/8/12 13:50
 * Version 1.0
 **/
@Slf4j
public class tcpServerSocket {
    public static void server(Integer port){
        log.info("服务端启动. . .");
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            while (true){
                //获取一个客户端连接
                Socket accept = server.accept();
                //创建信息线程处理客户端信息
                new Thread(() -> {
                    InputStream is = null;
                    ByteArrayOutputStream baos = null;
                    try {
                        //获取字节输入流
                        is = accept.getInputStream();
                        byte[] buffer = new byte[1024];
                        int len;
                        baos = new ByteArrayOutputStream();
                        while ((len = is.read(buffer)) != -1){
                            //写到baos中，中有个数组，会自动扩容
                            baos.write(buffer,0,len);
                        }
                        log.info(accept.getRemoteSocketAddress()+" - "+baos.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            if (is != null){
                                is.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },"server").start();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
