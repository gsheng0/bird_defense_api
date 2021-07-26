package com.george.springboot.request;

import com.george.springboot.Player;

public class JoinRoomRequest extends Request {
    private final String code;
    private final Player player;
    public JoinRoomRequest(Player player, String type, String code){
        super(player.getId(), type);
        this.code = code;
        this.player = player;
    }
    public Player getPlayer() { return player; }
    public String getCode() { return code; }
    public String toString() {
        return "id: " + super.getId() + " type: " + super.getType() + " code: " + code;
    }
}
