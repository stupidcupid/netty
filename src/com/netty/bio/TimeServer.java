package com.netty.bio;

import java.net.ServerSocket;

/**
 * @author nanzhou
 * @datetime 2018/4/23 下午7:13
 * @description: 同步阻塞的I/O的TimeServer
 * @since JDK 1.8
 */
public class TimeServer {


    public static void main(String[] args) {

        int port = 8080;
        if (args != null && args.length > 0) {

            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {

            }
        }

        ServerSocket server = null;


    }
}
