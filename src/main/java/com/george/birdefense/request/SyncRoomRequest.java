package com.george.birdefense.request;

import com.george.birdefense.Player;

public class SyncRoomRequest extends Request{
    private final String code;
    private final Player player;
    private final boolean start;
    public SyncRoomRequest(Player player, String type, String code, boolean start){
        super(player.getId(), type);
        this.code = code;
        this.player = player;
        this.start = start;
    }

    public boolean getStart() { return start; }

    public String getCode() {
        return code;
    }

    public Player getPlayer() {
        return player;
    }

    public String toString() {
        return "id: " + super.getId() + " type: " + super.getType() + " code: " + code + " start: " + start;

    }
}
