package com.ivanovskiy.test_assignment.controller;

import com.ivanovskiy.test_assignment.dto.PostDto;
import com.ivanovskiy.test_assignment.model.Post;
import com.ivanovskiy.test_assignment.service.PostService;
import com.ivanovskiy.test_assignment.validation.ValidationUtils;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
@Controller
public class PostController {

    private static final Logger logger = Logger.getLogger(PostController.class);

    @Autowired
    PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value="v1/post", method = RequestMethod.GET)
    public @ResponseBody
    List<PostDto> getPosts() {
        List<Post> posts = postService.findAll();
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @RequestMapping(value="v1/post/{id}", method = RequestMethod.GET)
    public @ResponseBody  PostDto getPost(@PathVariable("id") long id) throws Exception {
        logger.info(String.format("Start of getPost with id: %d",id));
        ValidationUtils.validateId(id);
        PostDto postDto = modelMapper.map(postService.findById(id), PostDto.class);
        logger.debug(String.format("End of getPost with result: %s", String.valueOf(postDto.toString())));
        return postDto;
    }

}
