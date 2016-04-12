package servlet;

/**
 * Created on 31.03.2016
 */

import logic.Department;
import logic.ManagementSystem;
import logic.Person;
import servlet.forms.MainFrameForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

@WebServlet("/edit")
public class PersonFrameServlet extends HttpServlet
{
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String pId = req.getParameter("personId");
        if (pId != null && req.getParameter("OK") != null) {
            try {
                if (Integer.parseInt(pId) > 0) {
                    updatePerson(req);
                }
                else {
                    insertPerson(req);
                }
            } catch (SQLException sql_e) {
                sql_e.printStackTrace();
                throw new IOException(sql_e.getMessage());
            } catch (ParseException p_e) {
                throw new IOException(p_e.getMessage());
            }
        }

        String d = req.getParameter("departmentId");
        String nd = req.getParameter("nameDepartment");
        String chd = req.getParameter("chief");
        String apdp = req.getParameter("amount_people");
        String y = req.getParameter("year");

        int departmentId = -1;
        if (d != null) {
            departmentId = Integer.parseInt(d);
        }

        String nameDepartment = "";
        if (nd != null) {
            nameDepartment = nd;
        }

        String chief = "";
        if (chd != null) {
            chief = chd;
        }

        int amount_people = -1;
        if (apdp != null) {
            amount_people = Integer.parseInt(apdp);
        }

        int year = -1;
        if (y != null) {
            year = Integer.parseInt(y);
        }

        MainFrameForm form = new MainFrameForm();
        try {
            Collection departments = ManagementSystem.getInstance().getDepartments();
            Department dep = new Department();
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
    }


    private void updatePerson(HttpServletRequest req) throws SQLException, ParseException {
        Person p = preparePerson(req);
        try {
            ManagementSystem.getInstance().updatePerson(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertPerson(HttpServletRequest req) throws SQLException, ParseException {
        Person p = preparePerson(req);
        try {
            ManagementSystem.getInstance().insertPerson(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Person preparePerson(HttpServletRequest req) throws ParseException {
        Person p = new Person();
        p.setPersonId(Integer.parseInt(req.getParameter("personId")));
        p.setFirstName(req.getParameter("firstName").trim());
        p.setSurName(req.getParameter("surName").trim());
        p.setPatronymic(req.getParameter("patronymic").trim());
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = format.parse(req.getParameter("birthDay"));
        p.setDateOfBirth(new java.sql.Date(date.getTime()));
        if (req.getParameter("sex").equals("0")) {
            p.setSex('×');
        } else {
            p.setSex('Æ');
        }
       // p.setDepartmentId(Integer.parseInt(req.getParameter("departmentId").trim()));
       // p.setPosition(req.getParameter("position").trim());
       // p.setRank(req.getParameter("rank").trim());
        return p;
    }


    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }
}
