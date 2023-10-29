/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;



/**
 *
 * @author Admin
 */
public class Session extends BaseEntity{
    private int id;
    private Date date;
    private TimeSlot time;
    private Group group;
    private int index;
    private Room room;
    private Instructor instructor;
    private Subject subject;

    public Session() {
    }

    public Session(int id, Date date, TimeSlot time, Group group, int index, Room room, Instructor instructor, Subject subject) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.group = group;
        this.index = index;
        this.room = room;
        this.instructor = instructor;
        this.subject = subject;
    }
    
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TimeSlot getTime() {
        return time;
    }

    public void setTime(TimeSlot time) {
        this.time = time;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
}
