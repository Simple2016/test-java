package lqw.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by liqw on 2017/9/23.
 */
public class TestClient {
    private static String serverHost = "127.0.0.1";//服务器地址
    private static int servicePort = 777;//建立连接端口（由服务器端代码设置）
    private static int serverPort = 888;//数据传输端口（由服务器端代码设置）
    private static String localHost = "127.0.0.1";//本地映射的地址
    private static int localPort = 8080;//本地映射的端口


    public static void main(String[] args) throws Exception {
        System.out.println("与服务器握手");
        final Socket service = new Socket(serverHost, servicePort);
        System.out.println("hello,server");
        InputStream inputStream = service.getInputStream();
        int i;
        while ((i = inputStream.read()) > -1) {
            System.out.println("服务器请我获取数据：" + i);
            final Socket data = new Socket(serverHost, serverPort);
            localTransfer(data);
            System.out.println("数据连接已建立");
        }
    }

    private static void localTransfer(final Socket data) {

        try {
            final InputStream data_in = data.getInputStream();
            final OutputStream data_out = data.getOutputStream();

            final Socket service = new Socket(localHost, localPort);
            final InputStream local_in = service.getInputStream();
            final OutputStream local_out = service.getOutputStream();

            final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "数据读取启动");
                    try {

                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = data_in.read(buffer)) != -1) {
                            local_out.write(buffer, 0, len);
                        }

                    } catch (IOException e) {
                        //e.printStackTrace();
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
                        while ((len = local_in.read(buffer)) != -1) {
                            data_out.write(buffer, 0, len);
                        }
                    } catch (IOException e) {
                        //e.printStackTrace();
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
