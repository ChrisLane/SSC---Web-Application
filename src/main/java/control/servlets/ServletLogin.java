package control.servlets;

import model.email.Email;

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

/**
 * The type Servlet login.
 */
@WebServlet(name = "ServletLogin", value = "/login")
public class ServletLogin extends HttpServlet {
    /**
     * Log the user in to their email account.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user inputted username and password
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(true);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Email email = new Email();
        RequestDispatcher dispatcher = null;
        try {
            // If we can log in then set the username and password in the session
            if (email.successfulLogin(username, password)) {
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                dispatcher = request.getRequestDispatcher("EmailForm.html");
                dispatcher.forward(request, response);
            }
        } catch (MessagingException e) {
            // Report that we could not log in
            out.println("<b>Incorrect login</b>");
            dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.include(request, response);
        }
    }
}
