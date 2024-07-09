
package airline;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

class test8 
{
JFrame frame2=null;
JLabel l,l1,l2,l3,l4,l5,l6,l7;
JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7;
Font font;
private String username;
private String password;
test8(String u,String p)
{
this.username=u;
this.password=p;
frame2=new JFrame("User details");

l1=new JLabel("Name:");
l2=new JLabel("Gender");
l3 = new JLabel("Phone number:");
l4 = new JLabel("Email id:");
l5 = new JLabel("Date of birth:");
l6 = new JLabel("Address:");
l7 = new JLabel("Passport ID:");

l=new JLabel("USER DETAILS");

frame2.add(l);
l.setBounds(650,50,500,25);
l.setFont(new Font("Arial", Font.BOLD, 30));

font=new Font("Arial", Font.BOLD, 17);

l1.setFont(font);
l2.setFont(font);
l3.setFont(font);
l4.setFont(font);
l5.setFont(font);
l6.setFont(font);
l7.setFont(font);


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
l6.setBounds(500,420,200,20);
frame2.add(l7);
l7.setBounds(500,540,200,25);

        tf1= new JTextField();
	tf2= new JTextField();
	tf3= new JTextField();
	tf4= new JTextField();
	tf5=new JTextField();
	tf6= new JTextField();
	tf7= new JTextField();
	

	frame2.add(tf1);
	tf1.setBounds(700,130,200,25);
	frame2.add(tf2);
	tf2.setBounds(700,190,200,25);
	frame2.add(tf3);
	tf3.setBounds(700,240,200,25);
	frame2.add(tf4);
	tf4.setBounds(700,300,200,25);
	frame2.add(tf5);
	tf5.setBounds(700,360,200,25);
	frame2.add(tf6);
	tf6.setBounds(700,420,200,70);
	frame2.add(tf7);
	tf7.setBounds(700,540,200,25);

	frame2.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	frame2.setLayout(null);
        frame2.setSize(400, 300);
        frame2.setVisible(true);
        
   String url = "jdbc:mysql://localhost:3306/intern";
   String user = "root";
   String pass = "pt2029";      
   String sql ="select* from user where Email_ID=? and Password=?";
   try
   {
   Class.forName("com.mysql.cj.jdbc.Driver");
   Connection con=DriverManager.getConnection(url,user,pass);  
   PreparedStatement statement=con.prepareStatement(sql);
   statement.setString(1,username);
   statement.setString(2,password);
   ResultSet res=statement.executeQuery();
   if(res.next())
   {
       String name=res.getString("Name");
       String gender=res.getString("Gender");
       String phno=res.getString("Phone_number");
       String email=res.getString("Email_ID");
       String dob=res.getString("DOB");
       String address=res.getString("Address");
       String pID=res.getString("Passport_ID");
      
       
      tf1.setText(name);
      tf2.setText(gender);
      tf3.setText(phno);
      tf4.setText(email);
      tf5.setText(dob);
      tf6.setText(address);
      tf7.setText(pID);
     
      res.close();
      statement.close();
      con.close();
   }
   else
   {
   System.out.println("Invalid login credentials");
   }
   }
   catch (ClassNotFoundException e) 
        {
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(frame2, "Error: Failed to load data", "Error", JOptionPane.ERROR_MESSAGE);
        }
}
}

public class user
{
public static void main(String ar[])
{
    test2 t=new test2();
    t.f.setVisible(false);
    test8 t8=new test8(t.username,t.password);
}
}
