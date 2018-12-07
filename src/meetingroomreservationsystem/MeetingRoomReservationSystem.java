/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingroomreservationsystem;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author hh
 */
public class MeetingRoomReservationSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        MeetingRoomServiceImp meetingRoomServiceImp = new MeetingRoomServiceImp();
        meetingRoomServiceImp.init();
        Room[] rooms= meetingRoomServiceImp.getRooms();
        for (int i = 0; i < rooms.length; i++) {
            System.out.println(rooms[i].getRoomName());
        }
        String str = meetingRoomServiceImp.reserve(LocalTime.parse("06:30"), LocalTime.parse( "07:30"), "Kevin");
        System.out.println(str);
        str = meetingRoomServiceImp.reserve(LocalTime.parse("08:30"), LocalTime.parse( "09:30"), "John");
        System.out.println(str);
        str = meetingRoomServiceImp.reserve(LocalTime.parse("07:30"), LocalTime.parse( "09:30"), "John");
        System.out.println(str);
        str = meetingRoomServiceImp.reserve(LocalTime.parse("07:30"), LocalTime.parse( "09:30"), "John");
        System.out.println(str);
        str = meetingRoomServiceImp.reserve(LocalTime.parse("07:30"), LocalTime.parse( "09:30"), "John");
        System.out.println(str);
        str = meetingRoomServiceImp.isAvailable(LocalTime.parse("10:30"), LocalTime.parse( "11:30"));
        System.out.println(str);
         str = meetingRoomServiceImp.reserve(LocalTime.parse("07:30"), LocalTime.parse( "09:30"), "John");
        System.out.println(str);
        
        Map<String, LinkedList<TimeRange>> reservation = meetingRoomServiceImp.getReservations("John");
        for (String temp : reservation.keySet()) {
            for (int i = 0; i < reservation.get(temp).size(); i++) {
                System.out.println(temp + reservation.get(temp).get(i).startTime + reservation.get(temp).get(i).endTime);
            }
        }
        str = meetingRoomServiceImp.cancel("John", LocalTime.parse("07:30"), LocalTime.parse( "09:30"));
        System.out.println(str);
        reservation = meetingRoomServiceImp.getReservations("John");
        for (String temp : reservation.keySet()) {
            for (int i = 0; i < reservation.get(temp).size(); i++) {
                System.out.println(temp + reservation.get(temp).get(i).startTime + reservation.get(temp).get(i).endTime);
            }
        }
         str = meetingRoomServiceImp.reserve(LocalTime.parse("07:30"), LocalTime.parse( "09:30"), "John");
        System.out.println(str);
    }
    
}
