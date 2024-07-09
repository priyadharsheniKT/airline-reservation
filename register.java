
package airline;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class test3 implements ActionListener
{

       JFrame frame2=null;
       JButton b1,b2=null;
       JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;   
       JTextField tf1,tf2,tf3,tf4,tf5,tf6;
       JPasswordField tf7,tf8;
       JRadioButton r1,r2;
       ButtonGroup bg;
       Font font;
      
              
test3()
 {
             
        frame2 = new JFrame("Register page");
        
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        frame2.setBackground(new Color(176, 224, 230)); 
       
       
        l1 = new JLabel("Name:");
        l2 = new JLabel("Gender:");
        l3 = new JLabel("Phone number:");
        l4 = new JLabel("Email id:");
        l5 = new JLabel("Date of birth (dd/mm/yy):");
        l6 = new JLabel("Address:");
        l7 = new JLabel("Passport ID:");
        l8 = new JLabel("Password:");
        l9 = new JLabel("Confirm password:");
        
        font=new Font("Arial", Font.BOLD, 17);
        
        l1.setFont(font);
        l2.setFont(font);
        l3.setFont(font);
        l4.setFont(font);
        l5.setFont(font);
        l6.setFont(font);
        l7.setFont(font);
        l8.setFont(font);
        l9.setFont(font);
                     
	tf1= new JTextField();
	tf2= new JTextField();
	tf3= new JTextField();
	tf4= new JTextField();
	tf5=new JTextField();
	tf6= new JTextField();
	tf7= new JPasswordField();
	tf8= new JPasswordField();


	frame2.add(tf1);
	tf1.setBounds(700,130,200,25);
	frame2.add(tf2);
	tf2.setBounds(700,250,200,25);
	frame2.add(tf3);
	tf3.setBounds(700,310,200,25);
	frame2.add(tf4);
	tf4.setBounds(700,360,200,25);
	frame2.add(tf5);
	tf5.setBounds(700,430,200,80);
	frame2.add(tf6);
	tf6.setBounds(700,540,200,25);
	frame2.add(tf7);
	tf7.setBounds(700,600,200,25);
	frame2.add(tf8);
	tf8.setBounds(700,670,200,25);
	

        b1 = new JButton("Back");
        b2 = new JButton("Register");
       

        r1=new JRadioButton("Male");
	r2=new JRadioButton("Female");
	bg=new ButtonGroup();
        bg.add(r1);
	bg.add(r2);

	frame2.add(r1);
	r1.setBounds(700,190,80,35);
	frame2.add(r2);
	r2.setBounds(780,190,130,35);
        
	r1.setFont(font);
	r2.setFont(font);
       
        frame2.add(l1);
	l1.setBounds(500,130,200,25);
        frame2.add(l2);
	l2.setBounds(500,190,200,25);
        frame2.add(l3);
	l3.setBounds(500,240,200,25);
        frame2.add(l4);
	l4.setBounds(500,300,200,25);
        frame2.add(l5);
	l5.setBounds(500,360,200,25);
        frame2.add(l6);
	l6.setBounds(500,420,200,25);
        frame2.add(l7);
	l7.setBounds(500,540,200,25);
        frame2.add(l8);
	l8.setBounds(500,600,200,25);
        frame2.add(l9);
	l9.setBounds(500,670,200,25);


	frame2.add(b1);
	b1.setBounds(800,730,150,30);
	frame2.add(b2);
	b2.setBounds(600,730,150,30);
        b1.addActionListener(this);
        b2.addActionListener(this);

        frame2.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	
	frame2.setLayout(null);
        frame2.setSize(400, 300);
        frame2.setVisible(true);
 }

       @Override
 public void actionPerformed(ActionEvent ae) 
 {
    if (ae.getActionCommand().equals("Register")) 
    {
        String name = tf1.getText();
        String gender = r1.isSelected() ? "Male" : "Female";
        String phno = tf2.getText();
        String email = tf3.getText();
        String dob = tf4.getText();
        String address = tf5.getText();
        String passportID = tf6.getText();
        char[] p2 = tf7.getPassword();
        String p=new String(p2);
        char[] p3 = tf8.getPassword();
        String p1=new String(p3);
        
        
        String url = "jdbc:mysql://localhost:3306/intern";
        String user = "root";
        String pass = "pt2029";
        String sql = "INSERT INTO user (Name, Gender, Phone_number, Email_ID, DOB, Address, Passport_ID, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url,user,pass)) 
            {
                PreparedStatement statement = con.prepareStatement(sql);
                if (name.isEmpty() || gender.isEmpty() || phno.isEmpty() ||email.isEmpty()|| dob.isEmpty()|| address.isEmpty() || passportID.isEmpty() || p.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame2,"Please fill in all the details!","Warning",JOptionPane.WARNING_MESSAGE);
                    
                }
                else if(!p.equals(p1))
                {
                    JOptionPane.showMessageDialog(frame2, "Error: Please check your password!","Warning",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    // Set the parameter values in the PreparedStatement
                    statement.setString(1, name);
                    statement.setString(2, gender);
                    statement.setString(3, phno);
                    statement.setString(4, email);
                    statement.setString(5, dob);
                    statement.setString(6, address);
                    statement.setString(7, passportID);
                    statement.setString(8, p);
                    
                    // Execute the INSERT statement
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0)
                    {
                        System.out.println("Data inserted successfully!");
                        test2 t2=new test2();
                        test4 t4=new test4(test2.username,test2.password);
                        t4.f.setVisible(false);
                    }
                    else
                    {
                        System.out.println("Failed to insert data.");
                    }
                }
            }
        } 
        catch (ClassNotFoundException e) 
        {
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(frame2, "Error: Failed to insert data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else if (ae.getActionCommand().equals("Back")) 
    {
        test2 t=new test2();
        t.f.setVisible(false);
        test1 t1 = new test1(test2.username,test2.password);
        frame2.setVisible(false);
    }
}
}


public class register
{
public static void main(String ar[])
{
   test3 t3=new test3(); 
}
}