/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
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
public class LessonDBContext extends DBContext<Lesson> {

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

    @Override
    public ArrayList<Lesson> all() {
        
        return null;
        
    }

    public ArrayList<Lesson> getTimeTable(int sid, Date from, Date to) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select timeslot.slot_name,\n"
                    + "                	course.cname,\n"
                    + "                 room.rname,\n"
                    + "			checkstatus.date,\n"
                    + "			checkstatus.status,\n"
                    + "                  timeslot.tid	,	\n"
                    +"                  lesson.lid"
                    + "   from lesson\n"
                    + "                  join room on lesson.rid = room.rid\n"
                    + "                  join timeslot on lesson.tid = timeslot.tid\n"
                    + "                  join course on lesson.cid = course.cid\n"
                    + "			 join checkstatus on lesson.lid = checkstatus.lid\n"
                    + "                  join student on checkstatus.sid = student.sid  "
                    + "	where student.sid = ? and checkstatus.date >= ? and checkstatus.date <= ?";
                   
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();

            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLid(rs.getInt("lid"));
                Timeslot t = new Timeslot();
                t.setTid(rs.getInt("tid"));
                t.setSlot_name(rs.getString("slot_name"));
                l.setTimeslot(t);

                Course c = new Course();
                c.setCname(rs.getString("cname"));
                l.setCourse(c);

                Room r = new Room();
                r.setRname(rs.getString("rname"));
                l.setRoom(r);
                
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
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lessons;
    }
    
    public static void main(String[] args) {
          ArrayList<Lesson> lessons = new LessonDBContext().getTimeTable(1, Date.valueOf("2023-03-18"), Date.valueOf("2023-03-24"));
          for (Lesson lesson : lessons) {
              System.out.println(lesson.toString());
        }
    }
}
