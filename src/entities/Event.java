/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author ZC-Lenovo
 */
public class Event {
    private int idEvent;
    private String titleEvent,imageEvent,descriptionEvent,Organisation;
    private Date timeEvent;

    @Override
    public String toString() {
        return "Event{" + "idEvent=" + idEvent + ", titleEvent=" + titleEvent + ", imageEvent=" + imageEvent + ", descriptionEvent=" + descriptionEvent + ", Organisation=" + Organisation + ", timeEvent=" + timeEvent + '}';
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitleEvent() {
        return titleEvent;
    }

    public Event() {
    }

    public Event(String titleEvent, String imageEvent, String descriptionEvent, String Organisation) {
        this.titleEvent = titleEvent;
        this.imageEvent = imageEvent;
        this.descriptionEvent = descriptionEvent;
        this.Organisation = Organisation;
    }

    public Event(int idEvent, String titleEvent, String imageEvent, String descriptionEvent, String Organisation,Date timeEvent) {
        this.idEvent = idEvent;
        this.titleEvent = titleEvent;
        this.imageEvent = imageEvent;
        this.descriptionEvent = descriptionEvent;
        this.Organisation = Organisation;
        this.timeEvent = timeEvent;
    }

    public void setTitleEvent(String titleEvent) {
        this.titleEvent = titleEvent;
    }

    public String getImageEvent() {
        return imageEvent;
    }

    public void setImageEvent(String imageEvent) {
        this.imageEvent = imageEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }

    public String getOrganisation() {
        return Organisation;
    }

    public void setOrganisation(String Organisation) {
        this.Organisation = Organisation;
    }

    public Date getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(Date timeEvent) {
        this.timeEvent = timeEvent;
    }
    
}
