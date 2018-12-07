/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetingroomreservationsystem;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author hh
 */
public class TimeRange {
    LocalTime startTime;
    LocalTime endTime;
    TimeRange(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
