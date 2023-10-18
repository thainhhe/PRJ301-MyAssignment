/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Department;
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
public class StudentDBContext extends DBContext<Student> {

    @Override
    public ArrayList<Student> list() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT [sid]\n"
                    + "      ,[sname]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,d.[did]\n"
                    + "	  ,d.dname\n"
                    + "  FROM [Student] s INNER JOIN Department d \n"
                    + "  ON s.did = d.did";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));

                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                s.setDept(d);
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    //1 ,2 , 0
    public ArrayList<Student> searchByDeptID(int did) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT [sid]\n"
                    + "      ,[sname]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,d.[did]\n"
                    + "	  ,d.dname\n"
                    + "  FROM [Student] s INNER JOIN Department d \n"
                    + "  ON s.did = d.did ";
            
            if(did > 0)
            {
                sql+= " WHERE d.did = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            
            if(did > 0)
            {
                stm.setInt(1, did);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));

                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                s.setDept(d);
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    
    

    @Override
    public void insert(Student entity) {
        try {
            connection.setAutoCommit(false);

            String sql_insert = "INSERT INTO [Student]\n"
                    + "           ([sname]\n"
                    + "           ,[gender]\n"
                    + "           ,[dob]\n"
                    + "           ,[did])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql_insert);
            stm.setString(1, entity.getName());
            stm.setBoolean(2, entity.isGender());
            stm.setDate(3, entity.getDob());
            stm.setInt(4, entity.getDept().getId());
            stm.executeUpdate();

            String sql_getid = "SELECT @@IDENTITY as [sid]";
            PreparedStatement stm2 = connection.prepareStatement(sql_getid);
            ResultSet rs = stm2.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getInt("sid"));
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }

            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(Student entity) {
        try {
            String sql_insert = "UPDATE [Student]\n"
                    + "   SET [sname] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[dob] = ?\n"
                    + "      ,[did] = ?\n"
                    + " WHERE [sid] = ?";
            PreparedStatement stm = connection.prepareStatement(sql_insert);
            stm.setString(1, entity.getName());
            stm.setBoolean(2, entity.isGender());
            stm.setDate(3, entity.getDob());
            stm.setInt(4, entity.getDept().getId());
            stm.setInt(5, entity.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Student entity) {
        try {
            String sql_insert = "DELETE Student\n"
                    + " WHERE [sid] = ?";
            PreparedStatement stm = connection.prepareStatement(sql_insert);
            stm.setInt(1, entity.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Student get(Student s) {
        try {
            String sql = "SELECT [sid]\n"
                    + "      ,[sname]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,d.[did]\n"
                    + "	  ,d.dname\n"
                    + "  FROM [Student] s INNER JOIN Department d \n"
                    + "  ON s.did = d.did WHERE [sid] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, s.getId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));

                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                s.setDept(d);
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
