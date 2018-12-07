/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingroomreservationsystem;

/**
 *
 * @author hh
 */
public class RoomsSetUp {
    private int roomNumber = 4;
    private Room[] allRooms = new Room[roomNumber];
    
    public void initRoomName() {
       for (int i = 0; i < 4; i++) {
           allRooms[i] =new Room("R" + (i + 1));
       }
    }
    
    public Room[] getAllRooms() {
        return this.allRooms;
    }
}
