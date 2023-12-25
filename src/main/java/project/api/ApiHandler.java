package project.api;
import com.sun.net.httpserver.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.*;
public class ApiHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange ex) throws IOException {

        StringBuilder sb = new StringBuilder();
        OutputStream os = ex.getResponseBody();
        ex.getResponseHeaders().set("Content-Type", "application/json");
        String res = "";

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            
            JsonObject obj = new Gson().fromJson(sb.toString(), JsonObject.class);
            obj.addProperty("my status", "test");


            res = obj.toString();
            ex.sendResponseHeaders(200, res.getBytes().length);

        } catch(Exception e) {
            JsonObject obj = new JsonObject();
            obj.addProperty("status", "fail");
            res = obj.toString();
            ex.sendResponseHeaders(400, res.getBytes().length);
        }

        os.write(res.getBytes());
        os.close();
    }
}