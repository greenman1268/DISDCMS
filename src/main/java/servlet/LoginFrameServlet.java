package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 21.04.2016
 */
@WebServlet("/login")
public class LoginFrameServlet extends HttpServlet{
    private String username;
    private String password;
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
         username = req.getParameter("username");
         password = req.getParameter("password");
        if(username.equals("admin") && password.equals("126874539")){
            getServletContext().getRequestDispatcher("/PersonFrame.jsp").forward(req, resp);
        }
    }
    // Переопределим стандартные методы
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
