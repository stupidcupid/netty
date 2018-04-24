package com.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @author nanzhou
 * @datetime 2018/4/23 下午7:16
 * @description: 同步阻塞的I/O的TimeServerHandler
 * @since JDK 1.8
 */
public class TimeServerHandler implements Runnable {


    private Socket socket;

    public TimeServerHandler(Socket socket) {

        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {

            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            for (; ; ) {
                body = in.readLine();
                if (null == body) {
                    break;
                }
                System.out.println("The time server recieve order : " + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                out.println(currentTime);
            }

        } catch (Exception e) {

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (this.socket != null) {

                try {
                    this.socket.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                this.socket = null;
            }

        }
    }
}
