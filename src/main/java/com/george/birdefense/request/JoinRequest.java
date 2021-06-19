package com.george.birdefense.request;

import com.george.birdefense.Player;

public class JoinRequest extends Request {
    private final String code;
    private final Player player;
    public JoinRequest(Player player, String type, String code){
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
