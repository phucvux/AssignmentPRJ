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
import model.Student;
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

    public Lesson get(int sid) {
        return null;
    }

    public ArrayList<Lesson> getSid(int sid) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select course.cname,\n"
                    +"             student.sid,\n"
                    + "           course.cid,\n"
                    + "		checkstatus.date,\n"
                    + "          timeslot.tid,\n"
                    + "		timeslot.slot_name,\n"
                    + "		checkstatus.status\n"
                    + "from lesson\n"
                    + "join course on course.cid = lesson.cid\n"
                    + "join checkstatus on lesson.lid = checkstatus.lid\n"
                    + "join timeslot on timeslot.tid = lesson.tid"
                    +"join student on checkstatus.sid = student.sid"
                    + "where student.sid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();

                Course c = new Course();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                l.setCourse(c);

                Timeslot t = new Timeslot();
                t.setSlot_name(rs.getString("slot_name"));
                t.setTid(rs.getInt("tid"));
                l.setTimeslot(t);

                Checkstatus s = new Checkstatus();
                s.setDate(rs.getDate("date"));
                s.setStatus(rs.getBoolean("status"));
                
                Student stu = new Student();
                stu.setSid(rs.getInt("sid"));
                s.setStudent(stu);
                
                l.getStatus().add(s);
                
                lessons.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatusDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatusDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatusDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lessons;
    }

    @Override
    public ArrayList<Lesson> all() {

       ArrayList<Lesson> lessons = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select course.cname,\n"
                  
                    + "           course.cid,\n"
                    + "		checkstatus.date,\n"
                    + "          timeslot.tid,\n"
                    + "		timeslot.slot_name,\n"
                    + "		checkstatus.status\n"
                    + "from lesson\n"
                    + "join course on course.cid = lesson.cid\n"
                    + "join checkstatus on lesson.lid = checkstatus.lid\n"
                    + "join timeslot on timeslot.tid = lesson.tid\n"
                    + "order by checkstatus.date";
                  
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                
                Checkstatus s = new Checkstatus();
                s.setDate(rs.getDate("date"));
                s.setStatus(rs.getBoolean("status"));
                l.getStatus().add(s);

                Course c = new Course();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                l.setCourse(c);

                Timeslot t = new Timeslot();
                t.setSlot_name(rs.getString("slot_name"));
                t.setTid(rs.getInt("tid"));
                l.setTimeslot(t);
       
                lessons.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatusDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatusDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatusDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lessons;

    }
}
