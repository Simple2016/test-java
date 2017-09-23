package lqw.test.socket.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by liqw on 2017/9/23.
 */
public class HttpUtil {
    public static String send(String ip, int port, String data) {
        try {
            Socket http = new Socket(ip, port);
            PrintWriter writer = new PrintWriter(http.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));

            writer.println(data);
            writer.flush();
            return readHttpString(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readHttpString(BufferedReader in) throws IOException {
        StringBuilder headStringBuilder = new StringBuilder();
        StringBuilder contentStringBuilder = new StringBuilder();
        int content_Length = 0;
        for (; ; ) {
            String s = in.readLine();
            headStringBuilder.append(s + "\r\n");
            if (s == null || "".equals(s.trim())) {
                break;
            }
            if (s.contains("Content-Length")) {
                content_Length = Integer.parseInt(s.split(":")[1].trim());
            }
        }
        String head = headStringBuilder.toString();
        if (content_Length > 0) {
            byte[] bytes = new byte[content_Length];
            for (int i = 0; i < content_Length; i++) {
                bytes[i] = (byte) in.read();
            }
            contentStringBuilder.append(new String(bytes));

        }
        String content = contentStringBuilder.toString();
        return head + content;
    }

    public static void main(String[] args) {
        String data = "GET /webtest-react/ HTTP/1.1\n" +
                "Host: localhost:999\n" +
                "Connection: keep-alive\n" +
                "Cache-Control: no-cache\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36\n" +
                "Postman-Token: 14ed9f71-057b-c7f2-3ff7-697c2b8b8570\n" +
                "Accept: */*\n" +
                "Accept-Encoding: gzip, deflate, sdch, br\n" +
                "Accept-Language: zh-CN,zh;q=0.8,zh-HK;q=0.6\n" +
                "Cookie: JSESSIONID=C5AA41E7C64F665433AC1719910FF4A4\n";
        String send = send("127.0.0.1", 8080, data);
        System.out.println(send);
    }
}
