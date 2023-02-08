package com.example.demo.service;

import com.example.demo.Entity.PostData;
import com.example.demo.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {
    private final RestTemplate restTemplate;
    private final PostDataService postDataService;

    @Value("${api.placeholder}")
    private String api;

    public PostService(RestTemplate restTemplate, PostDataService postDataService) {
        this.restTemplate = restTemplate;
        this.postDataService = postDataService;

    }
    public Post save(Post post){
        HttpHeaders headers =new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Post> entity =new HttpEntity<>(post,headers);
        Post result =restTemplate.postForObject(api+"/posts",entity,Post.class);
        return result;
    }
    public  Post update(Long id, Post post){
        HttpHeaders headers =new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <Post> entity =new HttpEntity<>(post,headers);
        Post result = restTemplate.exchange(api+"/posts"+id+"/comments",HttpMethod.PUT,entity,Post.class).getBody();
        return  result;
    }
    public List<Post> findAllByQueryParam(Long postId){
        HttpHeaders headers =new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity  <List<Post>> entity =new HttpEntity<>(headers);
        String uriTemplate = UriComponentsBuilder.fromHttpUrl(api+"/comments")
                .queryParam("postId","{postId}")
                .encode()
                .toUriString();
        Map<String, Long> params =new HashMap<>();
        params.put("postId",postId);

        List<Post>  result = restTemplate.exchange(uriTemplate,HttpMethod.GET,entity,List.class,params).getBody();
        return result;
    }


    public Object  findAll() {
         HttpHeaders headers = new HttpHeaders();
         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         HttpEntity<Post[]> entity =new HttpEntity<>(headers);
         Post[] result =restTemplate.exchange(this.api+"/posts", HttpMethod.GET,entity,Post[].class).getBody();
         postDataService.saveAll(result);
         return result;

     }
   public Page<PostData> findAL(Pageable pageable){
        return postDataService.findAll(pageable);
   }


}
