package com.academy.project.rest.Controller;

public class ApiResponse<T> {
    private String id;
    private String message;
    private T data;

    public ApiResponse(String id, String message, T data) {
        this.id = id;
        this.message = message;
        this.data = data;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}