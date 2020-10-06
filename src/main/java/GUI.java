import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public class GUI implements ActionListener {

    private static JTextField email;
    private static JPanel panel;
    private static JFrame frame;
    private static JButton login;
    private static JPasswordField password;
    private static JLabel emailLabel;
    private static final String vcode = generateCode();
    public GUI(){

        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login");
        frame.add(panel);

        panel.setLayout(null);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(110,120,80,25);
        panel.add(passwordLabel);

        password = new JPasswordField(24);
        password.setBounds(60,150,165,25);
        password.setBackground(Color.gray);
        panel.add(password);

        login = new JButton("Login");
        login.setBounds(100,185,80,25);
        login.addActionListener(this);
        panel.add(login);

        frame.setVisible(true);

    }
    private static String generateCode() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        PasswordHash passwordHash = new PasswordHash();
        boolean matched;
        SendEmail sendEmail = new SendEmail();
        try {
            matched = passwordHash.validatePassword(String.valueOf(password.getPassword()));
            if (matched) {
                sendEmail.sendEmailCode(vcode);
                panel.removeAll();
                emailLabel = new JLabel("Enter code");
                emailLabel.setBounds(110,60,80,25);
                panel.add(emailLabel);
                email = new JTextField(24);
                email.setBackground(Color.gray);
                email.setBounds(60, 90, 165, 25);
                panel.add(email);
                login = new JButton("Login");
                login.setBounds(100,185,80,25);
                login.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (email.getText().equals(vcode))
                            infoWindow("Welcome");
                        else
                            infoWindow("Error");
                    }
                });
                panel.add(login);
                panel.revalidate();
                panel.repaint();
            }
            else
                infoWindow("Error");
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
        } catch (InvalidKeySpecException invalidKeySpecException) {
            invalidKeySpecException.printStackTrace();
        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
        }
    }
    private static void infoWindow(String str){
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(250, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Info");
        frame.add(panel);

        panel.setLayout(null);

        JLabel war = new JLabel(str);
        war.setBounds(65,80,80,20);
        war.setBackground(Color.darkGray);
        panel.add(war);

        frame.setVisible(true);
    }
}
