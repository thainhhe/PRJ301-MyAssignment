/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Group;
import entity.Room;
import entity.Semester;
import entity.Session;
import entity.Subject;
import entity.TimeSlot;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> getSessions(int instructor_id, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "SELECT Session.session_id, Session.date, Instructor.instructor_id, Instructor.instructor_name, TimeSlot.timeslot_id, Room.room_id, [Group].group_id, [Group].group_name, Subject.subject_id, Subject.subject_name, Semester.semester_id, \n"
                    + "                  Semester.semester_name\n"
                    + "FROM     Session INNER JOIN\n"
                    + "                  Instructor ON Session.instructor_id = Instructor.instructor_id INNER JOIN\n"
                    + "                  [Group] ON Session.group_id = [Group].group_id AND Instructor.instructor_id = [Group].instructor_id INNER JOIN\n"
                    + "                  TimeSlot ON Session.timeslot_id = TimeSlot.timeslot_id INNER JOIN\n"
                    + "                  Room ON Session.room_id = Room.room_id INNER JOIN\n"
                    + "                  Subject ON [Group].subject_id = Subject.subject_id INNER JOIN\n"
                    + "                  Semester ON [Group].semester_id = Semester.semester_id\n" +
            "where Instructor.instructor_id = ? and Session.date >= ? and Session.date <=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, instructor_id);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                session.setId(rs.getInt("session_id"));
                session.setDate(rs.getDate("date"));
                Room room = new Room();
                room.setRid(rs.getString("room_id"));
                session.setRoom(room);
                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("timeslot_id"));
                session.setTime(t);
                Group g = new Group();
                g.setId(rs.getInt("group_id"));
                g.setName(rs.getString("group_name"));
                session.setGroup(g);
                Subject subject = new Subject();
                subject.setId(rs.getInt("subject_id"));
                subject.setName(rs.getString("subject_name"));
                session.setSubject(subject);
                Semester semester = new Semester();
                semester.setSemester_id(rs.getInt("semester_id"));
                semester.setSemester_name(rs.getString("semester_name"));
                g.setSemester(semester);
                sessions.add(session);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return sessions;
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
