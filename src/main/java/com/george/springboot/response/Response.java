package com.george.springboot.response;

public class Response {
    private final String errorMessage;
    private final boolean error;

    public Response(boolean error){
        this.error = error;
        this.errorMessage = "";
    }
    public Response(boolean error, String errorMessage){
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isError() {
        return error;
    }

    public String toString() { return error + " " + errorMessage; }
}
