package app.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Arrival implements Serializable {

    private SNSuser snSuser;

    private LocalDateTime timeOfArrival;

    /**
     * @author Pedro Nogueira <1211613@isep.ipp.pt>
     * @param snSuser
     * Constructor
     * Registers/creates the arrival of an SNS User.
     */
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
