package com.george.springboot.response;

public class ChangeSideResponse extends Response{
    public ChangeSideResponse(boolean error, String errorMessage){
        super(error, errorMessage);
    }
    public ChangeSideResponse(boolean error){
        super(error);
    }

}
