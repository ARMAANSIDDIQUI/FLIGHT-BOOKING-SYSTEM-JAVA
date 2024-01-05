import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args)
    {
        JFrame frame=new JFrame("Flight Booking System");

        JLabel namelb=new JLabel("Person Name:");
        JTextField nametf=new JTextField();

        JLabel emaillb=new JLabel("Email ID:");
        JTextField emailtf=new JTextField();

        JLabel phonelb=new JLabel("Phone Number:");
        JTextField phonetf=new JTextField();

        JLabel fromlb=new JLabel("From:");
        JTextField fromtf=new JTextField();

        JLabel tolb=new JLabel("To:");
        JTextField totf=new JTextField();

        JLabel datelb=new JLabel("Date Of Journey:");
        JTextField datetf=new JTextField();

        JLabel classlb=new JLabel("Select Class:");
        JTextField classtf=new JTextField();

        JLabel ticketidlb=new JLabel("");

        JButton bookNowbtn=new JButton("Book Now!");
        JButton clearbtn=new JButton("Clear");
        JButton closebtn=new JButton("Close");

        namelb.setBounds(40,40,150,40);
        nametf.setBounds(160,40,200,40);

        emaillb.setBounds(550,40,150,40);
        emailtf.setBounds(660,40,200,40);

        phonelb.setBounds(40,90,150,40);
        phonetf.setBounds(160,90,200,40);

        fromlb.setBounds(550,90,150,40);
        fromtf.setBounds(660,90,200,40);

        tolb.setBounds(40,140,150,40);
        totf.setBounds(160,140,200,40);

        datelb.setBounds(550,140,150,40);
        datetf.setBounds(660,140,200,40);

        bookNowbtn.setBounds(100,240,150,40);
        clearbtn.setBounds(400,240,150,40);
        closebtn.setBounds(700,240,150,40);

        ticketidlb.setBounds(300,300,500,40);

        frame.add(namelb);
        frame.add(nametf);

        frame.add(emaillb);
        frame.add(emailtf);

        frame.add(phonelb);
        frame.add(phonetf);

        frame.add(fromlb);
        frame.add(fromtf);

        frame.add(tolb);
        frame.add(totf);

        frame.add(datelb);
        frame.add(datetf);

        frame.add(classlb);
        frame.add(classtf);

        frame.add(bookNowbtn);
        frame.add(clearbtn);
        frame.add(closebtn);

        frame.add(ticketidlb);

        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(1000,600);
        frame.setVisible(true);


        clearbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nametf.setText("");
                emailtf.setText("");
                phonetf.setText("");
                fromtf.setText("");
                totf.setText("");
                datetf.setText("");
                classtf.setText("");
            }
        });

        closebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        bookNowbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nametf.getText().toString().isEmpty() ||
                        emailtf.getText().toString().isEmpty() ||
                        phonelb.getText().toString().isEmpty() ||
                        fromtf.getText().toString().isEmpty() ||
                        totf.getText().toString().isEmpty() ||
                        datelb.getText().toString().isEmpty()) {
                    ticketidlb.setText("Please fill the complete details");
                } else {
                    Random randomId = new Random();
                    int ticketid = randomId.nextInt(9999);
                    ticketidlb.setText("Your Ticket is successfully booked and ticket id : " + ticketid);

                    {
                        String url = "jdbc:mysql://localhost:3306/flightbookingsystem";
                        String username = "root";
                        String password = "";
                        try {
                            Connection connection = DriverManager.getConnection(url,username,password);
                            System.out.println("Db is connected");
                            String sql = " insert into users"
                                    + " values (null, ?, ?, ?, ?)";
                            PreparedStatement preparedStmt = connection.prepareStatement(sql);
                            preparedStmt.setString (1, nametf.getText().toString());
                            preparedStmt.setString (2, emailtf.getText().toString());
                            preparedStmt.setString   (3, phonetf.getText().toString());
                            preparedStmt.setString(4, fromtf.getText().toString());
                            preparedStmt.setString(5, totf.getText().toString());
                            preparedStmt.setString(6, datetf.getText().toString());
                            preparedStmt.setString(7, classtf.getText().toString());
                            preparedStmt.setString(8, ticketidlb.getText().toString());

                            preparedStmt.execute();


                        } catch (SQLException ex) {
                            throw new RuntimeException(ex+"db not connected");
                        }
                    }
                }
            }
    });
    }
}