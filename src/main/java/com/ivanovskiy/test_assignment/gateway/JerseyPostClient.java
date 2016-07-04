package com.ivanovskiy.test_assignment.gateway;

import com.ivanovskiy.test_assignment.model.Post;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
@Component
public class JerseyPostClient implements PostClient{

    private static final Logger logger = Logger.getLogger(JerseyPostClient.class);

    @Value("${post.resource.host}")
    String postHost;

    @Value("${post.resource.path}")
    String postPath;

    public Post getPost(long id) {
        logger.info(String.format("Start of getPost with id: %d, connection link: %s/%s",id, postHost, postPath ));
        Client client = ClientFactory.create();
        WebTarget target = client.target(postHost).path(postPath).path(String.valueOf(id));
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
        return response.readEntity(Post.class);
    }

}
