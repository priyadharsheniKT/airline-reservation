
package airline;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

class test1 implements ActionListener {
    JFrame frame1 = null;
    JPanel panel;
    JButton b1, b2 = null;
    JLabel l1, l2, l3, l4;

    test1(String u, String p) {
        frame1 = new JFrame("Home page");

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(173, 216, 235));
        panel.setLayout(null);

        l1 = new JLabel("Welcome to PT Airlines");
        l1.setFont(new Font("Arial", Font.BOLD, 45));
        l1.setBounds(530, 60, 1000, 70);

        l2 = new JLabel("We are leading airlines in India since 2003.");
        l2.setFont(new Font("Arial", Font.BOLD, 20));
        l2.setBounds(580, 180, 1000, 50);

        panel.add(l2);

        l3 = new JLabel("We happily welcome you to our family. Please enjoy your journey with us!!");
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l3.setBounds(450, 220, 1000, 50);

        panel.add(l3);

        try {
            URL imageUrl = new URL("https://images.cnbctv18.com/wp-content/uploads/2023/11/air-india.jpg"); 
            ImageIcon i = new ImageIcon(imageUrl);
            l4 = new JLabel(i);
            l4.setBounds(240, 330, 1105, 370);
            panel.add(l4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b1 = new JButton("Login");
        b2 = new JButton("Register");

        panel.add(l1);
        panel.add(b1);
        panel.add(b2);

        b1.setBounds(460, 740, 150, 38);
        b2.setBounds(1050, 740, 150, 38);

        b1.addActionListener(this);
        b2.addActionListener(this);

        frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame1.getContentPane().add(panel);
        frame1.setSize(400, 750);
        frame1.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Login")) {
            test2 t2 = new test2();
        } else if (ae.getActionCommand().equals("Register")) {
            test3 t3 = new test3();
        }
    }

}

public class home {
    public static void main(String ar[]) {
        test2 t = new test2();
        t.f.setVisible(false);
        test1 t1 = new test1(test2.username, test2.password);
    }
}
