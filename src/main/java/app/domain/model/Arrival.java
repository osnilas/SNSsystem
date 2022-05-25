package app.domain.model;

import java.time.LocalDateTime;

public class Arrival {

    private SNSuser snSuser;

    private LocalDateTime timeOfArrival;

    public Arrival (SNSuser snSuser) {
        this.snSuser = snSuser;
        this.timeOfArrival = LocalDateTime.now();
    }

    public SNSuser getSnSuser() {
        return snSuser;
    }

    public LocalDateTime getTimeOfArrival() {
        return timeOfArrival;
    }
}
