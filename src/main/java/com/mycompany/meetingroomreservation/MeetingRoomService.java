/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.meetingroomreservation;

import java.time.LocalTime;

/**
 *
 * @author hh
 */
public interface MeetingRoomService {

    /**
     * Returns all the rooms
     */
    public Room[] getRooms();

    /**
     * Reserves any available room for the given time range. It also handles "no
     * availability" appropriately
     */
    public String reserve(LocalTime start, LocalTime end, String owner);

    /**
     * Checks if any room is available for the given time range.
     */
    public String isAvailable(LocalTime start, LocalTime end);

    /**
     * Fetches all reservations that are owned by the given owner
     */
    public String getReservations(String owner);

    /**
     * Cancels all reservations owned by the owner for the given time range
     */
    public String cancel(String owner, LocalTime start, LocalTime end);

}
