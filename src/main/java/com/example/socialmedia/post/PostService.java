package com.example.socialmedia.post;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.socialmedia.repository.PostRepository;

import jakarta.validation.Valid;

@Service
public class PostService {
    
    public PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository =  postRepository;
    }

    public Post getPostById(Long id){
        Optional<Post> opt = postRepository.findById(id);
        if(opt.isEmpty())
            return null;
        return opt.get();
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}
