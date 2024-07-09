
package airline;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class test2 implements ActionListener
{
JFrame f=new JFrame();
JButton b1=null,b2=null,b3=null;
JTextField tf1=null;
JPasswordField tf2=null;
JLabel l1,l2,l3,l4;
public static String username;
public static String password;


test2()
{
     
f=new JFrame("Login");
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setBackground(new Color(176, 224, 230));

b1=new JButton("Login");
b1.setBounds(780,380,80,30);
b2=new JButton("Here");
b2.setBounds(820,460,80,25);
b3=new JButton("Back");
b3.setBounds(1000,600,80,30);

l1=new JLabel("Username (Email ID):");
l1.setBounds(500,250 , 300, 20);
l2=new JLabel("Password:");
l2.setBounds(500, 300, 120, 20);
l3=new JLabel("If new user register");
l3.setBounds(650, 460, 250,25);
l4=new JLabel("Welcome dear user, have a good day!");
l4.setBounds(500, 100, 1000, 35);

l1.setFont(new Font("Arial",Font.BOLD,20));
l2.setFont(new Font("Arial",Font.BOLD,20));
l3.setFont(new Font("Arial",Font.BOLD,17));
l4.setFont(new Font("Arial",Font.BOLD,29));

tf1=new JTextField(25);
tf1.setBounds(750,250,150,25);
tf2=new JPasswordField(25);
tf2.setBounds(750,300,150,25);


f.add(b1);
f.add(b2);
f.add(b3);
f.add(l1);
f.add(l2);
f.add(l3);
f.add(l4);
f.add(tf1);
f.add(tf2);

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);

f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
f.setLayout(null);
f.setSize(400,300);
f.setVisible(true);
}

@Override
public void actionPerformed(ActionEvent ae)
{
if(ae.getActionCommand().equals("Login"))
{
username=tf1.getText();
password=new String(tf2.getPassword());
String url = "jdbc:mysql://localhost:3306/intern";
String user = "root";
String pass = "pt2029";
String sql = "SELECT EXISTS(SELECT 1 FROM user where Email_ID=? AND Password=?) AS record;";

try 
{
    Class.forName("com.mysql.cj.jdbc.Driver");
    try (Connection con = DriverManager.getConnection(url, user, pass)) 
    {
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1,username);
        statement.setString(2, password);
        ResultSet res = statement.executeQuery();
                if (res.next())
                {
                 int r = res.getInt("record");
                 boolean found = (r == 1);
                 if(found)
                 {
                     test4 t4=new test4(username,password);
                     //test8 t8=new test8(username,password);
                     //t8.frame2.setVisible(false);
                 }
                 else
                 {
                      JOptionPane.showMessageDialog(f, "Error: Invalid login or user not found", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                }
    }
}
catch (ClassNotFoundException ce) 
        {
        } 
catch (SQLException ce) 
        {
            JOptionPane.showMessageDialog(f, "Error: Invalid login or user not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
          

}
else if(ae.getActionCommand().equals("Here"))
{
 test3 t3=new test3();
}
else if(ae.getActionCommand().equals("Back"))
{
test1 t1=new test1(username,password);
}

}

}

public class login
{
public static void main(String ar[])
{
test2 t2=new test2();
}
}