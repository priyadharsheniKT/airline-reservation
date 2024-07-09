package airline;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;  
import java.time.temporal.ChronoUnit;

class test7 implements ActionListener 
{
    JFrame f = null;
    JLabel l1, l2, l3, l4, l5,l7,l8;
    JButton b1 = null;
    JComboBox<String> c1, c2,c3,c4;
    JTextField tf1;
    Font font;

    test7() 
    {
        f = new JFrame("Cancellation");
        b1 = new JButton("Cancel");
        b1.setBounds(670, 680, 100, 30);

        l1 = new JLabel("From:");
        l1.setBounds(250, 150, 50, 30);
        l2 = new JLabel("To:");
        l2.setBounds(550, 150, 50, 30);
        l3 = new JLabel("Date (dd/mm/yy):");
        l3.setBounds(850, 150, 200, 30);
        l4 = new JLabel("Disclaimer:");
        l4.setBounds(250, 550, 100, 30);
        l5 = new JLabel("Our 24 hour cancellation policy offers a full refund if you cancel your booking within 7 days of departure.");
        l5.setBounds(310, 570, 1000, 50);       
        l7=new JLabel("Time:");
        l7.setBounds(450, 350, 50, 30);
        l8=new JLabel("Class:");
        l8.setBounds(750, 350, 50, 30);
        

        tf1 = new JTextField(25);
        tf1.setBounds(980, 150, 100, 30);

        String x[] = {"Australia", "Austria", "Bahrain", "Bangladesh", "Canada", "Denmark", "France", "Germany", "Hong Kong", "Israel", "Italy", "Japan", "Kenya", "Kuwait", "Maldives", "Myanmar", "Nepal", "Netherlands", "Oman", "Qatar", "Saudi Arabia", "Singapore", "South Korea", "Sri Lanka", "Thailand", "United Arab Emirates", "United Kingdom", "United States"};
        String y[] = {"Ahmedabad", "Amritsar", "Bengaluru", "Bhopal", "Coimbatore", "Goa", "Hyderabad", "Indore", "Jaipur", "Kochi", "Kolkata", "Kozhikode", "Madurai", "Mangaluru", "Mumbai", "New Delhi","Visakhapatnam"};
        c1 = new JComboBox<>(y);
        c1.setBounds(300, 150, 200, 30);
        c2 = new JComboBox<>(x);
        c2.setBounds(590, 150, 200, 30);
        
        String z[]=  {"Economy","Business","First Class"};
        String w[]={"Early Morning(1:00-6:00am)","Morning(7:00-10:00am)","Afternoon(12:00-3:00pm)","Evening(5:00-8:00pm)","Late Evening(9:00-11:00pm)"};
        
        c3=new JComboBox<>(w);//time
        c4=new JComboBox<>(z);//class
    
        
        c3.setBounds(510, 350, 200, 30);
        c4.setBounds(810, 350, 200, 30);
        
               
        font=new Font("Arial", Font.BOLD, 14);
        l1.setFont(font);
        l2.setFont(font);
        l3.setFont(font);
        l4.setFont(font);
        l5.setFont(font);  
        l7.setFont(font);
        l8.setFont(font);

        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l7);
        f.add(l8);
        f.add(tf1);
        f.add(b1);
        f.add(c1);
        f.add(c2);
        f.add(c3);
	f.add(c4);

