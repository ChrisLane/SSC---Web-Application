package control.servlets;

import model.email.Email;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Servlet send email.
 */
@WebServlet(name = "ServletSendEmail", value = "/sendEmail")
public class ServletSendEmail extends HttpServlet {
    /**
     * Send an email.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the username and password from the session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        // Get the message details from the form
        String recipient = request.getParameter("InputRecipient");
        String subject = request.getParameter("InputSubject");
        String messageBody = request.getParameter("InputMessage");

        RequestDispatcher dispatcher;

        Email email = new Email();

        // Try to send the email
        if (email.successfulEmail(username, password, recipient, subject, messageBody)) {
            // Email sent successfully, inform the user
            request.setAttribute("Message", "Your email was sent!");
            dispatcher = request.getRequestDispatcher("Result.jsp");
            dispatcher.forward(request, response);
        } else {
            // Email failed to send, inform the user
            request.setAttribute("Message", "Your email failed to send :(");
            dispatcher = request.getRequestDispatcher("Result.jsp");
            dispatcher.include(request, response);
        }
    }
}
