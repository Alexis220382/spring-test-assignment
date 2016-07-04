package com.ivanovskiy.test_assignment.gateway;

import com.ivanovskiy.test_assignment.model.Post;

/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
public interface PostClient {

    Post getPost(long id);
}
