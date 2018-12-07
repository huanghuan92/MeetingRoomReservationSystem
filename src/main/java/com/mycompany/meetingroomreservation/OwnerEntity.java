/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.meetingroomreservation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author hh
 */
public class OwnerEntity {
    private String ownerID;
    //Key is the meeting room. Value is the booked time of the meeing room
    private Map<String, LinkedList<TimeRange>> reservation = new HashMap<>();
    
    public OwnerEntity(String ownerID) {
        this.ownerID = ownerID;
    }
   
    public String getOwnerID() {
        return ownerID;
    }
    
    public Map<String, LinkedList<TimeRange>> getReservatioin() {
        return this.reservation;
    }
}
