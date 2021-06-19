package com.george.birdefense.request;

import com.george.birdefense.Player;

public class CreateRequest extends Request {
    private final Player player;
    public CreateRequest(Player player, String type){
        super(player.getId(), type);
        this.player = player;
    }
    public Player getPlayer() { return player; }
}
