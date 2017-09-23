package lqw.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liqw on 2017/9/23.
 */
public class Test {
    final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);
    final static Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) throws Exception {
        EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);
                final Exchanger<String> EXCHANGER = new Exchanger<>();
                EXECUTOR_SERVICE.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(EXCHANGER.exchange("inner1"));

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
                EXECUTOR_SERVICE.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(EXCHANGER.exchange("inner2"));

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
        EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(EXCHANGER.exchange("3"));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
