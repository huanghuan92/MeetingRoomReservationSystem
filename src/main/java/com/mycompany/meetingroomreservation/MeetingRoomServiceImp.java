/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.meetingroomreservation;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

/**
 *
 * @author hh
 */
public class MeetingRoomServiceImp implements MeetingRoomService {
    
    /**
     * Key is the owner id and value is the owner entity. 
     * Use this map to reflect the owner id to ownerEntity.
     */
    static Map<String, OwnerEntity> ownerMap = new HashMap<>();
    RoomsSetUp roomSetUp = new RoomsSetUp();
    
    public void init() {
        roomSetUp.initRoom();
    }

    /**
     * Returns all the rooms
     */
    public Room[] getRooms() {   
        return roomSetUp.getRooms();
    }

    /**
     * Reserves any available room for the given time range. It also handles "no
     * availability" appropriately
     */
    public String reserve(LocalTime start, LocalTime end, String owner) {
        Room[] rooms = roomSetUp.getRooms();
        int free = checkAvailability(start, end); 
        //If there is available room
        if (free != -1) {
            if (!ownerMap.containsKey(owner)) {
                ownerMap.put(owner, new OwnerEntity(owner));
            }
            //Add the new time range to meeting room.
            rooms[free].occupiedTimeRange.add(new TimeRange(start, end));
            Map<String, LinkedList<TimeRange>> reservation = ownerMap.get(owner).getReservatioin();
            if (!reservation.containsKey(rooms[free].getRoomName())) {
                reservation.put(rooms[free].getRoomName(), new LinkedList<TimeRange>());
            }
            //add the new time range to owner
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
    public String getReservations(String owner) {
        if (!ownerMap.containsKey(owner)) {
            return new String("You have no reservation");
        }
        Map<String, LinkedList<TimeRange>> reservation = ownerMap.get(owner).getReservatioin();
        
        String response = "";
        //List all meeting rooms and their booked time range
        for (String meetingRoom: reservation.keySet()) {
            response += "Room " + meetingRoom + ": ";
            for (int i = 0; i < reservation.get(meetingRoom).size(); i++) {
                TimeRange timeRange = reservation.get(meetingRoom).get(i);
                response += timeRange.startTime.toString() + "-" + timeRange.endTime.toString();
                if (i < reservation.get(meetingRoom).size() - 1) {
                    response += ", ";
                } else {
                    response += ". ";
                }
            }
        }
        return response;
    }

    /**
     * Cancels all reservations owned by the owner for the given time range
     */
    public String cancel(String owner, LocalTime start, LocalTime end) {
        
        Map<String, LinkedList<TimeRange>> reservation = ownerMap.get(owner).getReservatioin();
        Room[] rooms = roomSetUp.getRooms();
        int count = 0;
        Iterator<Map.Entry<String, LinkedList<TimeRange>>> iterator = reservation.entrySet().iterator(); 
        while(iterator.hasNext()) { 
            Map.Entry<String, LinkedList<TimeRange>> entry = iterator.next();
            //check and update the owener's reservation
            //if owner owns the given time range reservation
            for (int i = 0; i < reservation.get(entry.getKey()).size(); i++) {
                if (reservation.get(entry.getKey()).get(i).startTime.equals(start) && 
                    reservation.get(entry.getKey()).get(i).endTime.equals(end)) {
                    //if the booked meeting room just contains the given time range
                    if (reservation.get(entry.getKey()).size() == 1) {
                        iterator.remove();
                        count++;
                        break;
                    } else {
                        reservation.get(entry.getKey()).remove(i);
                        count++;
                    }   
                }
            }
            int roomIndex = Integer.parseInt(entry.getKey().substring(1)) - 1;       
            List<TimeRange> occupiedTimeRange = rooms[roomIndex].occupiedTimeRange;
            //check and update meeting room booked time
            //if the meeting room was book for the given time
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

    /**
     * if there is available room for the given time range, return room number
     * of first found avaliable room. If there is not available room, return -1.
     */
    public int checkAvailability(LocalTime start, LocalTime end) {
        Room[] rooms = roomSetUp.getRooms();
        for (int i = 0; i < rooms.length; i++) {
            List<TimeRange> occupiedTimeRange = rooms[i].occupiedTimeRange;
            int j = 0;
            for (; j < occupiedTimeRange.size(); j++) {
                TimeRange timeRange = occupiedTimeRange.get(j);
                if (!(timeRange.startTime.isAfter(end) || timeRange.startTime.equals(end)
                        || timeRange.endTime.isBefore(start) || timeRange.endTime.equals(start))) {
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
