package com.george.springboot.response;

import com.george.springboot.Player;

public class JoinResponse extends Response{
    private final Player other;
    public JoinResponse(Player other){
        super(false);
        this.other = other;
    }
    public JoinResponse(boolean error, String errorMessage){
        super(error, errorMessage);
        other = null;
    }

    public Player getOtherPlayer() { return other; }
}
