
import java.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hh
 */

public class MeetingRoomReservationSystemTest {
    
    @Test
    @Parameters(method = "testParameters")
    //@Parameters(method = "testParameters")
    public void testMeetingRoomReservationSystem(LocalTime start, LocalTime end, String owner, String expectedResponse) {
        
        
    }
    @SuppressWarnings("unused")
    private static Object[][] testParameters() throws Throwable {
        // Parameters: loanAmount={0}, downPayment={1}, availableFunds={2}, expectApproved={3}, expectedMessage={4}
        return new Object[][] {
            { LocalTime.parse("06:30"), LocalTime.parse( "07:30"), "Kevin", "Reserve Room R1 in time range 06:30 to 07:30 successfully"}
//            { 1000.0f,  50.0f, 250.0f, false, "error.insufficient.down.payment"},
//            { 1000.0f, 200.0f, 150.0f, false, "error.insufficient.funds.for.down.payment" }
        };

    }
    
    
//    @SuppressWarnings("unused")
//    private static Object[][] testRequestLoan_Parameters() throws Throwable {
//        // Parameters: loanAmount={0}, downPayment={1}, availableFunds={2}, expectApproved={3}, expectedMessage={4}
//        return new Object[][] {
//            { 1000.0f, 200.0f, 250.0f,  true, null },
//            { 1000.0f,  50.0f, 250.0f, false, "error.insufficient.down.payment"},
//            { 1000.0f, 200.0f, 150.0f, false, "error.insufficient.funds.for.down.payment" }
//        };
//
//    }
    
}
