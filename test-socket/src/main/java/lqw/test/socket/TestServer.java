package lqw.test.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        dataServer.setSoTimeout(5000);
        localServer.setSoTimeout(61000);

        while (true) {
            print("等待客户端握手/wait client");
            Socket server = serviceServer.accept();
            print("hello,client" + server.getRemoteSocketAddress());
            OutputStream outputStream = server.getOutputStream();
            InputStream inputStream = server.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            try {
                String s = new BufferedReader(inputStreamReader).readLine();
                print(s);
            } catch (Exception e) {
                continue;
            }
            try {
                while (true) {
                    print("等待外部请求/wait request");
                    Socket localServerAccept = null;
                    localServerAccept = localServer.accept();
                    print("获得请求/get request from:" + localServerAccept.getRemoteSocketAddress());
                    print("请客户端接收数据/connect client data");
                    outputStream.write(1);
                    outputStream.flush();
                    final Socket dataServerAccept = dataServer.accept();
                    print("客户端已建立数据连接/client data connected");
                    serverTransfer(dataServerAccept, localServerAccept);
                }
            } catch (IOException e) {
                //e.printStackTrace();
                print("客户端已断开/client disconnected");
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
                        print(Thread.currentThread().getName() + "数据读取启动/data read start");
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
                    print(Thread.currentThread().getName() + "数据读取关闭/data read close");
                }
            });
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        print(Thread.currentThread().getName() + "本地返回启动/local response start");
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
                    print(Thread.currentThread().getName() + "本地返回关闭/local response close");
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

    private static void print(String s) {
        System.out.println(new SimpleDateFormat("[yyyyMMdd hh:mm:ss]").format(new Date()) + s);
    }


}
