package com.ivanovskiy.test_assignment.service;

import com.ivanovskiy.test_assignment.model.Post;
import com.ivanovskiy.test_assignment.service.exception.PostServiceException;

import java.util.List;

/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
public interface PostService {

    Post findById(long id) throws PostServiceException;

    List<Post> findAll();
}

