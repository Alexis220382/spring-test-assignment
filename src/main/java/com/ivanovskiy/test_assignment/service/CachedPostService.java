package com.ivanovskiy.test_assignment.service;

import com.ivanovskiy.test_assignment.dao.PostDao;
import com.ivanovskiy.test_assignment.gateway.PostClient;
import com.ivanovskiy.test_assignment.model.Post;
import com.ivanovskiy.test_assignment.service.exception.PostServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
@Service
@Transactional
public class CachedPostService implements PostService{

    private static final Logger logger = Logger.getLogger(CachedPostService.class);

    @Autowired
    PostDao postDao;

    @Autowired
    PostClient postClient;

    @Override
    public Post findById(long id) throws PostServiceException {
        logger.info(String.format("Start of findById with id: %d",id));
        //DB works as cache
        Post post = postDao.read(id);
        if(post == null){
            try{
                post = postClient.getPost(id);
            }catch (Exception ex){
                logger.error("Error during post fetching", ex);
                throw new PostServiceException("Error during post fetching", PostServiceException.ErrorCode.POST_FETCHING_ERROR);
            }
            postDao.persist(post);
        }
        return post;


    }

    @Override
    public List<Post> findAll() {
        return postDao.list();
    }
}
