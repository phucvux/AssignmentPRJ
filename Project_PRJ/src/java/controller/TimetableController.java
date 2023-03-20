/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.authentic.BaseRequiredAuthenticatedController;
import dal.DBContext;
import dal.LessonDBContext;
import dal.TimeSlotDBContext;
import dal.UserDBContext;
import date.DateTimeHelper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lesson;
import model.Timeslot;
import model.User;

/**
 *
 * @author CucLe
 */
public class TimetableController extends BaseRequiredAuthenticatedController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        User u = (User) request.getSession().getAttribute("user");
        int sid = u.getUid();
//        int sid = Integer.parseInt(request.getParameter("uid"));

        String date = request.getParameter("date");
        if(date==null){
            date = DateTimeHelper.getCurrentDate().toString();
        }
        ArrayList<Date> weekDays = DateTimeHelper.getWeekFromDate(date);

        request.setAttribute("weekDays", weekDays);
        Date from = weekDays.get(0);
        Date to = weekDays.get(weekDays.size() - 1);
     
//        String from2 = request.getParameter("from");
//        String to2 = request.getParameter("to");
        TimeSlotDBContext timeDB = new TimeSlotDBContext();
        ArrayList<Timeslot> slots = timeDB.all();
        request.setAttribute("slots", slots);

        LessonDBContext ldbc = new LessonDBContext();
        ArrayList<Lesson> lessons = ldbc.getTimeTable(sid, from, to);
//        ArrayList<Lesson> lessons = ldbc.getTimeTable(sid, Date.valueOf(from2), Date.valueOf(to2));
//        System.out.println("hello");
//        for (Lesson lesson : lessons) {
//            System.out.println(lesson);
//        }
        
        request.setAttribute("sid", sid);
        request.setAttribute("lessons", lessons);
        request.getRequestDispatcher("/view/function/timetable.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
