package com.group3.backend.controller;

import java.time.LocalDate;

public class TimeTableDateRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private String matrNr;
    private boolean currentWeek;

    public TimeTableDateRequest(LocalDate startDate, LocalDate endDate, String matrNr, boolean currentWeek) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.matrNr = matrNr;
        this.currentWeek = currentWeek;
    }

    public TimeTableDateRequest() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(String matrNr) {
        this.matrNr = matrNr;
    }

    public boolean isCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(boolean currentWeek) {
        this.currentWeek = currentWeek;
    }
}
