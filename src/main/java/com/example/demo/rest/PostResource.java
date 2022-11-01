package com.example.demo.rest;

import com.example.demo.Entity.PostData;
import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostResource {
    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;

    }
    @PostMapping("/posts")
    public ResponseEntity save(@RequestBody Post post){
        Post result =postService.save(post);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/post")
    public ResponseEntity show(@RequestParam Long postId){
        List<Post> result =postService.findAllByQueryParam(postId);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/posts")
    public ResponseEntity findAll(){
        Object result = postService.findAll();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/posts/pageable")
    public ResponseEntity getAll(Pageable pageable){
    Page<PostData> smth =postService.findAL(pageable);
    return ResponseEntity.ok(smth);
}}
