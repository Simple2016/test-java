package lqw.test.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 */
public class Server {

    //搭建服务器端
    public static void main(String[] args) throws IOException {
        Server socketService = new Server();
        //1、a)创建一个服务器端Socket，即SocketService
        socketService.oneServer();
    }

    public void oneServer() throws IOException {

        ServerSocket server = new ServerSocket(999);
        System.out.println("内部服务器启动成功");

        final Socket socket = server.accept();
        System.out.println("客户端已连接");
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        final PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println("--Start--");
        writer.flush();
        in.readLine();
        final ServerSocket httpserver = new ServerSocket(888);
        System.out.println("外部服务器启动成功");
        System.out.println("开始监听888");
        Thread write_thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    PrintWriter printWriter = null;
                    try {
                        Socket httpsocket = httpserver.accept();
                        BufferedReader httpin = new BufferedReader(
                                new InputStreamReader(
                                        httpsocket.getInputStream()));
                        printWriter = new PrintWriter(
                                httpsocket.getOutputStream());
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = httpin.readLine()) != null
                                && !line.equals("")) {
                            System.out.println(line);
                            stringBuilder.append(line).append("\r\n");
                        }
                        writer.println(stringBuilder.toString());
                        writer.flush();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    BufferedReader in1 = null;
                    try {
                        in1 = new BufferedReader(new InputStreamReader(
                                socket.getInputStream()));
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        while (true) {
                            String inString = in1.readLine();
                            if ("--End--".equals(inString)) {
                                break;
                            }

                            stringBuilder.append(inString).append("\n");

                        }
                        System.err.println(stringBuilder.toString());
                        printWriter.println(stringBuilder.toString());
                        printWriter.flush();
                        printWriter.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        write_thread.run();

    }
}
