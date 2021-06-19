package com.george.birdefense;

import com.george.birdefense.request.Request;

public class Action extends Request {
    private final long id;
    private final int frameNumber;
    private final String type;
    private final double x;
    private final double y;
    public Action(long id, String type, double x, double y, int frameNumber){
        super(id, type);
        this.type = type;
        this.x = x;
        this.y = y;
        this.frameNumber = frameNumber;
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public int getFrameNumber() {
        return frameNumber;
    }
    public String getType() {
        return type;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String toString() {
        return type + " [" + x + ", " + y + "] " + frameNumber + " id: " + id + "\n";
    }
}
