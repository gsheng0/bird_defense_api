package com.george.birdefense.response;

import com.george.birdefense.Player;

public class SyncRoomResponse extends Response {
    private final Player player;
    private final boolean other;
    private final boolean start;
    public SyncRoomResponse(boolean error, String errorMessage){
        super(error, errorMessage);
        this.player = null;
        other = false;
        start = false;
    }
    public SyncRoomResponse(Player player, boolean start){
        super(false, "");
        this.player = player;
        other = true;
        this.start = start;
    }
    public SyncRoomResponse(boolean other){
        super(false, "");
        this.other = other;
        player = null;
        start = false;
    }
    public boolean getStart() { return start; }
    public Player getPlayer() { return player; }
    public boolean getOther() { return this.other; }
}
