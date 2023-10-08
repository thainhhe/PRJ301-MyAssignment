/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Rect;
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
public class RectDBContext extends DBContext<Rect> {

    @Override
    public ArrayList<Rect> list() {
        ArrayList<Rect> rects = new ArrayList<>();
        try {
            String sql = "SELECT id,x,y,w,h,r,g,b FROM Rect";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Rect rect = new Rect();
                rect.setId(rs.getInt("id"));
                rect.setX(rs.getInt("x"));
                rect.setY(rs.getInt("y"));
                rect.setW(rs.getInt("w"));
                rect.setH(rs.getInt("h"));
                rect.setR(rs.getInt("r"));
                rect.setG(rs.getInt("g"));
                rect.setB(rs.getInt("b"));
                rects.add(rect);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RectDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return rects;
    }

    @Override
    public void insert(Rect entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Rect entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Rect entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Rect get(Rect entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
