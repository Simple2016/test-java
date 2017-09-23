package lqw.test.socket;

import lqw.test.socket.util.HttpUtil;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liqw on 2017/9/23.
 */
public class Client {
    private static final Exchanger<String> EXCHANGER = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
    static int portForHttp = 8080;
    static int portForServer = 888;

    public static void main(String[] args) throws Exception {
        final Socket server = new Socket("104.194.79.49", portForServer);

        threadPool.execute(new Runnable() {
            public void run() {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
                    PrintWriter writer = new PrintWriter(server.getOutputStream());
                    while (true) {
                        System.out.println("listen to server");

                        String s = HttpUtil.readHttpString(in);
                        if ("null\r\n".equals(s)) {
                            System.out.println("服务器已断开");
                            threadPool.shutdownNow();
                            break;
                        }
                        if ("".equals(s.trim())) {
                            System.out.println("回车");
                            continue;
                        }
                        System.out.println("request from server=>" + s);
                        EXCHANGER.exchange(s);
                        String exchange = EXCHANGER.exchange("");
                        System.out.println("response to server=>" + exchange);

                        //返回httpresponse

                        writer.println(exchange);
                        writer.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        String exchange = EXCHANGER.exchange("");
                        //发消息
                        String send = HttpUtil.send("127.0.0.1", 8080, exchange);
                        EXCHANGER.exchange(send);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    //threadPool.shutdown();
}
