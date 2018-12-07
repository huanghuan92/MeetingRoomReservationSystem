/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.meetingroomreservation;

/**
 *
 * @author hh
 */
public class RoomsSetUp {
    private int roomNumber = 4;
    private Room[] rooms = new Room[roomNumber];
    
    //Create room with name
    public void initRoom() {
       for (int i = 0; i < roomNumber; i++) {
           rooms[i] =new Room("R" + (i + 1));      
       }
    }
    
    public Room[] getRooms() {
        return this.rooms;
    }
}
