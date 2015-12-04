package email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {

    public boolean successfulLogin(String username, String password) throws MessagingException {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        props.setProperty("mail.user", username);
        props.setProperty("mail.password", password);

        Session session = Session.getDefaultInstance(props);

        try {
            Store store = session.getStore("imaps");
            store.connect("imap.googlemail.com", username, password);
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean successfulEmail(String username, String password, String recipient, String subject, String messageBody) {

        String smtphost = "smtp.gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtphost);
        props.put("mail.smtp.port", "587");

        props.setProperty("mail.user", username);
        props.setProperty("mail.password", password);

        Session session = Session.getDefaultInstance(props);

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
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
