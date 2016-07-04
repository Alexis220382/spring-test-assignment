package com.ivanovskiy.test_assignment.validation;

import com.ivanovskiy.test_assignment.service.exception.PostServiceException;

/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
public class ValidationUtils {

    public static void validateId(long id) throws PostServiceException {
        if(id<=0){
            throw new PostServiceException("Invalide id. Id value should be greather than 0", PostServiceException.ErrorCode.BAD_REQUEST);
        }
    }
}
