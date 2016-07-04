package com.ivanovskiy.test_assignment.dao;

import com.ivanovskiy.test_assignment.model.Post;

import java.util.List;

/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
public interface PostDao {

    void persist(Post post);

    Post read(long id);

    List<Post> list();
}
