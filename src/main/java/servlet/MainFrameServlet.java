package servlet;

/**
 * Created on 31.03.2016
 */

import logic.Department;
import logic.ManagementSystem;
import logic.Person;
import servlet.forms.MainFrameForm;
import servlet.forms.PersonForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

@WebServlet("/main")
public class MainFrameServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Установка кодировки для принятия параметров
        req.setCharacterEncoding("UTF-8");
        int answer = 0;
        try {
            answer = checkAction(req);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }
        if (answer == 1) {
            // Тут надо сделать вызов другой формы, которая перенаправит сервлет
            // на другую JSP для ввода данных о новом студенте
            try {
                Person p = new Person();
                p.setDateOfBirth(new java.sql.Date(new java.util.Date().getTime()));
                Collection departments = ManagementSystem.getInstance().getDepartments();
                PersonForm pForm = new PersonForm();
                pForm.initFromPerson(p);
                pForm.setDepartments(departments);
                req.setAttribute("person", pForm);
                getServletContext().getRequestDispatcher("/PersonFrame.jsp").forward(req, resp);
                return;
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        if (answer == 2) {
            // Тут надо сделать вызов другой формы, которая перенаправит сервлет
            // на другую JSP для ввода данных о студенте
            try {
                if (req.getParameter("personId") != null) {
                    int pId = Integer.parseInt(req.getParameter("personId"));
                    Person p = ManagementSystem.getInstance().getPersonById(pId);
                    Collection departments = ManagementSystem.getInstance().getDepartments();
                    PersonForm pForm = new PersonForm();
                    pForm.initFromPerson(p);
                    pForm.setDepartments(departments);
                    req.setAttribute("person", pForm);
                    getServletContext().getRequestDispatcher("/PersonFrame.jsp").forward(req, resp);
                    return;
                }
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        String d = req.getParameter("departmentId");
        String nd = req.getParameter("nameDepartment");
        String chd = req.getParameter("chief");
        String apdp = req.getParameter("amount_people");
        String y = req.getParameter("year");

        if (answer == 3) {
            // Здесь мы перемещаем стедунтов в другую группу
            String newDep = req.getParameter("newDepartmentsId");
            String newY = req.getParameter("newYear");
            try {
                Department dep = new Department();
                dep.setDepartmentId(Integer.parseInt(d));
                Department dep2 = new Department();
                dep2.setDepartmentId(Integer.parseInt(newDep));
                ManagementSystem.getInstance().movePersonsToDepartment(dep,dep2,y);
                // Теперь мы будем показывать группу, куда переместили
                d = newDep;
                y = newY;
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        int departmentId = -1;
        if (d != null) {
            departmentId = Integer.parseInt(d);
        }
        String year = "";
        if (y != null) {
            year = y;
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

    // Здесь мы проверям какое действие нам надо сделать – и возвращаем ответ
    private int checkAction(HttpServletRequest req) throws SQLException {
        if (req.getParameter("Add") != null) {
            return 1;
        }
        if (req.getParameter("Edit") != null) {
            return 2;
        }
        if (req.getParameter("MoveGroup") != null) {
            return 3;
        }
        if (req.getParameter("Delete") != null) {
            if (req.getParameter("personId") != null) {
                Person p = new Person();
                p.setPersonId(Integer.parseInt(req.getParameter("personId")));
                try {
                    ManagementSystem.getInstance().deletePerson(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return 0;
        }
        return 0;
    }

    // Переопределим стандартные методы
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
