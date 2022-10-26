package com.misrbanque.task.helpers;

import com.misrbanque.task.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerHelper {

    public static <T> ResponseEntity<Response<T>> formatResponse(T obj) {
        return new ResponseEntity<>(Response.succeed(obj), HttpStatus.OK);
    }

}
