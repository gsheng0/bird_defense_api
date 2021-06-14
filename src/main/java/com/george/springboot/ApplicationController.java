package com.george.springboot;

import com.george.springboot.request.CreateRequest;
import com.george.springboot.request.JoinRequest;
import com.george.springboot.request.Request;
import com.george.springboot.request.SyncRoomRequest;
import com.george.springboot.response.CreateResponse;
import com.george.springboot.response.JoinResponse;
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
    public CreateResponse createRoom(@RequestBody CreateRequest payload){
        Player player = payload.getPlayer();
        Room room = new Room(Room.generateCode(4));
        room.getPlayers().add(player);
        rooms.add(room);
        System.out.println(room.getCode());
        return new CreateResponse(room.getCode());

    }

    @PostMapping("/room/join")
    public JoinResponse joinRoom(@RequestBody JoinRequest payload){
        Player player = payload.getPlayer();
        String code = payload.getCode();
        System.out.println(code);
        for(Room room : rooms){
            if(room.getCode().equals(code))
            {
                if(room.getPlayers().size() >= 2)
                    return new JoinResponse(true, "Room is full");
                else {
                    room.addPlayer(player);
                    return new JoinResponse(room.getPlayers().get(0));
                }
            }
        }
        return new JoinResponse(true, "Room does not exist");

    }

    @PostMapping("/room/sync")
    public SyncRoomResponse syncRoom(@RequestBody SyncRoomRequest payload){
        String code = payload.getCode();
        Player player = payload.getPlayer();
        boolean started = payload.getStart();

        for(Room room : rooms){
            if(room.getCode().equals(code)){
                if(room.getPlayers().size() == 2){
                    if(started){
                        room.start();
                    }
                    if(room.getPlayers().get(0).getId() == player.getId())
                        return new SyncRoomResponse(room.getPlayers().get(1), room.getStarted());
                    return new SyncRoomResponse(room.getPlayers().get(0), room.getStarted());
                }
                else{
                    return new SyncRoomResponse(false);
                }
            }
        }
        return new SyncRoomResponse(false, "Room does not exist");
    }
}
