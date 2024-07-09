
package airline;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class test6 implements ActionListener {
    JFrame f=null;
    JLabel l1,l2,l3,l4,l5,l6;
    JButton b1=null;
    JComboBox<String> c1,c2,c3,c4;
    JTextField tf1;
    Font font;

    test6() {
        f=new JFrame("Search & Book Flights");

        l1=new JLabel("From:");
        l2=new JLabel("To:");
        l3=new JLabel("Select date:");
        l4=new JLabel("Select  time:");
        l5=new JLabel("Class:");
        l6=new JLabel("Search and book your flights at ease with us!");

        b1=new JButton("Search");
        b1.setFont(new Font("Arial",Font.BOLD,15));

        tf1=new JTextField(20);

        String y[] = {"Australia", "Austria", "Bahrain", "Bangladesh", "Canada", "Denmark", "France", "Germany", "Hong Kong", "Israel", "Italy", "Japan", "Kenya", "Kuwait", "Maldives", "Myanmar", "Nepal", "Netherlands", "Oman", "Qatar", "Saudi Arabia", "Singapore", "South Korea", "Sri Lanka", "Thailand", "United Arab Emirates", "United Kingdom", "United States"};
        String x[] = {"Ahmedabad", "Amritsar", "Bengaluru", "Bhopal", "Coimbatore", "Goa", "Hyderabad", "Indore", "Jaipur", "Kochi", "Kolkata", "Kozhikode", "Madurai", "Mangaluru", "Mumbai", "New Delhi","Visakhapatnam"};
        String z[]=  {"Economy","Business","First Class"};
        String w[]={"Early Morning(1:00-6:00am)","Morning(7:00-10:00am)","Afternoon(12:00-3:00pm)","Evening(5:00-8:00pm)","Late Evening(9:00-11:00pm)"};
        c1=new JComboBox<>(x);//from
        c2=new JComboBox<>(y);//to
        c3=new JComboBox<>(z);//class
        c4=new JComboBox<>(w);//time

        font=new Font("Arial",Font.BOLD,14);

        l1.setFont(font);
        l2.setFont(font);
        l3.setFont(font);
        l4.setFont(font);
        l5.setFont(font);

        l1.setBounds(70,320,50,30);//from
        l2.setBounds(320,320,50,30);//to
        l3.setBounds(560,320,100,30);//date
        l4.setBounds(830,320,100,30);//time
        l5.setBounds(1150,320,50,30);//class
        l6.setBounds(550, 150, 800, 70);
        l6.setFont(new Font("Arial",Font.BOLD,23));

        b1.setBounds(700,600,100,30);

        tf1.setBounds(670,320,150,30);

        c1.setBounds(120,320,180,30);
        c2.setBounds(360,320,180,30);
        c3.setBounds(1200,320,150,30);
        c4.setBounds(930,320,200,30);//time

        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);

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
        f.setSize(600,600);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Action performed: " + ae.getActionCommand());

        if (ae.getActionCommand().equals("Search")) {
            System.out.println("Search button clicked");

            String url = "jdbc:mysql://localhost:3306/intern";
            String user = "root";
            String pass = "pt2029";
            String sql = "SELECT EXISTS(SELECT 1 FROM flight where Origin=? AND Destination=? AND Timing=? AND Class=?) AS record;";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection con = DriverManager.getConnection(url, user, pass)) {
                    System.out.println("Connected to database");

                    PreparedStatement statement = con.prepareStatement(sql);
                    String origin = c1.getSelectedItem().toString();
                    String dest = c2.getSelectedItem().toString();
                    String time = c4.getSelectedItem().toString();
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
                                                                            
                    String c = c3.getSelectedItem().toString();
                    String date = tf1.getText();
                    statement.setString(1, origin);
                    statement.setString(2, dest);
                    statement.setString(3, time);
                    statement.setString(4, c);
                    ResultSet res = statement.executeQuery();
                    if (res.next()) {
                        int r = res.getInt("record");
                        String sql3="SELECT Seats FROM flight where Origin=? AND Destination=? AND Timing=? AND Class=?;";
                        statement = con.prepareStatement(sql3);
                        statement.setString(1, origin);
                        statement.setString(2, dest);
                        statement.setString(3, time);
                        statement.setString(4, c);
                        res=statement.executeQuery();
                        int count=0;
                        if(res.next()) {
                            count=res.getInt("Seats");
                        }
                        boolean found = (r == 1);
                        boolean valid=false;                  
                        if(!date.isEmpty()) {
                            valid=true;
                        }
                        if (found && count>0) {
                            if(valid) {
                                int option = JOptionPane.showOptionDialog(null, "Your booking is confirmed!", "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                                if (option == JOptionPane.OK_OPTION) {
                                    String sql1 = "select Name,Passport_ID from user where Email_ID=? and Password=?";
                                    test2 t = new test2();
                                    t.f.setVisible(false);
                                    String username = test2.username;
                                    String password = test2.password;

                                    statement = con.prepareStatement(sql1);
                                    statement.setString(1, username);
                                    statement.setString(2, password);
                                    res = statement.executeQuery();
                                    if (res.next()) {
                                        String name = res.getString("Name");
                                        String pID = res.getString("Passport_ID");
                                        String sql2 = "INSERT INTO history(Name,Passport_ID,Origin,Destination,Date,Timing,Class) "
                                            + "VALUES(?,?,?,?,?,?,?);";
                                        statement = con.prepareStatement(sql2);
                                        statement.setString(1, name);
                                        statement.setString(2, pID);
                                        statement.setString(3, origin);
                                        statement.setString(4, dest);

                                        statement.setString(5, date);
                                        statement.setString(6, time);
                                        statement.setString(7, c);
                                        statement.executeUpdate();
                                        JOptionPane.showConfirmDialog(f,"Click yes to make payment?","Payment",JOptionPane.YES_NO_OPTION);
                                        test9 t9=new test9(origin,dest,date,time,c);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(f, "Please fill in all the details!","Warning",JOptionPane.WARNING_MESSAGE);
                            }
                        } else {  
                            JOptionPane.showMessageDialog(f, "Unfortunately seat unavailable :(","Warning",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    res.close();
                    statement.close();
                }
            } catch (ClassNotFoundException ce) {
                System.out.println("Error: MySQL JDBC Driver not found");
            } catch (SQLException ce) {
                JOptionPane.showMessageDialog(f, "Error: Failed to load data", "Error", JOptionPane.ERROR_MESSAGE);
                ce.printStackTrace();
            }
        }
    }
}

public class search {
    public static void main(String ar[]) {
        test6 t6=new test6();
    }
}
