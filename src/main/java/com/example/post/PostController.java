package com.example.post;

import com.example.location.Location;
import com.example.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value="/posts")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @RequestMapping(value = "/posts/{id}")
    public Post getPost(@PathVariable String id) {
        return postService.getPost( id);
    }


}
