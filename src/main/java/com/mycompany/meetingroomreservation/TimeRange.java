/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.meetingroomreservation;

import java.time.LocalTime;

/**
 * This class is used to record a time range.
 */
public class TimeRange {
    LocalTime startTime;
    LocalTime endTime;
    public TimeRange(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
