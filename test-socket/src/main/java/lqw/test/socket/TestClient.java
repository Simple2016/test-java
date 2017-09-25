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
    final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(1);
    final static Exchanger<String> EXCHANGER = new Exchanger<>();
    final static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws Exception {
//        ServerSocket serverSocket = new ServerSocket(999);
//        while (true) {
//            final Socket accept = serverSocket.accept();
//            localTransfer(accept);
//        }
        //final Socket server = new Socket("104.194.79.49", 888);
//        for (; ; ) {


        EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                try {


//                    final Socket server = new Socket("127.0.0.1", 888);
                    final Socket server = new Socket("104.194.79.49", 888);
                    localTransfer(server);


                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

//        }
    }

    private static void localTransfer(final Socket accept) {

        try {


            final InputStream in = accept.getInputStream();
            final OutputStream out = accept.getOutputStream();

            final Socket service = new Socket("127.0.0.1", 8080);
            final InputStream service_in = service.getInputStream();
            final OutputStream service_out = service.getOutputStream();

            final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    try {

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
                    System.out.println("999连接已断开");
                }
            });
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    try {

                        System.out.println("..");
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
                    System.out.println("8080连接已断开");
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
