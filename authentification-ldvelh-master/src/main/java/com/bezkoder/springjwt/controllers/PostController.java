package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.PostDto;
import com.bezkoder.springjwt.models.Post;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SearchRequest;
import com.bezkoder.springjwt.repository.PostRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.PostService;
import com.bezkoder.springjwt.security.services.impl.UserDetailsImpl;
import com.bezkoder.springjwt.util.FileUtilePost;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    @PostMapping("/{idUser}")
    public User createPost(@PathVariable("idUser") final Long idUser, @RequestBody PostDto postDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Long id = optionalUser.get().getId();
        return postService.savePost(postDto, id);
    }

    @GetMapping("")
    public Iterable<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable("id") final Long id) {
        Optional<Post> post = postService.getPost(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            return null;
        }
    }

    @PostMapping("/searchProject")
    public List<Post> searchPostByName(@RequestBody SearchRequest searchRequest){
        List<Post> posts = null;
        if (searchRequest.getName().isEmpty()){
            posts = postService.getPosts();
        }else{
            posts=postService.searchPostByText(searchRequest);
        }
        return posts;
    }

    @PutMapping("")
    public ResponseEntity<String> updatePost(@RequestBody() PostDto postDto, Authentication authentication) {
        final UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Optional <User> userOptional = userRepository.findById(user.getId());

        if(postDto.getUserId() == userOptional.get().getId()){
            postService.updatePost(postDto);
            return new ResponseEntity<String>("update ok", HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Ce Projet ne vous appartient pas; Vous ne pourrez pas le modifier", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjet(@PathVariable("id") final Long id, Authentication authentication) {
        final UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Optional <User> userOptional = userRepository.findById(user.getId());
        Optional <Post> optionalPost = postService.getPost(id);
        if(optionalPost.get().getUserId() == userOptional.get().getId()){
//            userOptional.get().getPosts().remove(optionalPost);
            postService.postDelete(optionalPost.get().getId());
            return new ResponseEntity<String>("delete ok",HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Ce Projet ne vous appartient pas; Vous ne pourrez pas le supprimer", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/poster/{id}")
    public void deletePoster(@PathVariable("id")  final Long id){
        postService.postDelete(id);
    }

    /*---------------fonction upload----------------------------------------*/

    private Post upload(MultipartFile file, PostDto  posterDto ) {
        String fileName="";
        String lastFile="";
        if(file != null) {
            String[] nameExtension = Objects.requireNonNull(file.getContentType()).split("/");
            fileName="imgPost" + "." + nameExtension[1];
            lastFile = posterDto.getId() != null ?posterDto.getImage():"";

            posterDto.setImage(fileName);
        }
        Post poster = postRepository.save(modelMapper.map(posterDto,Post.class));
        if(file != null) {
            try {
                if(posterDto.getId()!=null) {
                    FileUtilePost.saveFileAndReplace(lastFile,file,fileName,poster.getId());
                }else {
                    FileUtilePost.saveFile(poster.getId(),fileName,file);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return poster;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin")
    public Post updateUPost(@RequestBody PostDto postDto){
        return postService.adminUpdatePost(postDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public void deletePostAdmin(@PathVariable("id") final Long id) {
        postService.adminDeletePost(id);
    }
}
