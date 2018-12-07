/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingroomreservationsystem;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;

/**
 *
 * @author hh
 */
public class Room {

    private String roomName = "";
    List<TimeRange> occupiedTimeRange;

    public Room(String roomName) {
        this.roomName = roomName;
        occupiedTimeRange = new LinkedList<>();
        Collections.sort(occupiedTimeRange, new Comparator<TimeRange>() {
            public int compare(TimeRange t1, TimeRange t2) {
                return t1.startTime.compareTo(t2.startTime);
            }
        });
    }

//    public void setRoomName(String roomName) {
//        this.roomName = roomName;
//    }

    public String getRoomName() {
        return roomName;
    }

    public List<TimeRange> getOccupiedTimeRange() {
        return this.occupiedTimeRange;
    }
}
