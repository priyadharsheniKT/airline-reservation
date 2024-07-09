
package airline;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class test9 implements ActionListener
{
    
    private String origin;
    private String dest;
    private String date;
    private String time;
    private String c;
    
    JFrame f=null;
    JTextArea ta=null;
    JLabel l1;
    JButton b1=null;
    Font font;
    
    test9(String o,String d,String da,String t,String c)
    {
        this.origin=o;
        this.dest=d;
        this.date=da;
        this.time=t;
        this.c=c;
       
        f=new JFrame("Bill");
         
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBackground(new Color(176, 224, 230));
        
        l1=new JLabel("TICKET PAYMENT");
        l1.setFont(new Font("Arial",Font.BOLD,32));
        ta=new JTextArea();
        b1=new JButton("Pay");
        
        
        l1.setBounds(650,80,500,100);
        ta.setBounds(550,200,500,250);
        b1.setBounds(750,550,100,50);
        
        font=new Font("Arial",Font.BOLD,17);
        ta.setFont(font);
        
        
        f.add(l1);
        f.add(ta);
        f.add(b1);
        
        b1.addActionListener(this);
        
        
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        f.setLayout(null);
        f.setSize(1000,700);
        f.setVisible(true);
        
        String url = "jdbc:mysql://localhost:3306/intern";
        String user = "root";
        String pass = "pt2029";
        String sql = "SELECT Cost FROM flight WHERE Origin=? AND Destination=? AND Timing=? AND Class=?;";

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url,user,pass); 
                    PreparedStatement statement = con.prepareStatement(sql)) 
            {
                statement.setString(1, origin);
                statement.setString(2, dest);
                statement.setString(3, time);
                statement.setString(4, c);
                try (ResultSet res = statement.executeQuery()) 
                {
                    float cost;
                    if(res.next())
                    {
                       cost= res.getFloat("Cost");
                       if(time.equals("EM"))
                           time="Early Morning(1:00-6:00am)";
                       else if(time.equals("M"))
                           time="Morning(7:00-10:00am)";
                       else if(time.equals("A"))
                           time="Afternoon(12:00-3:00pm)";
                       else if(time.equals("E"))
                           time="Evening(5:00-8:00pm)";
                       else
                            time="Late Evening(9:00-11:00pm)";
                        
                        String t1="Origin: "+origin;
                        String t2="Destination: "+dest;
                        String t3="Date: "+date;
                        String t4="Time: "+time;
                        String t5="Class: "+c;
                        String t6="Total Cost: "+cost+" /- Rs ";
                        ta.setText(t1+"\n\n"+t2+"\n\n"+t3+"\n\n"+t4+"\n\n"+t5+"\n\n"+t6);
                        
                    }
                }
            }
        } 
        catch (ClassNotFoundException e) 
        {
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(f, "Payment failed", "Error", JOptionPane.ERROR_MESSAGE);
        }       
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("Pay"))
        {
            int option = JOptionPane.showConfirmDialog(f, "Click yes to proceed", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) 
            {
                int op = JOptionPane.showOptionDialog(null, "Payment successful", "Payment confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if (op == JOptionPane.OK_OPTION) 
                {
                    test5 t5=new test5();
                    f.setVisible(false);
                    String url = "jdbc:mysql://localhost:3306/intern";
                    String user = "root";
                    String pass = "pt2029";
                    try
                    {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection(url,user,pass);  
                    String sql1="UPDATE flight SET Seats = Seats - 1 WHERE Origin =? AND Destination =? AND Timing =? AND Class=?;";                  
                    PreparedStatement statement1 = con.prepareStatement(sql1);
                    statement1.setString(1, origin);
                    statement1.setString(2, dest);
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
                             
            
                    statement1.setString(3,time);
                    statement1.setString(4, c);
                    statement1.executeUpdate();                   
                    }
                    catch (ClassNotFoundException e) 
                   {
                   } 
                    catch (SQLException e) 
                    {
                   JOptionPane.showMessageDialog(f, "Payment failed", "Error", JOptionPane.ERROR_MESSAGE);
                    }                                         
                }
            }
            else
            {
               test6 t6=new test6();//back to search
                
            }
        }
    }
}
    



public class bill 
{
    public static void main(String ar[])
    {
        
    }
    
}
