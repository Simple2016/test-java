package lqw.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liqw on 2017/9/23.
 */
public class TestServer {

    public static void main(String[] args) throws Exception {
        final ServerSocket serviceServer = new ServerSocket(777);//为客户端提供连接服务
        final ServerSocket dataServer = new ServerSocket(888);//为客户端提供数据传输服务
        final ServerSocket localServer = new ServerSocket(999);//本地服务器，接收外部请求
        while (true) {
            System.out.println("等待客户端握手");
            Socket server = serviceServer.accept();
            System.out.println("hello,client" + server.getRemoteSocketAddress());
            final OutputStream outputStream = server.getOutputStream();

            try {

                while (true) {
                    System.out.println("等待外部请求");
                    final Socket localServerAccept = localServer.accept();
                    System.out.println("请客户端接收数据");
                    outputStream.write(1);
                    final Socket dataServerAccept = dataServer.accept();
                    System.out.println("客户端已建立数据连接");
                    serverTransfer(dataServerAccept, localServerAccept);
                }
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("客户端已断开");
            }
        }

    }

    private static void serverTransfer(final Socket dataServerAccept, final Socket localServerAccept) {

        try {
            final InputStream data_in = dataServerAccept.getInputStream();
            final OutputStream data_out = dataServerAccept.getOutputStream();

            final InputStream local_in = localServerAccept.getInputStream();
            final OutputStream local_out = localServerAccept.getOutputStream();

            final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "数据读取启动");
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = local_in.read(buffer)) != -1) {
                            data_out.write(buffer, 0, len);
                        }
                    } catch (IOException e) {
//                        e.printStackTrace();
                        closeTransferStream(data_in, data_out, local_in, local_out);
                    }
                    closeTransferStream(data_in, data_out, local_in, local_out);
                    System.out.println(Thread.currentThread().getName() + "数据读取关闭");
                }
            });
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "本地返回启动");
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = data_in.read(buffer)) != -1) {
                            local_out.write(buffer, 0, len);
                        }

                    } catch (IOException e) {
//                        e.printStackTrace();
                        closeTransferStream(data_in, data_out, local_in, local_out);
                    }
                    closeTransferStream(data_in, data_out, local_in, local_out);
                    System.out.println(Thread.currentThread().getName() + "本地返回关闭");
                }
            });
            EXECUTOR_SERVICE.shutdown();
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
