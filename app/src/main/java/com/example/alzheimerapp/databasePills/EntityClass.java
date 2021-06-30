package com.example.alzheimerapp.databasePills;

import androidx.annotation.NonNull;



public class EntityClass {


    String eventname;
    String eventdate;
    String eventtime;

    public EntityClass(String eventname, String eventdate, String eventtime) {
        this.eventname = eventname;
        this.eventdate = eventdate;
        this.eventtime = eventtime;
    }

    public EntityClass(String eventname, String eventdate) {
        this.eventname = eventname;
        this.eventdate = eventdate;
    }
    public EntityClass() {

    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }


    @NonNull
    @Override
    public String toString() {
        return eventname + "\n" + eventdate;
    }
}
