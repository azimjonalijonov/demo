package com.example.demo.service;

import com.example.demo.Entity.PostData;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostDataService {
private final PostRepository postRepository;

    public PostDataService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public PostData save(PostData postData){
        PostData res = postRepository.save(postData);
        return res;
    }
    public List<PostData> saveAll(Post[] posts){
        List<PostData> postDataList =new ArrayList<>();
                for(Post post:posts){
                    PostData postData =new PostData();
                    postData.setPostId(post.getId());
                    postData.setUserId(post.getUserId());
                    postData.setTitle(post.getTitle());
                    postData.setBody(post.getBody());
                    postDataList.add(postData);
                }

        return postRepository.saveAll(postDataList);

    }
    @Transactional(readOnly = true)
    public Page<PostData> findAll(Pageable pageable){
        return postRepository.findAll(pageable);
}



  }


