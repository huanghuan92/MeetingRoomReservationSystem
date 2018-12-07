/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.meetingroomreservation.MeetingRoomServiceImp;
import com.mycompany.meetingroomreservation.Room;
import java.time.LocalTime;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;

/**
 *
 * @author hh
 */
@RunWith(JUnitParamsRunner.class)
public class MeetingRoomReservationTest {

    static MeetingRoomServiceImp  meetingRoomServiceImp;
    static Room[] rooms;

    @BeforeClass
    public static void init() {
        meetingRoomServiceImp = new MeetingRoomServiceImp();
        meetingRoomServiceImp.init(); 
        rooms = meetingRoomServiceImp.getRooms(); 
    }

    @Test
    @Parameters(method = "testParameters")
    public void testReservationFunction(LocalTime start, LocalTime end, String owner,
            String operation, String expectedResponse) {
        String actualResponse = meetingRoomOperation(start, end, owner, operation);
        assertEquals(actualResponse, expectedResponse);
    }
    
     private String meetingRoomOperation(LocalTime start, LocalTime end, String owner, String operation) {
         String actualResponse = "";
         switch (operation) {
            case "reserve":
                actualResponse = meetingRoomServiceImp.reserve(start, end, owner);
                return actualResponse;
            case "isAvailable": 
                actualResponse = meetingRoomServiceImp.isAvailable(start, end);
                return actualResponse;
            case "cancel": 
                actualResponse = meetingRoomServiceImp.cancel(owner, start, end);
                return actualResponse;
            case "getReservations":
                actualResponse = meetingRoomServiceImp.getReservations(owner);
                return actualResponse;
         }
        return actualResponse;
    }

