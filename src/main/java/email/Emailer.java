package email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emailer {
    private Session session;
    private String username;


    public Emailer(Session session, String username) {
        this.session = session;
        this.username = username;
    }

    public void sendEmail(String recipient, String subject, String message) throws MessagingException {

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(username));
        InternetAddress[] toAddresses = {new InternetAddress(recipient)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setText(message);

        // sends the e-mail
        Transport.send(msg);
    }
}
