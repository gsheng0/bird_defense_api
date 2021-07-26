package com.george.springboot.request;

import com.george.springboot.Player;

public class CreateRoomRequest extends Request {
    private final Player player;
    public CreateRoomRequest(Player player, String type){
        super(player.getId(), type);
        this.player = player;
    }
    public Player getPlayer() { return player; }
}
