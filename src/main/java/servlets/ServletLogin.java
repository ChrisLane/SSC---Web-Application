package servlets;

import email.Email;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletLogin", value = "/login")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(true);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Email email = new Email();
        RequestDispatcher dispatcher = null;
        try {
            if (email.successfulLogin(username, password)) {
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                dispatcher = request.getRequestDispatcher("EmailForm.html");
                dispatcher.forward(request, response);
            }
        } catch (MessagingException e) {
            out.println("<b>Incorrect login</b>");
            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);
        }
    }
}
