package servlets;

import email.Email;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletSendEmail", value = "/sendEmail")
public class ServletSendEmail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        String password = session.getAttribute("password").toString();

        String recipient = request.getParameter("InputRecipient");
        String subject = request.getParameter("InputSubject");
        String messageBody = request.getParameter("InputMessage");

        RequestDispatcher dispatcher = null;

        Email email = new Email();

        if (email.successfulEmail(username, password, recipient, subject, messageBody)) {
            request.setAttribute("Message", "Your email was sent!");
            dispatcher = request.getRequestDispatcher("Result.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("Message", "Your email failed to send :(");
            dispatcher = request.getRequestDispatcher("Result.jsp");
            dispatcher.include(request, response);
        }
    }
}
