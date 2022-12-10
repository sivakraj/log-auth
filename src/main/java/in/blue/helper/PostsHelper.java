package in.blue.helper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

public class PostsHelper {

    private PostsHelper() {
        //A private constructor to avoid object creation
    }

    public static HttpHeaders httpHeaders() {
        //Set required header values here
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return httpHeaders;
    }

    public static HttpEntity<String> wrapHeaders(HttpHeaders httpHeaders) {
        //wrap the headers to HttpEntity
        return new HttpEntity<>(httpHeaders);
    }

}
