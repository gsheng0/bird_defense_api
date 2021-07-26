package com.george.springboot;

import java.util.ArrayList;

public class Room {
    private final String code;
    private ArrayList<Player> players = new ArrayList<>();
    private boolean started = false;
    private Player host;
    private Player bird;
    private Player bat;
    private boolean requestSwitch = false;
    public Room(String code){
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void setRequestSwitch(boolean requestSwitch){
        this.requestSwitch = requestSwitch;
    }
    public boolean isRequestSwitch() { return requestSwitch; }
    public void setHost(Player host) {
        this.host = host;
        this.bird = host;
    }
    public void setBirdPlayer(Player player){
        bird = player;
    }
    public void setBatPlayer(Player player){
        bat = player;
    }
    public void swapSides(){
        Player temp = bat;
        bat = bird;
        bird = temp;
    }
    public Player getBirdPlayer() { return bird; }
    public Player getBatPlayer() { return bat; }
    public boolean getStarted() { return started; }
    public void start() { started = true; }
    public void stop() { started = false; }
    public void addPlayer(Player id) { players.add(id); }

    public static String generateCode(int length){
        String out = "";
        for(int i = 0; i < length; i++){
            int rand = (int)(Math.random() * 26) + 'A';
            char c = (char)rand;
            out += "" + c;
        }
        return out;
    }
}
