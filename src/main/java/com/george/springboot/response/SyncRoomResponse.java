package com.george.springboot.response;

import com.george.springboot.Player;

public class SyncRoomResponse extends Response {
    private final Player player;
    private final boolean other;
    private final boolean start;
    private final String side;
    private boolean changeSideRequest = false;
    public SyncRoomResponse(boolean error, String errorMessage){
        super(error, errorMessage);
        this.player = null;
        other = false;
        start = false;
        this.side = "";
    }
    public SyncRoomResponse(Player player, boolean start, String side, boolean isRequest){
        super(false, "");
        this.player = player;
        other = true;
        this.start = start;
        this.side = side;
        this.changeSideRequest = isRequest;
    }
    public SyncRoomResponse(boolean other){
        super(false, "");
        this.other = other;
        player = null;
        start = false;
        side = "";
    }

    public String getSide() { return side; }
    public boolean getStart() { return start; }
    public Player getPlayer() { return player; }
    public boolean getOther() { return this.other; }
    public boolean isChangeSideRequest() { return changeSideRequest; }
}
