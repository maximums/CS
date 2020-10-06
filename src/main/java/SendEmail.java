import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public void sendEmailCode(String code) throws MessagingException {

        final String to = "lovememoremyfirstcrush@gmail.com";
        final String username = "lovememoremyfirstcrush@gmail.com";
        final String password = "qwerty45215asdfgh";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lovememoremyfirstcrush@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject("Verification code");
            message.setText(code);
            Transport.send(message);
    }

}