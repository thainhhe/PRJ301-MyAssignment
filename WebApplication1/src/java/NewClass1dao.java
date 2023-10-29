
import dal.NewClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class NewClass1dao extends DBContext {

    public List<NewClass> getAll() throws SQLException {
        String sql = "select description from TimeSlot ";

        List<NewClass> list = new ArrayList();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            NewClass n = new NewClass(rs.getString("description"));
            list.add(n);

        }
        return list;
    }
}
