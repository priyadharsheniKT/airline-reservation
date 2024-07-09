
package airline;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class test4 implements ActionListener
{
JFrame f=null;
JMenuBar mb;
JMenu dash;
JMenuItem book,ch,search,cancel,user,log;
JLabel l1,l2;
private final String username;
private final String password;

test4(String u,String p)
{
this.username=u;
this.password=p;

f=new JFrame();
mb=new JMenuBar();
dash=new JMenu("Dashboard");
book=new JMenuItem("Booking history");
ch=new JMenuItem("Cancellation history");
search=new JMenuItem("Search & book flights");
cancel=new JMenuItem("Cancellation");
user=new JMenuItem("User details");
log=new JMenuItem("Logout");
f.setJMenuBar(mb);
mb.add(dash);
dash.add(book);
dash.addSeparator();
dash.add(ch);
dash.addSeparator();
dash.add(search);
dash.addSeparator();
dash.add(cancel);
dash.addSeparator();
dash.add(user);
dash.addSeparator();
dash.add(log);

dash.setMnemonic('f');
book.setMnemonic('b');
ch.setMnemonic('a');
search.setMnemonic('s');
cancel.setMnemonic('c');
user.setMnemonic('u');
log.setMnemonic('l');


dash.setPreferredSize(new Dimension(200,30));
book.setPreferredSize(new Dimension(200,50));
ch.setPreferredSize(new Dimension(200,50));
search.setPreferredSize(new Dimension(200,50));
cancel.setPreferredSize(new Dimension(200,50));
user.setPreferredSize(new Dimension(200,50));
log.setPreferredSize(new Dimension(200,50));

book.addActionListener(this);
ch.addActionListener(this);
search.addActionListener(this);
cancel.addActionListener(this);
user.addActionListener(this);
log.addActionListener(this);

l1=new JLabel("Welcome to your dashboard!");
l1.setBounds(550,200,500,100);
l1.setFont(new Font("Arial",Font.BOLD,32));
f.add(l1);

l2=new JLabel("Here at your dashboard you can book flights, view your booking history and cancel your fights anytime");
l2.setFont(new Font("Arial",Font.PLAIN,20));
l2.setBounds(350,300,1200,100);
f.add(l2);

f.setLayout(null);
f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
f.setSize(1000,1000);
f.setVisible(true);
}

@Override
public void actionPerformed(ActionEvent ae)
{
if (ae.getActionCommand().equals("Booking history"))
	{
	 test5 t5=new test5();
	} 
else if (ae.getActionCommand().equals("Cancellation history"))
	{
	test10 t10=new test10();
	} 
else if (ae.getActionCommand().equals("Search & book flights"))
	{
	test6 t6=new test6();
	} 
else if (ae.getActionCommand().equals("Cancellation"))
	{
	test7 t7 = new test7();
	}
else if (ae.getActionCommand().equals("User details"))
	{
	test8 t8=new test8(username,password);
	}
else if (ae.getActionCommand().equals("Logout"))
	{
	test1 t1=new test1(username,password);
	} 
} 
}

public class main
{
public static void main(String ar[])
{
  
}
}