package in.blue.service.impl;

import in.blue.model.Post;
import in.blue.service.PostsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static in.blue.helper.PostsHelper.httpHeaders;
import static in.blue.helper.PostsHelper.wrapHeaders;

@Service
public class PostsServiceImpl implements PostsService {

    @Value("${posts.base.url}")
    private String postsBaseUrl;

    private final RestTemplate restTemplate;

    public PostsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Demonstrates RestTemplate#exchange call gor GET operation with Headers
     * Response body is returned as String
     * @return response as String
     */
    @Override
    public String getPost(Integer postId) {
        //Setting the headers happen by invoking methods in PostsHelper class

        //Set the headers using RestTemplate#exchange method as getForEntity or getForObject doesn't support sending headers
        ResponseEntity<String> response = restTemplate
                .exchange(
                        postsBaseUrl + "/posts/{id}",
                        HttpMethod.GET,
                        wrapHeaders(httpHeaders()),
                        String.class,
                        Map.of("id", postId)
                );

        return response.getStatusCode().is2xxSuccessful() ? response.getBody() : "";
    }

    /**
     * Demonstrates RestTemplate#exchange call gor GET operation with Headers
     * Response body is returned as Pojo
     * @return response as posts pojo
     */
    @Override
    public List<Post> getPosts() {
        //Set required header values here
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        //wrap the headers to HttpEntity
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        //Set the headers using RestTemplate#exchange method as getForEntity or getForObject doesn't support sending headers
        ResponseEntity<List<Post>> response = restTemplate
                .exchange(
                        postsBaseUrl + "/posts",
                        HttpMethod.GET,
                        httpEntity,
                        new ParameterizedTypeReference<>() {}
                );

        return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
    }

    @Override
    public Post createPost(Post post) {
        //Set required header values here
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        //wrap the headers to HttpEntity
        HttpEntity<Post> httpEntity = new HttpEntity<>(post, httpHeaders);

        ResponseEntity<Post> response = restTemplate
                .exchange(
                        postsBaseUrl + "/posts",
                        HttpMethod.POST,
                        httpEntity,
                        Post.class
                );
        return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
    }

}
