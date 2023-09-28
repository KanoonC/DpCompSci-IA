package IA;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

import javax.swing.*;

public class GUI implements ActionListener {
	
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel success;
	public JFrame frame = new JFrame();
	
	
	public Connection connectToDatabase() {
	    // MySQL database credentials
	    String url = "jdbc:mysql://localhost:3306/Users";
	    String username = "root";
	    String password = "Kanoon20@5";
	    Connection connection = null;

	    try {
	        // Register the MySQL JDBC driver
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // Create the database connection
	        connection = DriverManager.getConnection(url, username, password);
	        System.out.println("Connected to the database!");
	    } catch (ClassNotFoundException e) {
	        System.out.println("MySQL JDBC driver not found.");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.out.println("Failed to connect to the database.");
	        e.printStackTrace();
	    }

	    return connection;
	}
	
	public static void main(String[] args){
		new GUI();
	}
	
	public GUI() { // Constructor for the Login Page
		JPanel panel = new JPanel();
		frame.setTitle("Login Page");
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(panel);
		
		panel.setLayout(null);
		
		userLabel = new JLabel("Username");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
	
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);
		
		button = new JButton("Login");
		button.setBounds(130, 100, 80, 25);
		button.addActionListener(this);
		panel.add(button);
		
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String user = userText.getText();
		char[] pass = passwordText.getPassword();
		
		String dataUser = userText.getText();
		String dataPass = passwordText.getText();
		
		Connection con = connectToDatabase();
		Statement stm = null;
		
		try {
			stm = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "SELECT Password FROM User where Username='"+ dataUser + "'";
		
		ResultSet rs = null;
		try {
			rs = stm.executeQuery(sql);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if(rs.next()) {
				if(dataPass.equals(rs.getString(1))){
					frame.dispose();
					LaunchApp launchApp = new LaunchApp();
				}else {
					JOptionPane.showMessageDialog(null, "Username or password is wrong, try again");
					userText.setText("");
					passwordText.setText("");
				}
				
				
			}else if(user.equals("Admin") && CheckingPassword(pass)) {
				success.setText("Login Successful...");
				
				if(e.getSource() == button) {
					frame.dispose();
					LaunchApp launchApp = new LaunchApp();
					
					
				}
			}else {
				JOptionPane.showMessageDialog(null, "Username or password is wrong, try again.");
				userText.setText("");
				passwordText.setText("");
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static boolean CheckingPassword(char[] password) {
		char[] correctPassword = new char[] {'a', 'd', 'm', 'i', 'n'};
		
		if(password.length != correctPassword.length) {
			return false;
		}
		
		for(int i = 0; i < password.length; i++) {
			if(password[i] != password[i]) {
				return false;
			}
		}
		return true;
	}
}
