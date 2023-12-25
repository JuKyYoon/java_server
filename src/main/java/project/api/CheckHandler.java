package project.api;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;

public class CheckHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String res = """
                {
                    "message" : "success"
                }
                """;

        // 응답 헤더 설정
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, res.getBytes().length);

        // 응답
        OutputStream os = exchange.getResponseBody();
        os.write(res.getBytes());
        os.close();
    }
}
