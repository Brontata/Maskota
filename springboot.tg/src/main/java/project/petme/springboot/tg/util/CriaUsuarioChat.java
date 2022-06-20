package project.petme.springboot.tg.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CriaUsuarioChat {

    public static int criarUsuarioApiChat(String id, String username) throws IOException {
        System.out.println("### CRIANDO USUARIO NA API DE CHAT ###");
        int result;
        HttpPost post = new HttpPost("https://api-F598C1EF-B8C3-4311-94DE-57F4C7E619A9.sendbird.com/v3/users");

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"user_id\":\"" + id + "\",");
        json.append("\"nickname\":\"" + username + "\",");
        json.append("\"profile_url\":\"\"");
        json.append("}");

        // send a JSON data
        post.setEntity(new StringEntity(json.toString()));

        post.addHeader("Api-Token", "78292c710664e4e896c949437e00411b317f6cea");
        post.addHeader("content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            result = response.getStatusLine().getStatusCode();
        }

        if (result == 200) {
            System.out.println("### USUARIO CRIADO NA API DE CHAT COM SUCESSO ###");
        }

        return result;
    }

}
