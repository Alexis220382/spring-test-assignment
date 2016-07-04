package com.ivanovskiy.test_assignment;

import com.ivanovskiy.test_assignment.dao.PostDao;
import com.ivanovskiy.test_assignment.gateway.PostClient;
import com.ivanovskiy.test_assignment.model.Post;
import com.ivanovskiy.test_assignment.service.CachedPostService;
import com.ivanovskiy.test_assignment.service.exception.PostServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {

    @InjectMocks
    private CachedPostService service = new CachedPostService();

    @Mock
    PostDao postDao;

    @Mock
    PostClient postClient;

    Post expectedPost;

    @Before
    public void setUp() {
        initMocks(this);
        expectedPost = new Post();
        expectedPost.setId(10l);
        expectedPost.setBody("test");
    }

    @Test
    public void testWithEmptyCache() throws PostServiceException {
        when(postDao.read(anyLong())).thenReturn(null);
        when(postClient.getPost(Mockito.anyInt())).thenReturn(expectedPost);

        Post actural = service.findById(20l);
        Assert.assertEquals(expectedPost, actural);
        Mockito.verify(postDao, Mockito.times(1)).read(20l);
        Mockito.verify(postDao, Mockito.times(1)).persist(expectedPost);
        Mockito.verify(postClient, Mockito.times(1)).getPost(20l);
    }

    @Test(expected=PostServiceException.class)
    public void testWithNoConnection() throws PostServiceException {
        when(postDao.read(anyLong())).thenReturn(null);
        when(postClient.getPost(Mockito.anyInt())).thenThrow(new RuntimeException());

        service.findById(20l);
    }
}

