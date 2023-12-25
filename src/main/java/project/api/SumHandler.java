package project.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.List;
import project.dto.UserDto;

public class SumHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // 파일 경로 지정
        String filepath = "/Users/jkyoon/myproject/java_server/data/user.json";

        // 파일 읽기
        Reader fileReader = new FileReader(filepath);

        // 읽은 내용 List에 저장
        Gson gson = new Gson();
        List<UserDto> users;
        users = gson.fromJson(fileReader, new TypeToken<List<UserDto>>(){}.getType());

        // Sum 계산
        int sumPostCount = 0;
        for(UserDto u : users) {
            sumPostCount += u.getPostCount();
        }

        // JSON 오브젝트 생성 후 문자열 변환
        JsonObject jo = new JsonObject();
        jo.addProperty("sum", sumPostCount);
        String res = jo.toString();

        // 헤더 설정
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, res.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(res.getBytes());
        os.close();
    }
}
