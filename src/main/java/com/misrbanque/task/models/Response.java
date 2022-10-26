package com.misrbanque.task.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Response<T> {

    private boolean success;

    private T data;

    public static Response<?> fail(String message) {
        return new Response<>(false, message);
    }

    public static <T> Response<T> succeed(T data) {
        return new Response<>(true, data);
    }

}
