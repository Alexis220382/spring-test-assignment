package com.ivanovskiy.test_assignment.service.exception;

import org.apache.http.HttpStatus;

/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
public class PostServiceException extends Exception{

    //we can add here some error code and other info
    public enum ErrorCode {
        POST_FETCHING_ERROR(HttpStatus.SC_INTERNAL_SERVER_ERROR),
        BAD_REQUEST(HttpStatus.SC_BAD_REQUEST);
        //and others

        private int numberValue;

        public int getNumberValue(){
            return numberValue;
        }

        ErrorCode(int numberValue){
            this.numberValue = numberValue;
        }


    }

    private ErrorCode errorCode;

    public PostServiceException(final String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
