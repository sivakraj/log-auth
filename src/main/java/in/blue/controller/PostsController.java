package in.blue.controller;

import in.blue.model.Post;
import in.blue.service.PostsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    /**Using @Autowired is discouraged as it has to do extra operations using reflection to find the bean
    Constructor injection of required beans as shown below is the best approach**/
    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<String> getAPost(@PathVariable Integer postId) {
        final String post = postsService.getPost(postId);
        return new ResponseEntity<>(post, StringUtils.isEmpty(post) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postsService.getPosts());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postsService.createPost(post));
    }

}
