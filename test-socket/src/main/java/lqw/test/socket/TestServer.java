package lqw.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by liqw on 2017/9/23.
 */
public class TestServer {
    final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(1);
    final static Exchanger<String> EXCHANGER = new Exchanger<>();
    final static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws Exception {


//        for (; ; ) {

        EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(888);
                    ServerSocket serverSocket2 = new ServerSocket(999);
                    while (true) {
                        System.out.println("等待客户端");
                        final Socket accept = serverSocket.accept();
                        System.out.println("客户端已连接");
                        System.out.println("等待外部请求");
                        final Socket server = serverSocket2.accept();
                        serverTransfer(accept, server);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//        }

    }

    private static void serverTransfer(final Socket accept, final Socket server) {

        try {


            final InputStream in = accept.getInputStream();
            final OutputStream out = accept.getOutputStream();

            final InputStream service_in = server.getInputStream();
            final OutputStream service_out = server.getOutputStream();

            final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    try {

                        System.out.println("读取外部请求数据");
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = service_in.read(buffer)) != -1) {
                            out.write(buffer, 0, len);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                        closeTransferStream(in, out, service_in, service_out);
                    }
                    closeTransferStream(in, out, service_in, service_out);
                    System.out.println("外部请求连接已断开");
                }
            });
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("读取客户端数据");
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = in.read(buffer)) != -1) {
                            service_out.write(buffer, 0, len);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        closeTransferStream(in, out, service_in, service_out);
                    }
                    closeTransferStream(in, out, service_in, service_out);
                    System.out.println("客户端连接已断开");
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeTransferStream(InputStream in, OutputStream out, InputStream service_in, OutputStream service_out) {
        try {
            in.close();
            out.close();
            service_in.close();
            service_out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


}
