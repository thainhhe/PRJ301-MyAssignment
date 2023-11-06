/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Attendance;
import entity.Session;
import entity.Student;
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
public class AttendanceDBContext extends DBContext<Attendance> {
//sessid = session_id
    public ArrayList<Attendance> getAttendances(int session_id) {
        ArrayList<Attendance> atts = new ArrayList<>();
        try {
            String sql = "SELECT s.student_id,s.student_name,ISNULL(a.status,0) as [status], \n"
                    + "                     ISNULL(a.description,'') as [description],\n"
                    + "                     ISNULL(a.att_datetime, GETDATE()) as [att_datetime],\n"
                    + "                     a.session_id\n"
                    + "                     FROM [Session] ses INNER JOIN [Group] g ON ses.group_id = g.group_id\n"
                    + "                   	INNER JOIN [Group_Student] gs ON g.group_id = gs.group_id\n"
                    + "                    	INNER JOIN [Student] s ON s.student_id = gs.student_id\n"
                    + "                   	LEFT JOIN Attendance a ON s.student_id = a.student_id\n"
                    + "                   	WHERE ses.session_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, session_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                Student student = new Student();
                Session session = new Session();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("student_name"));
                session.setId(session_id);
                att.setStudent(student);
                att.setSession(session);
                att.setStatus(rs.getBoolean("status"));
                att.setDescription(rs.getString("description"));
                att.setDatetime(rs.getDate("att_datetime"));

                atts.add(att);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atts;
    }

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
