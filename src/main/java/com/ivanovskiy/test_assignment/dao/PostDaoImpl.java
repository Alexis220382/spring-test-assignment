package com.ivanovskiy.test_assignment.dao;

import com.ivanovskiy.test_assignment.model.Post;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
@Repository
public class PostDaoImpl implements PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void persist(Post post) {
        getSession().persist(post);
    }

    @Override
    public Post read(long id) {
        return  getSession().get(Post.class, id);
    }

    @Override
    public List<Post> list() {
        Criteria crit = getSession().createCriteria(Post.class);
        return crit.list();
    }
}

