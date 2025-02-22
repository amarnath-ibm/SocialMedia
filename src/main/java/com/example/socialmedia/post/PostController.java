package com.example.socialmedia.post;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialmedia.post.PostService;
import com.example.socialmedia.user.User;
import com.example.socialmedia.user.UserNotFoundException;
import com.example.socialmedia.user.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/{id}")
public class PostController {

    private PostService postService;
    private UserService userService;

    public PostController(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }
    
    @GetMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< List<Post> > getAllPostsByUser(@PathVariable Long id){
        User user = userService.findUser(id);
        if(user == null)
            throw new UserNotFoundException("User doesn't exist with id: " + id);

        List<Post> posts = user.getPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping(value = "/posts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getAllPostsByUser(@PathVariable Long id, @PathVariable Long postId){
        User user = userService.findUser(id);
        if(user == null)
            throw new UserNotFoundException("User doesn't exist with id: " + id);

        Predicate<Post> predicate = post -> post.getId() == postId;
        Post post = user.getPosts().stream().filter(predicate).findFirst().orElse(null);
        if(post == null)
            throw new PostNotFoundException("User doesn't exist with id: " + id);
        return ResponseEntity.ok(post);
    }


    @PostMapping("/posts")
    public ResponseEntity<EntityModel<Post>> createPost(@PathVariable Long id,@Valid @RequestBody Post post){
        User user = userService.findUser(id);
        System.out.println("post from user: " +  " id passed "  + id);
        if(user == null)
            throw new UserNotFoundException("User doesn't exist with id: " + id);
        post.setUser(user);
        System.out.println("post from user: " + post);
        // user.getPosts().add(post);
        // List<Post> posts = user.getPosts();
        // posts.add(post);
        // user.setPosts(posts);
        // userService.save(user);
        //postService.save(post);
        return ResponseEntity.ok(EntityModel.of(post));
    }
        
}
