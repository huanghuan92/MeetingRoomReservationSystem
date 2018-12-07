/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingroomreservationsystem;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import meetingroomreservationsystem.RoomsSetUp;
import meetingroomreservationsystem.OwnerManagement;

/**
 *
 * @author hh
 */
public class MeetingRoomServiceImp implements MeetingRoomService {
    
    static Map<String, OwnerEntity> ownerMap = new HashMap<>();
    RoomsSetUp roomSetUp = new RoomsSetUp();
    
     public void init() {
        roomSetUp.initRoomName();
    }

    /**
     * Returns all the rooms
     */

    public Room[] getRooms() {
        return roomSetUp.getAllRooms();
    }

    /**
     * Reserves any available room for the given time range. It also handles "no
     * availability" appropriately
     */
    public String reserve(LocalTime start, LocalTime end, String owner) {
        Room[] rooms = roomSetUp.getAllRooms();
        int free = checkAvailability(start, end);    
        if (free != -1) {
            if (!ownerMap.containsKey(owner)) {
                ownerMap.put(owner, new OwnerEntity(owner));
            }
            rooms[free].occupiedTimeRange.add(new TimeRange(start, end));
            Map<String, LinkedList<TimeRange>> reservation = ownerMap.get(owner).getReservatioin();
            if (!reservation.containsKey(rooms[free].getRoomName())) {
                reservation.put(rooms[free].getRoomName(), new LinkedList<TimeRange>());
            }
            reservation.get(rooms[free].getRoomName()).add(new TimeRange(start, end));
            return new String("Reserve Room " + rooms[free].getRoomName() +
                    " in time range "  + start.toString() + " to " + end.toString() + " successfully");
        }

        return new String("Reservation failed. No Availablity");
    }

    /**
     * Checks if any room is available for the given time range.
     */
    public String isAvailable(LocalTime start, LocalTime end) {
        int free = checkAvailability(start, end);
        if (free == -1) {
            return new String("No availability for time range "
                    + start.toString() + " to " + end.toString());  
        }
        return new String("There is available room for time range "
                        + start.toString() + " to " + end.toString());
    }

    /**
     * Fetches all reservations that are owned by the given owner
     */
    public Map<String, LinkedList<TimeRange>> getReservations(String owner) {
        return ownerMap.get(owner).getReservatioin();
    }

    /**
     * Cancels all reservations owned by the owner for the given time range
     */
    public String cancel(String owner, LocalTime start, LocalTime end) {
        
        Map<String, LinkedList<TimeRange>> reservation = ownerMap.get(owner).getReservatioin();
        Room[] rooms = roomSetUp.getAllRooms();
        int count = 0;
        //TimeRange timeRange = new TimeRange(start, end);
        
        for (String str : reservation.keySet()) {
            for (int i = 0; i < reservation.get(str).size(); i++) {
                if (reservation.get(str).get(i).startTime.equals(start) && 
                    reservation.get(str).get(i).endTime.equals(end)) {
                    reservation.get(str).remove(i);
                    count++;
                }
            }
            int roomIndex = Integer.parseInt(str.substring(1)) - 1;       
            List<TimeRange> occupiedTimeRange = rooms[roomIndex].occupiedTimeRange;
            for (int i = 0; i < occupiedTimeRange.size(); i++) {
                if (occupiedTimeRange.get(i).startTime.equals(start) && 
                    occupiedTimeRange.get(i).endTime.equals(end)) {
                    occupiedTimeRange.remove(i);
                }
            }
        }
        if (count == 0) {
            return new String("No reservation in time range "  + start.toString()
                    + " to " + end.toString());
        } else {
            return new String("Reservation in time range "  + start.toString()
                    + " to " + end.toString()+  " have been calcelled");
        }
    }

    public int checkAvailability(LocalTime start, LocalTime end) {
        Room[] rooms = roomSetUp.getAllRooms();

        for (int i = 0; i < rooms.length; i++) {
            List<TimeRange> occupiedTimeRange = rooms[i].occupiedTimeRange;
            int j = 0;
            for (; j < occupiedTimeRange.size(); j++) {
                TimeRange timeRange = occupiedTimeRange.get(j);
                if (!(timeRange.startTime.isBefore(start) || timeRange.endTime.isAfter(end))) {
                    break;
                }
            }
            if (j == occupiedTimeRange.size()) {
                return i;
            }
        }
        return -1;
    }
}
