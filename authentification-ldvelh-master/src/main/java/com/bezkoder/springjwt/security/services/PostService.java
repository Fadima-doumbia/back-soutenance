package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.dto.PostDto;
import com.bezkoder.springjwt.models.Post;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SearchRequest;
import com.bezkoder.springjwt.repository.PostRepository;
import com.bezkoder.springjwt.repository.ProjectRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class PostService {
    @Autowired
    private ProjectRepository projectRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;


    public Optional<Post> getPost(final Long id){
        return postRepository.findById(id);
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public List<Post> searchPostByText (SearchRequest searchRequest){
        return postRepository.findByTexte(searchRequest.getName());
    }

    public void postDelete (Long id){
        Post projetOptional = postRepository.findById(id).get();
        User userOptional = userRepository.findById(projetOptional.getUserId()).get();
        userOptional.getProjects().remove(postRepository);
        userRepository.save(userOptional);

    }

    public Post updatePost(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
        return postRepository.save(post);
    }

    public User savePost(PostDto postDto, Long idUser){
        Optional<User> userOptional = userRepository.findById(idUser);
        Post post = modelMapper.map(postDto, Post.class);
        User user = null;
        if (userOptional.isPresent()){
            user = userOptional.get();
            user.getPosts().add(post);
            return userRepository.save(user);        }
        return user;
    }

    public void adminDeletePost(Long id){
        Post postOptional = postRepository.findById(id).get();
        User userOptional = userRepository.findById(postOptional.getUserId()).get();
        userOptional.getProjects().remove(postOptional);
        userRepository.save(userOptional);

    }

    public Post adminUpdatePost(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
        return postRepository.save(post);
    }
}
