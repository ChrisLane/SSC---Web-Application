package email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The type Email.
 */
public class Email {

    /**
     * Successful login boolean.
     *
     * @param username the username
     * @param password the password
     * @return If the login was successful or not
     * @throws MessagingException the messaging exception
     */
    public boolean successfulLogin(String username, String password) throws MessagingException {
        // Set properties for login connection
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.user", username);
        props.setProperty("mail.password", password);

        Session session = Session.getDefaultInstance(props);

        try {
            Store store = session.getStore("imaps");
            store.connect("imap.googlemail.com", username, password);
            store.close();

            // Successfully logged in
            return true;
        } catch (NoSuchProviderException e) {
            // Login failed
            return false;
        }
    }

    /**
     * Successful email boolean.
     *
     * @param username    the username
     * @param password    the password
     * @param recipient   the recipient
     * @param subject     the subject
     * @param messageBody the message body
     * @return If the email was sent successfully or not
     */
    public boolean successfulEmail(String username, String password, String recipient, String subject, String messageBody) {
        // Set properties for email sending
        String smtphost = "smtp.gmail.com";
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.host", smtphost);
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.user", username);
        props.setProperty("mail.password", password);

        Session session = Session.getDefaultInstance(props);

        // Form and send the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(messageBody);

            message.saveChanges();

            Transport transport = session.getTransport("smtp");
            transport.connect(smtphost, username, password);
            transport.sendMessage(message, message.getAllRecipients());

            transport.close();
            // Message sent successfully
            return true;
        } catch (MessagingException e) {
            // Message failed to send
            return false;
        }
    }
}
