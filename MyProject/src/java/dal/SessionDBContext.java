/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Attendance;
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
            String sql = "SELECT ses.session_id, ses.[date], r.room_id, t.timeslot_id, \n"
                    + "g.group_id, g.group_name, sub.subject_id, sub.subject_name, \n"
                    + "i.instructor_id, i.instructor_name, sem.semester_id, sem.semester_name\n"
                    + "FROM     Session ses INNER JOIN\n"
                    + "                  Instructor i ON ses.instructor_id = i.instructor_id INNER JOIN\n"
                    + "                  [Group] g ON ses.group_id = g.group_id AND i.instructor_id = g.instructor_id INNER JOIN\n"
                    + "                  TimeSlot t ON ses.timeslot_id = t.timeslot_id INNER JOIN\n"
                    + "                  Room r ON ses.room_id = r.room_id INNER JOIN\n"
                    + "                  Subject sub ON g.subject_id = sub.subject_id INNER JOIN\n"
                    + "                  Semester sem ON g.semester_id = sem.semester_id\n"
                    + "where i.instructor_id = ? and ses.[date] >= ? and ses.[date] <= ?";
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

    public void addAttendances(Session ses) {
        try {
            connection.setAutoCommit(false);
            String sql_update_isAtt = "UPDATE [Session] SET isAtt = 1 WHERE session_id =?";
            PreparedStatement stm_update_isAtt = connection.prepareStatement(sql_update_isAtt);
            stm_update_isAtt.setInt(1, ses.getId());
            stm_update_isAtt.executeUpdate();

            String sql_remove_atts = "DELETE Attendance WHERE session_id =?";
            PreparedStatement stm_remove_atts = connection.prepareStatement(sql_remove_atts);
            stm_remove_atts.setInt(1, ses.getId());
            stm_remove_atts.executeUpdate();

            for (Attendance att : ses.getAtts()) {
                String sql_insert_att = "INSERT INTO [Attendance]\n"
                        + "           ([session_id]\n"
                        + "           ,[student_id]\n"
                        + "           ,[status]\n"
                        + "           ,[description]\n"
                        + "           ,[att_datetime])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,GETDATE())";
                PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                stm_insert_att.setInt(1, ses.getId());
                stm_insert_att.setInt(2, att.getStudent().getId());
                stm_insert_att.setBoolean(3, att.isStatus());
                stm_insert_att.setString(4, att.getDescription());
                stm_insert_att.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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
        try {
            String sql = "SELECT Session.session_id, Session.date, Room.room_id, TimeSlot.timeslot_id, TimeSlot.description, [Group].group_id, [Group].group_name, Subject.subject_id, Subject.subject_name, Instructor.instructor_id, Instructor.instructor_name, \n"
                    + "                  Semester.semester_name, Session.isAtt\n"
                    + "FROM     Session INNER JOIN\n"
                    + "                  Instructor ON Session.instructor_id = Instructor.instructor_id INNER JOIN\n"
                    + "                  [Group] ON Session.group_id = [Group].group_id AND Instructor.instructor_id = [Group].instructor_id INNER JOIN\n"
                    + "                  TimeSlot ON Session.timeslot_id = TimeSlot.timeslot_id INNER JOIN\n"
                    + "                  Room ON Session.room_id = Room.room_id INNER JOIN\n"
                    + "                  Subject ON [Group].subject_id = Subject.subject_id INNER JOIN\n"
                    + "                  Semester ON Session.semester_id = Semester.semester_id\n"
                    + "				  where session_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                session.setId(rs.getInt("session_id"));
                session.setDate(rs.getDate("date"));
                session.setIsAtt(rs.getBoolean("isAtt"));
                Room room = new Room();
                room.setRid(rs.getString("room_id"));
                session.setRoom(room);
                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("timeslot_id"));
                t.setDescription(rs.getString("description"));
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
                semester.setSemester_name(rs.getString("semester_name"));
                session.setSemester(semester);
                return session;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
