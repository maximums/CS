import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class GUI{

    private static JPasswordField password;

    public GUI(){

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
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

        JButton login = new JButton("Login");
        login.setBounds(100,185,80,25);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PasswordHash passwordHash = new PasswordHash();
                boolean matched;
                try{
                    matched = passwordHash.validatePassword(String.valueOf(password.getPassword()));
                    if (matched)
                        infoWindow("Welcome");
                    else
                        infoWindow("Error");
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    noSuchAlgorithmException.printStackTrace();
                } catch (InvalidKeySpecException invalidKeySpecException) {
                    invalidKeySpecException.printStackTrace();
                }
            }
        });
        panel.add(login);

        frame.setVisible(true);

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
