package com.george.springboot.response;

public class CreateRoomResponse extends Response {
    private final String code;
    public CreateRoomResponse(String code){
        super(false);
        this.code = code;
    }
    public CreateRoomResponse(boolean error, String errorMessage){
        super(error, errorMessage);
        this.code = "";
    }
    public String getCode() { return code; }
}
