package lqw.test.proxy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by liqw on 2017/6/13.
 */
public class HttpClient2 {
    public static void main(String args[]) throws IOException {


        Socket s = new Socket("127.0.0.1", 8080);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
        OutputStream out = s.getOutputStream();
        StringBuffer sb = new StringBuffer("POST /eips-wechat/test/?signature=3b8b6a68b9c73507d2dcffb0a4b1b2f7e5f26786&timestamp=1497424125&nonce=1894717024&openid=oypHlvt8BbE6PoPqt67g00seifak HTTP/1.1\r\n");
        sb.append("User-Agent: Mozilla/4.0\r\n");
        sb.append("Host: liqw.online\r\n");
        sb.append("Accept: */*\r\n");
        sb.append("Content-Type: text/xml\r\n");
        sb.append("\r\n");
        out.write(sb.toString().getBytes());
        String tmp = "";
        while ((tmp = br.readLine()) != null) {
            System.out.println(tmp);
        }
        out.close();
        br.close();


    }


}
