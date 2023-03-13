/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Checkstatus;
import model.Course;
import model.Lesson;
import model.Room;
import model.Timeslot;

/**
 *
 * @author CucLe
 */
public class StatusDBContext extends DBContext<Lesson> {

    @Override
    public void insert(Lesson model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Lesson model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Lesson model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lesson get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Lesson> all(Lesson model) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select course.cname,\n"
                    + "		checkstatus.date,\n"
                    + "		timeslot.slot_name,\n"
                    + "		checkstatus.[status]\n"
                    + "		from lesson\n"
                    + "		join course on lesson.cid =course.cid\n"
                    + "		join checkstatus on lesson.lid = checkstatus.lid\n"
                    + "		join timeslot on lesson.tid = timeslot.tid\n"
                    + "		where course.cid = '?'\n"
                    + "		order by checkstatus.date";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getCourse().getCid());
            rs = stm.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();

                Timeslot t = new Timeslot();
                t.setSlot_name(rs.getString("slot_name"));
                l.setTimeslot(t);

                Course c = new Course();
                c.setCname(rs.getString("cname"));
                l.setCourse(c);
                
                Checkstatus s = new Checkstatus();
                s.setDate(rs.getDate("date"));
                s.setStatus(rs.getBoolean("status"));
                l.getStatus().add(s);
                
                lessons.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lessons;
    }

    @Override
    public ArrayList<Lesson> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
