package com.george.springboot;

public class Player {
    private final long id;
    private String name;
    private final Side side;
    public enum Side{BIRD, BAT}

    public Player(long id, String name, Side side){
        this.id = id;
        this.name = name;
        this.side = side;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Side getSide() {
        return side;
    }
}