     /**
      * Parameter 1: start time
      * Parameter 2: end time
      * parameter 3: owner
      * Parameter 4: operation(reserve/isAvailable/cancel/)
      * Parameter 5: expected response
      */
    @SuppressWarnings("unused")
    private static Object[][] testParameters() throws Throwable {
        return new Object[][]{
            {LocalTime.parse("06:30"), LocalTime.parse("07:30"), "Kevin", "reserve",
                "Reserve Room R1 in time range 06:30 to 07:30 successfully"},
            {LocalTime.parse("08:30"), LocalTime.parse("09:30"), "John", "reserve",
                "Reserve Room R1 in time range 08:30 to 09:30 successfully"},
            {LocalTime.parse("10:30"), LocalTime.parse("12:30"), "kevin", "reserve",
                "Reserve Room R1 in time range 10:30 to 12:30 successfully"},
            {LocalTime.parse("10:30"), LocalTime.parse("12:30"), "John", "reserve",
                "Reserve Room R2 in time range 10:30 to 12:30 successfully"},
            {LocalTime.parse("07:30"), LocalTime.parse("09:30"), "Kelly", "reserve",
                "Reserve Room R2 in time range 07:30 to 09:30 successfully"},
            {LocalTime.parse("07:30"), LocalTime.parse("09:30"), "David", "reserve",
                "Reserve Room R3 in time range 07:30 to 09:30 successfully"},
            {LocalTime.parse("07:30"), LocalTime.parse("09:30"), "Tom", "reserve",
                "Reserve Room R4 in time range 07:30 to 09:30 successfully"},
            //The given time range is the same as no availability time range
            {LocalTime.parse("07:30"), LocalTime.parse("09:30"), "Mike", "reserve",
                "Reservation failed. No Availablity"},
            //The start time is before the start time of no availability time range
            //The end time is after the end time of no availability time range
            {LocalTime.parse("06:30"), LocalTime.parse("11:30"), "Mike", "reserve",
                "Reservation failed. No Availablity"},
            //The start time is equal to the start time of no availability time range
            //The end time is after the end time of no availability time range
            {LocalTime.parse("08:30"), LocalTime.parse("10:30"), "Mike", "reserve",
                "Reservation failed. No Availablity"},
            //The start time is after the start time of no availability time range
            //The end time is after the end time of no availability time range
            {LocalTime.parse("09:00"), LocalTime.parse("10:30"), "Mike", "reserve",
                "Reservation failed. No Availablity"},
            //The start time is before the start time of no availability time range
            //The end time is equal the end time of no availability time range
            {LocalTime.parse("06:30"), LocalTime.parse("09:30"), "Mike", "reserve",
                "Reservation failed. No Availablity"},
            //The start time is equal to the start time of no availability time range
            //The end time is before the end time of no availability time range
            {LocalTime.parse("08:30"), LocalTime.parse("09:00"), "Mike", "reserve",
                "Reservation failed. No Availablity"},
            //The start time is before the start time of no availability time range
            //The end time is before the end time of no availability time range
             {LocalTime.parse("06:30"), LocalTime.parse("09:00"), "Mike", "reserve",
                "Reservation failed. No Availablity"},
             //The start time is after the start time of no availability time range
            //The end time is equal to the end time of no availability time range
            {LocalTime.parse("09:00"), LocalTime.parse("09:30"), "Mike", "reserve",
                "Reservation failed. No Availablity"},
            //The start time is after the start time of no availability time range
            //The end time is after the end time of no availability time range
            {LocalTime.parse("09:00"), LocalTime.parse("09:20"), "Mike", "reserve",
                "Reservation failed. No Availablity"},
            
            //The given time range is the same as no availability time range
            {LocalTime.parse("07:30"), LocalTime.parse("09:30"), "", "isAvailable", 
                "No availability for time range 07:30 to 09:30"},
            //The start time is before the start time of no availability time range
            //The end time is after the end time of no availability time range
            {LocalTime.parse("06:30"), LocalTime.parse("11:30"), "", "isAvailable", 
                "No availability for time range 06:30 to 11:30"},
            //The start time is equal to the start time of no availability time range
            //The end time is after the end time of no availability time range          
            {LocalTime.parse("08:30"), LocalTime.parse("10:30"), "", "isAvailable", 
                "No availability for time range 08:30 to 10:30"},
            //The start time is after the start time of no availability time range
            //The end time is after the end time of no availability time range
            {LocalTime.parse("09:00"), LocalTime.parse("10:30"), "", "isAvailable", 
                "No availability for time range 09:00 to 10:30"},
             //The start time is before the start time of no availability time range
            //The end time is equal the end time of no availability time range
            {LocalTime.parse("06:30"), LocalTime.parse("09:30"), "", "isAvailable", 
                "No availability for time range 06:30 to 09:30"},
            //The start time is equal to the start time of no availability time range
            //The end time is before the end time of no availability time range
            {LocalTime.parse("08:30"), LocalTime.parse("09:00"), "", "isAvailable", 
                "No availability for time range 08:30 to 09:00"},
            //The start time is before the start time of no availability time range
            //The end time is equal the end time of no availability time range
            {LocalTime.parse("06:30"), LocalTime.parse("09:00"), "", "isAvailable", 
                "No availability for time range 06:30 to 09:00"},
            //The start time is after the start time of no availability time range
            //The end time is equal to the end time of no availability time range
            {LocalTime.parse("09:00"), LocalTime.parse("09:30"), "", "isAvailable", 
                "No availability for time range 09:00 to 09:30"},
            //The start time is after the start time of no availability time range
            //The end time is after the end time of no availability time range
            {LocalTime.parse("09:00"), LocalTime.parse("09:20"), "", "isAvailable", 
                "No availability for time range 09:00 to 09:20"},
            {LocalTime.parse("07:30"), LocalTime.parse("08:30"), "", "isAvailable", 
                "There is available room for time range 07:30 to 08:30"},   
            
            {null, null, "John", "getReservations",
                "Room R2: 10:30-12:30. Room R1: 08:30-09:30. "},
            {null, null, "Paul", "getReservations", "You have no reservation"},
             //The start time is before the start time of no availability time range
            //The end time is after the end time of no availability time range
             {LocalTime.parse("06:30"), LocalTime.parse("11:30"), "John", "cancel",
                "No reservation in time range 06:30 to 11:30"},
             //The start time is equal to the start time of no availability time range
            //The end time is after the end time of no availability time range  
            {LocalTime.parse("08:30"), LocalTime.parse("10:30"), "John", "cancel",
                "No reservation in time range 08:30 to 10:30"},
            //The start time is after the start time of no availability time range
            //The end time is after the end time of no availability time range
            {LocalTime.parse("09:00"), LocalTime.parse("10:30"), "John", "cancel",
                "No reservation in time range 09:00 to 10:30"},
             //The start time is before the start time of no availability time range
            //The end time is equal the end time of no availability time range
            {LocalTime.parse("06:30"), LocalTime.parse("09:30"), "John", "cancel",
                "No reservation in time range 06:30 to 09:30"},
            //The start time is equal to the start time of no availability time range
            //The end time is before the end time of no availability time range
            {LocalTime.parse("08:30"), LocalTime.parse("09:00"), "John", "cancel",
                "No reservation in time range 08:30 to 09:00"},
            //The start time is before the start time of no availability time range
            //The end time is equal the end time of no availability time range
            {LocalTime.parse("06:30"), LocalTime.parse("09:00"), "John", "cancel",
                "No reservation in time range 06:30 to 09:00"},
            //The start time is after the start time of no availability time range
            //The end time is equal to the end time of no availability time range
            {LocalTime.parse("09:00"), LocalTime.parse("09:30"), "John", "cancel",
                "No reservation in time range 09:00 to 09:30"},
            //The start time is after the start time of no availability time range
            //The end time is after the end time of no availability time range
            {LocalTime.parse("09:00"), LocalTime.parse("09:20"), "John", "cancel",
                "No reservation in time range 09:00 to 09:20"},
            {LocalTime.parse("08:30"), LocalTime.parse("09:30"), "John", "cancel",
                "Reservation in time range 08:30 to 09:30 have been calcelled"},
            {null, null, "John", "getReservations", "Room R2: 10:30-12:30. "},
            {LocalTime.parse("07:30"), LocalTime.parse("09:30"), "", "isAvailable",
                "There is available room for time range 07:30 to 09:30"},
            {LocalTime.parse("07:30"), LocalTime.parse("09:30"), "Mike", "reserve",
                "Reserve Room R1 in time range 07:30 to 09:30 successfully"},
            {null, null, "Mike", "getReservations", "Room R1: 07:30-09:30. "},};   
    }
}
