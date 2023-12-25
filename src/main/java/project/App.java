package project;
import com.sun.net.httpserver.*;

import project.api.ApiHandler;
import project.api.CheckHandler;
import project.api.LoginHandler;
import project.api.SumHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    private static final int PORT = 8888;
    public static void main(String args[]) throws IOException {

        // 서버 생성
        HttpServer app = HttpServer.create(new InetSocketAddress("0.0.0.0", PORT), 0);


        // 라우팅
        app.createContext("/", new CheckHandler());
        app.createContext("/sum", new SumHandler());
        app.createContext("/auth", new LoginHandler());
        app.createContext("/api", new ApiHandler());

        app.setExecutor(null);
        // 시작
        app.start();

        System.out.println("----------server start---------");

    }
}
