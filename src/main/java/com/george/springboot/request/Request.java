package com.george.springboot.request;

public class Request {
    private final long id;
    private final String type;
    public Request(long id, String type){
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getType() { return type;}

    public String toString() {
        return "id: " + id + " type: " + type;
    }
}
