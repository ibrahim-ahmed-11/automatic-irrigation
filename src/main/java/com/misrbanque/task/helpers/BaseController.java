package com.misrbanque.task.helpers;

import com.misrbanque.task.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected <T> ResponseEntity<Response<T>> formatResponse(T result) {
        Response<T> resultResponse = new Response<>(true, result);
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }

}
