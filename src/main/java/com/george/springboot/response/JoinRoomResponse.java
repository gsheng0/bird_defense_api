package com.george.springboot.response;

import com.george.springboot.Player;

public class JoinRoomResponse extends Response{
    private final Player other;
    private final String side;
    public JoinRoomResponse(Player other, String side){
        super(false);
        this.other = other;
        this.side = side;
    }
    public JoinRoomResponse(boolean error, String errorMessage){
        super(error, errorMessage);
        other = null;
        side = "";
    }

    public Player getOtherPlayer() { return other; }
    public String getSide() { return side; }
}
