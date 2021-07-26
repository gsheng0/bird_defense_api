package com.george.springboot.request;

public class ChangeSideRequest extends Request{
    private final String roomCode;
    private final boolean isFromHost;
    public ChangeSideRequest(long id, String type, String roomCode, boolean isFromHost){
        super(id, type);
        this.roomCode = roomCode;
        this.isFromHost = isFromHost;
    }

    public String getRoomCode(){ return roomCode; }
    public boolean isFromHost() { return isFromHost; }
    public String toString() {
        return "id: " + super.getId() + " type: " + super.getType() + " roomcode: " + roomCode;
    }
}
