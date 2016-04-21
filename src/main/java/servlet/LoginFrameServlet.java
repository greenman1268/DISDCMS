package servlet;

import logic.Department;
import logic.ManagementSystem;
import servlet.forms.MainFrameForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created on 21.04.2016
 */
@WebServlet("/login")
public class LoginFrameServlet extends HttpServlet{

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if(req.getParameter("Cancel") != null){
            MainFrameForm form = new MainFrameForm();
            String d = req.getParameter("departmentId");
            int departmentId = -1;
            if (d != null) {
                departmentId = Integer.parseInt(d);
            }
            Department dep = null;
            try {
                Collection departments = ManagementSystem.getInstance().getDepartments();
                dep = new Department();
                dep.setDepartmentId(departmentId);
                if (departmentId == -1) {
                    Iterator i = departments.iterator();
                    dep = (Department) i.next();
                }
                Collection persons = ManagementSystem.getInstance().getPersonsFromDepartment(dep);
                form.setDepartmentId(dep.getDepartmentId());
                form.setNameDepartment(dep.getNameDepartment());
                form.setChief(dep.getChief());
                form.setAmount_people(dep.getAmount_people());
                form.setDepartments(departments);
                form.setPersons(persons);

            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            } catch (Exception e){
                e.printStackTrace();
            }

            req.setAttribute("form", form);
            getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean error = false;
        if(username != null && password != null)
        if(username.equals("admin") && password.equals("126874539")){
            getServletContext().getRequestDispatcher("/Login/AdminFrame.jsp").forward(req, resp);
            return;
        }
        else { error = true; req.setAttribute("error", error);}

        getServletContext().getRequestDispatcher("/Login/LoginFrame.jsp").forward(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