        c1.addActionListener(this);
        c2.addActionListener(this);
        c3.addActionListener(this);
        c4.addActionListener(this);
        b1.addActionListener(this);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLayout(null);
        f.setSize(800, 300);
        f.setVisible(true);
    }

    @Override
  public void actionPerformed(ActionEvent ae) 
  {
    if (ae.getActionCommand().equals("Cancel"))           
    {
        int option = JOptionPane.showConfirmDialog(f, "Are you sure?", "Cancellation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) 
        {
            String url = "jdbc:mysql://localhost:3306/intern";
            String user = "root";
            String pass = "pt2029";

            String sql1 = "SELECT Name, Passport_ID FROM user WHERE Email_ID=? AND Password=?";
            String sql3 = "DELETE FROM history WHERE Name=? AND Passport_ID=? AND Origin=? AND Destination=? AND Date=?";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection con = DriverManager.getConnection(url, user, pass); 
                        PreparedStatement statement = con.prepareStatement(sql1)) 
                {
                    test2 t;
                    t = new test2();
                    t.f.setVisible(false);
                    String username = test2.username;
                    String password = test2.password;
                    statement.setString(1, username);
                    statement.setString(2, password);
                    try (ResultSet res = statement.executeQuery()) 
                    {
                        if (res.next())
                        {
                            String name = res.getString("Name");
                            String pID = res.getString("Passport_ID");
                            String origin = c1.getSelectedItem().toString();
                            String dest = c2.getSelectedItem().toString();
                            String date = tf1.getText();
                            String time=c3.getSelectedItem().toString();
                            
                             if (time.equals("Early Morning(1:00-6:00am)")) 
                              time = "EM";
                             else if (time.equals("Morning(7:00-10:00am)"))                              
                              time = "M";                               
                             else if (time.equals("Afternoon(12:00-3:00pm)"))                             
                              time = "A";                              
                             else if (time.equals("Evening(5:00-8:00pm)"))                              
                              time = "E";                            
                             else                             
                              time = "LE";
                            String c=c4.getSelectedItem().toString();
                            
                            String sql2="SELECT Cost FROM flight WHERE Origin=? AND Destination=? AND Timing=? AND Class=?;";
                            PreparedStatement statement1=con.prepareStatement(sql2);
                            statement1.setString(1, origin);
                            statement1.setString(2, dest);
                            statement1.setString(3, time);
                            statement1.setString(4, c);
                            float cost=0;            
                           
                            ResultSet res1=statement1.executeQuery();
                            while(res1.next())
                            {
                               cost=res1.getFloat("Cost");
                            }
                            LocalDate dateToday = LocalDate.now();
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
                            String input1 = dateToday.format(dtf);
                            String input2=date;
                            
                             LocalDate date1 = LocalDate.parse(input1, dtf);
                             LocalDate date2 = LocalDate.parse(input2, dtf);
                             long daysBetween = ChronoUnit.DAYS.between(date1, date2);
                            
                                                       
                            try (PreparedStatement statement2 = con.prepareStatement(sql3)) 
                            {
                                statement2.setString(1, name);
                                statement2.setString(2, pID);
                                statement2.setString(3, origin);
                                statement2.setString(4, dest);
                                statement2.setString(5, date);                                
                                statement2.executeUpdate();
                                if(daysBetween >=7)
                                {                              
                                   JOptionPane.showMessageDialog(f, "Booking cancelled successfully and a refund of Rs "+cost+" /- has been made!", "Cancellation", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(f, "Booking cancelled successfully but you are ineligible for full refund!", "Cancellation", JOptionPane.INFORMATION_MESSAGE);
                                }
                                {
                                  String sql4="INSERT INTO cancel_history(Email_ID,Password,Origin,Destination,Date,Timing,Class) VALUES(?,?,?,?,?,?,?)";
                                    try (PreparedStatement statement3 = con.prepareStatement(sql4)) 
                                    {
                                        statement3.setString(1, username);
                                        statement3.setString(2, password);
                                        statement3.setString(3, origin);
                                        statement3.setString(4, dest);
                                        statement3.setString(5, date);
                                        statement3.setString(6, time);
                                        statement3.setString(7, c);
                                        
                                        
                                        statement3.executeUpdate();
                                      
                                    }
                                }
                                
                              
                            }
                                                        
                            String sql5="UPDATE flight SET Seats = Seats + 1 WHERE Origin =? AND Destination =? AND Timing =? AND Class=?;";
                            PreparedStatement statement4=con.prepareStatement(sql5);
                            statement4.setString(1, origin);
                            statement4.setString(2, dest);                                                                   
                            statement4.setString(3, time);
                            statement4.setString(4, c);
                            statement4.executeUpdate();
                                                                               
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(f, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } 
             catch (ClassNotFoundException e) 
             {
             } 
            
            catch (SQLException ce) 
            {
                ce.printStackTrace();
                JOptionPane.showMessageDialog(f, "Cancellation failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
            test10 t10=new test10();
        }
    }
}
    }

public class cancel
{
public static void main(String ar[]) 
{
   test7 t7=new test7();
}
}