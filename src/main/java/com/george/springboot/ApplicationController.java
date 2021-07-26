package com.george.springboot;

import com.george.springboot.request.*;
import com.george.springboot.response.ChangeSideResponse;
import com.george.springboot.response.CreateRoomResponse;
import com.george.springboot.response.JoinRoomResponse;
import com.george.springboot.response.SyncRoomResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin("*")
@RestController //marks class that handles request mapping
public class ApplicationController {
    private final AtomicLong counter = new AtomicLong();
    private int userId = -1;
    private ArrayList<Integer> userIds = new ArrayList<>();
    private ArrayList<Action> actions = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

    @GetMapping("/")
    public String home(){
        return "Bird Defense API Home";
    }

    @PostMapping("/send")
    public void process(@RequestBody Action payload)
            throws Exception {
        actions.add(payload);

    }

    @GetMapping("/join")
    public int join(HttpServletRequest request){
        userId++;
        userIds.add(userId);
        return userId;
    }

    @PostMapping("/sync")
    public ArrayList<Action> sync(@RequestBody Request payload){
        long id = payload.getId();
        ArrayList<Action> out = new ArrayList<>();
        for(Action action : actions){
            if(action.getId() != id)
                out.add(action);
        }
        for(Action action : out){
            actions.remove(action);
        }
        return out;
    }

    @PostMapping("/room/create")
    public CreateRoomResponse createRoom(@RequestBody CreateRoomRequest payload){
        Player player = payload.getPlayer();
        Room room = new Room(Room.generateCode(4));
        room.getPlayers().add(player);
        room.setBirdPlayer(player);
        room.setHost(player);
        rooms.add(room);
        System.out.println(room.getCode());
        return new CreateRoomResponse(room.getCode());

    }

    @PostMapping("/room/join")
    public JoinRoomResponse joinRoom(@RequestBody JoinRoomRequest payload){
        Player player = payload.getPlayer();
        String code = payload.getCode();
        System.out.println(code);
        for(Room room : rooms){
            if(room.getCode().equals(code))
            {
                if(room.getPlayers().size() >= 2)
                    return new JoinRoomResponse(true, "Room is full");
                else {
                    if(room.getBirdPlayer() == null){
                        room.setBirdPlayer(player);
                    }
                    else{
                        room.setBatPlayer(player);
                    }
                    room.addPlayer(player);
                    if(room.getPlayers().get(0).getId() == room.getBatPlayer().getId())
                        return new JoinRoomResponse(room.getPlayers().get(0), "bat");
                    else
                        return new JoinRoomResponse(room.getPlayers().get(0), "bird");
                }
            }
        }
        return new JoinRoomResponse(true, "Room does not exist");

    }

    @PostMapping("/room/change")
    public ChangeSideResponse changeSide(@RequestBody ChangeSideRequest payload){

        for(Room room : rooms){
            if(room.getCode().equals(payload.getRoomCode())){
                if(payload.isFromHost())
                    room.swapSides();
                else
                    room.setRequestSwitch(true);
                return new ChangeSideResponse(false, "");
            }
        }
        return new ChangeSideResponse(true, "Room not found");

    }

    @PostMapping("/room/sync")
    public SyncRoomResponse syncRoom(@RequestBody SyncRoomRequest payload){
        String code = payload.getCode();
        Player player = payload.getPlayer();
        boolean started = payload.getStart();


        for(Room room : rooms){
            if(room.getCode().equals(code)){
                boolean request = room.isRequestSwitch();
                if(room.getPlayers().size() == 2){
                    if(started){
                        room.start();
                    }
                    if(room.getPlayers().get(0).getId() == player.getId()) {
                        if(room.getBatPlayer().getId() == player.getId()){ //If the current player is the bat player, then the other player is the bird player
                            return new SyncRoomResponse(room.getPlayers().get(1), room.getStarted(), "bird", request);
                        }
                        else{
                            return new SyncRoomResponse(room.getPlayers().get(1), room.getStarted(), "bat", request);
                        }

                    }
                    else {
                        if(room.getBatPlayer().getId() == player.getId()){
                            return new SyncRoomResponse(room.getPlayers().get(0), room.getStarted(), "bird", request);
                        }
                        else{
                            return new SyncRoomResponse(room.getPlayers().get(0), room.getStarted(), "bat", request);
                        }

                    }
                }
                else{
                    return new SyncRoomResponse(false);
                }
            }
        }
        return new SyncRoomResponse(false, "Room does not exist");
    }
}
