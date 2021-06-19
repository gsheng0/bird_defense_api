package com.george.birdefense.response;

public class CreateResponse extends Response {
    private final String code;
    public CreateResponse(String code){
        super(false);
        this.code = code;
    }
    public CreateResponse(boolean error, String errorMessage){
        super(error, errorMessage);
        this.code = "";
    }
    public String getCode() { return code; }
}
