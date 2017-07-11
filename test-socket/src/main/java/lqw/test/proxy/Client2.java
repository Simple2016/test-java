package lqw.test.proxy;

import java.io.*;
import java.net.Socket;

/**
 * Server
 */
public class Client2 {

    // 搭建客户端
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("104.194.79.49", 999);
        System.out.println("客户端启动成功");
        final PrintWriter write = new PrintWriter(socket.getOutputStream());
        final BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));

        while (true) {
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                String inString = null;
                try {
                    System.out.print("read:");
                    inString = in.readLine();
                    System.out.println(">"+inString);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if ("--End--".equals(inString)) {
                    stringBuilder.append("\r\n");
                    break;
                } else if ("--Start--".equals(inString)) {
                    write.println(inString);
                    write.flush();
                    continue;
                } else if ("".equals(inString)) {
                    continue;
                }
                stringBuilder.append(inString).append("\r\n");

            }
            String data = stringBuilder.toString();
            System.err.println("获得请求数据：" + data);

            System.err.println("调用本地web服务");
            Socket s = new Socket("127.0.0.1", 8080);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
            OutputStream out = s.getOutputStream();
            out.write(data.toString().getBytes());
            out.write("\r\n".getBytes());
            out.flush();

            String tmp = "";
            StringBuilder stringBuilder1 = new StringBuilder();
            while ((tmp = br.readLine()) != null) {
                stringBuilder1.append(tmp).append("\r\n");
            }
            out.close();
            br.close();


            System.err.println("发送数据：" + stringBuilder1.toString());
            write.println(stringBuilder1.toString());
            write.println("--End--");
            write.flush();
            System.err.println("发送ok");

        }

    }

}

