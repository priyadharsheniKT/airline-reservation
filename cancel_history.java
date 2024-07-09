
package airline;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

class test10 
{
    JFrame f = null;
    JTextArea ta1, ta2, ta3, ta4,ta5;
    JLabel l2,l3,l4,l5,l6,l7;
    Font font;

    test10() 
    {
        f = new JFrame();
        
        ta1 = new JTextArea();
        ta1.setBounds(30, 200, 270, 500);
        f.add(ta1);
        
	l2=new JLabel("YOUR CANCELLATION HISTORY");
	f.add(l2);
        l2.setBounds(600,10,500,100);


	l2.setFont(new Font("Arial", Font.BOLD, 30));

        ta2 = new JTextArea();
        ta2.setBounds(330, 200, 270, 500);
        f.add(ta2);

        ta3 = new JTextArea();
        ta3.setBounds(630, 200, 270, 500);
        f.add(ta3);

        ta4 = new JTextArea();
        ta4.setBounds(930, 200, 250, 500);
        f.add(ta4);
        
        ta5=new JTextArea();
        ta5.setBounds(1210, 200, 270, 500);
        f.add(ta5);
        
        l3=new JLabel("Origin");
        l3.setBounds(30,120,270,100);
        f.add(l3);
        
        l4=new JLabel("Destination");
        l4.setBounds(330,120,270,100);
        f.add(l4);
        
        l5=new JLabel("Date");
        l5.setBounds(630,120,270,100);
        f.add(l5);
        
        l6=new JLabel("Timing");
        l6.setBounds(930,120,270,100);
        f.add(l6);
        
        l7=new JLabel("Class");
        l7.setBounds(1210,120,270,100);
        f.add(l7);
        
        
        l3.setFont(new Font("Arial", Font.BOLD, 24));
        l4.setFont(new Font("Arial", Font.BOLD, 24));
        l5.setFont(new Font("Arial", Font.BOLD, 24));
        l6.setFont(new Font("Arial", Font.BOLD, 24));
        l7.setFont(new Font("Arial", Font.BOLD, 24));
        
        
        font=new Font("Arial",Font.PLAIN,20);
        ta1.setFont(font);
        ta2.setFont(font);
        ta3.setFont(font);
        ta4.setFont(font);
        ta5.setFont(font);


	f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        f.setLayout(null);
        f.setSize(1500, 900); 
        f.setVisible(true);

                                      
        String url = "jdbc:mysql://localhost:3306/intern";
        String username = "root";
        String pass = "pt2029";
        String sql = "SELECT * FROM cancel_history WHERE Email_ID = ? AND Password = ?";

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection con = DriverManager.getConnection(url,username,pass)) 
                {
                    PreparedStatement statement = con.prepareStatement(sql);
                    test2 t=new test2();
                    t.f.setVisible(false);
                    String u=test2.username;
                    String p=test2.password;
                    System.out.println(u);
                    System.out.println(p);
                    statement.setString(1,u);
                    statement.setString(2,p);
                    ResultSet res=statement.executeQuery();
                    if (res.next())
                    {                                             
                            StringBuilder originBuilder = new StringBuilder();
                            StringBuilder destBuilder = new StringBuilder();
                            StringBuilder dateBuilder = new StringBuilder();
                            StringBuilder timeBuilder = new StringBuilder();
                            StringBuilder classBuilder = new StringBuilder();
                            
                            do
                            {
                                String origin = res.getString("Origin");
                                String dest = res.getString("Destination");
                                String date = res.getString("Date");
                                String time = res.getString("Timing");
                                String c = res.getString("Class");
                                
                                originBuilder.append(origin).append("\n");
                                destBuilder.append(dest).append("\n");
                                dateBuilder.append(date).append("\n");
                                timeBuilder.append(time).append("\n");
                                classBuilder.append(c).append("\n");
                            } while (res.next());
                            
                            ta1.setText(originBuilder.toString());
                            ta2.setText(destBuilder.toString());
                            ta3.setText(dateBuilder.toString());
                            ta4.setText(timeBuilder.toString());
                            ta5.setText(classBuilder.toString());
                            System.out.println("Cancellation History Found");
                        }
                        else
                        {
                            System.out.println("No Cancellation History Found");
                        }                   
                    res.close();
                    statement.close();
                } 
           
        }               
        catch (ClassNotFoundException e) 
        {
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(f, "Error: Failed to load data", "Error", JOptionPane.ERROR_MESSAGE);
        }
                       
    }
}

public class cancel_history
{
    public static void main(String ar[]) 
{
        test5 t10 = new test5();
    }
}