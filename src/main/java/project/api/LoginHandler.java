package project.api;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.*;
import project.dto.AccountDto;
import project.dto.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.StringTokenizer;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        System.out.println(String.format("method : %s",method));

        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);

        // just read
//        String line;
//        while((line = br.readLine()) != null) {
//            System.out.println(line);
//        }

        Gson gson = new Gson();
        AccountDto ac = gson.fromJson(br, AccountDto.class);

        ac.setId(ac.getId() + " server");
        ac.setPassword(ac.getPassword() + " server");

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        ac.setLastLoginDate(ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd @ HH:mm:ss")));

        JsonObject jo = new JsonObject();
        jo.addProperty("message", "post success");
        jo.addProperty("id", ac.getId());
        jo.addProperty("pw", ac.getPassword());
        jo.addProperty("date",ac.getLastLoginDate());

        String res = jo.toString();

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, res.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(res.getBytes());
        br.close();
        os.close();
    }
}
