package lqw.test.socket;

import lqw.test.socket.util.HttpUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liqw on 2017/9/23.
 */
public class Server {
    private static final Exchanger<String> EXCHANGER = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
    static int portForHttp = 999;
    static int portForClient = 888;

    public static void main(String[] args) throws Exception {
        final ServerSocket serverForHttp = new ServerSocket(portForHttp);
        final ServerSocket serverForClient = new ServerSocket(portForClient);

        threadPool.execute(new Runnable() {
            public void run() {

                try {
                    listen:
                    while (true) {
                        System.out.println("listen http " + portForHttp);
                        Socket accept = serverForHttp.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                        String s = HttpUtil.readHttpString(in);
                        System.out.println("receive browser=>" + s);
                        if ("".equals(s)) {
                            System.out.println("空的请求");
                            continue;
                        }
                        EXCHANGER.exchange(s);
                        String exchange = EXCHANGER.exchange("");
                        System.out.println("receive from client:" + exchange);
                        //返回httpresponse
                        PrintWriter writer = new PrintWriter(accept.getOutputStream());
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

                    System.out.println("listen to client: " + portForClient);
                    Socket accept = serverForClient.accept();
                    System.out.println("client connected");
                    send2Client(accept);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private static void send2Client(Socket accept) throws IOException, InterruptedException {
        PrintWriter writer = new PrintWriter(accept.getOutputStream());
        InputStream inputStream = accept.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String exchange = EXCHANGER.exchange("");
            //发消息
            writer.println(exchange);
            writer.flush();
            //收消息
            String s = HttpUtil.readHttpString(in);
            while ("".equals(s.trim())) {
                System.out.println("回车");
                s = HttpUtil.readHttpString(in);
            }
            EXCHANGER.exchange(s);
        }
    }
    //threadPool.shutdown();
}
