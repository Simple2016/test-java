package lqw.test.proxy;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Server
 */
public class Client {
    static String urlString = "";
    static String methodType = "";
    static Map<String, String> propMap = new HashMap<String, String>();

    // 搭建客户端
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 999);
        System.out.println("客户端启动成功");
        final PrintWriter write = new PrintWriter(socket.getOutputStream());
        final BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        Thread read_thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    while (true) {
                        String inString = null;
                        try {
                            inString = in.readLine();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println("get:" + inString);
                        if ("--End--".equals(inString)) {

                            break;
                        } else if ("--Start--".equals(inString)) {
                            write.println(inString);
                            write.flush();
                            continue;
                        } else if ("".equals(inString)) {
                            continue;
                        } else if (inString.startsWith("POST")
                                || inString.startsWith("GET")) {
                            String[] split = inString.split(" ");
                            methodType = split[0].trim();
                            urlString = split[1].trim();
                        } else {
                            String[] split = inString.split(":");
                            String key = split[0].trim();
                            String value = split[1].trim();
                            propMap.put(key, value);
                        }
                    }
                    System.out.println("urlString:" + urlString);
                    System.out.println("methodType:" + methodType);
                    System.out.println("propMap:" + propMap);

                    URL url = null;
                    try {
                        url = new URL("http://127.0.0.1:8080" + urlString);
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    HttpURLConnection conn = null;
                    try {
                        conn = (HttpURLConnection) url.openConnection();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    for (Map.Entry<String, String> entry : propMap.entrySet()) {
                        conn.setRequestProperty(entry.getKey(),
                                entry.getValue());
                    }
                    if ("POST".equals(methodType)) {
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        conn.setUseCaches(false);
                    }

                    try {
                        conn.connect();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    InputStream instream = null;
                    try {
                        if (conn.getResponseCode()==400) {
                            write.println("HTTP/1.1 400 bad request");
                            write.println("--End--");
                            write.flush();
                            continue;
                        }
                        instream = conn.getInputStream();

                        System.err.println(conn.getHeaderFields());

//                        for (Map.Entry<String, List<String>> entry : conn
//                                .getHeaderFields().entrySet()) {
//                            if (entry.getKey() == null) {
//                                write.println(entry
//                                        .getValue()
//                                        .toString()
//                                        .substring(
//                                                1,
//                                                entry.getValue().toString()
//                                                        .length() - 1));
//                                continue;
//                            }
//                            write.println(entry.getKey()
//                                    + ":"
//                                    + entry.getValue()
//                                    .toString()
//                                    .substring(
//                                            1,
//                                            entry.getValue().toString()
//                                                    .length() - 1));
//                        }



                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        ByteArrayOutputStream b = new ByteArrayOutputStream();
                        int l;
                        final byte[] tmp = new byte[2048];
                        while ((l = instream.read(tmp)) != -1) {
                            b.write(tmp, 0, l);
                        }
                        String aaaa = new String(b.toByteArray());
                        System.err.println("发送数据："+aaaa);
                        String contentType = conn.getContentType();
                        write.println("HTTP/1.1 200 OK");
                        write.println("Date: " + conn.getHeaderField("Date"));
                        if (contentType == null || "".equals(contentType)) {
                            write.println("Content-Type: text/html;charset=utf-8");
                        } else {
                            write.println("Content-Type: " + contentType);
                        }
                        write.println("Content-Length: "
                                +(aaaa.length()));
                        write.println("\n" + aaaa);
                        write.println("--End--");
                        write.flush();
                        System.err.println("发送ok");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {
                        try {
                            instream.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        read_thread.run();
    }
}
