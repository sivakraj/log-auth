package in.blue.service;

import in.blue.model.Post;

import java.util.List;

public interface PostsService {

    String getPost(final Integer postId);

    List<Post> getPosts();

    Post createPost(Post post);

}
