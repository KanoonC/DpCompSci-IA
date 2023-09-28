package IA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;

public class LaunchApp implements ActionListener{
	
	private static JLabel qTitle, qOneLabel, qTwoLabel, title, img, img2, img3;
	private static JButton btnNxt, btnRtn, btnPrint, btnEmail;
	public static JComboBox q1, q2;
	
	static Boolean quest;
	
	static String chosenSeason, chosenRegion, emoji, prefecture, prefecture2, place, place2, food, event, word, pic, pic2, pic3;
	
	public JFrame frame = new JFrame();
	public JPanel panel = new JPanel();
	
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
	
	public LaunchApp() {
		frame.setSize(1000, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		panel.setLayout(null);
		frame.add(panel);
		
		question();
		
		frame.setVisible(true);
	}

	public void question() {
		frame.setTitle("Question");
		
		qTitle = new JLabel("Where & when are you planning to go?");
		qTitle.setBounds(140, 170, 800, 100);
		qTitle.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
		qTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(qTitle);
		
		String a1[] = {"Hokkaido　北海道", "Tohoku　東北", "Kanto　関東",  "Chubu　中部", "Kansai　関西", "Chugoku　中国", "Shikoku　四国", "Kyushu & Okinawa　九州/沖縄"};
		String a2[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
				
		qOneLabel = new JLabel("Select a Region");
		qOneLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		qOneLabel.setBounds(320, 270, 300, 50);
		
		q1 = new JComboBox(a1);
		q1.setBounds(480, 273, 230, 50);
		
		qTwoLabel = new JLabel("Select a Month");
		qTwoLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		qTwoLabel.setBounds(320, 320, 300, 50);
		
		q2 = new JComboBox(a2);
		q2.setBounds(480, 323, 230, 50);
				
		panel.add(qOneLabel);
		panel.add(q1);
		panel.add(qTwoLabel);
		panel.add(q2);
		
		btnNxt = new JButton("Next");
		btnNxt.setBounds(490, 500, 80, 25);
		btnNxt.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnNxt.addActionListener(this);
		panel.add(btnNxt);
	}
	
	public void display() {
		frame.setTitle(chosenRegion + ", " + chosenSeason);
		String jpRegion = "";
		if(chosenRegion.equals("Hokkaido")) {
			jpRegion = "北海道";
		}else if(chosenRegion.equals("Tohoku")) {
			jpRegion = "東北地方";
		}else if(chosenRegion.equals("Kanto")) {
			jpRegion = "関東地方";
		}else if(chosenRegion.equals("Chubu")) {
			jpRegion = "中部地方";
		}else if(chosenRegion.equals("Kansai")) {
			jpRegion = "関西地方";
		}else if(chosenRegion.equals("Chugoku")) {
			jpRegion = "中国地方";
		}else if(chosenRegion.equals("Shikoku")) {
			jpRegion = "四国地方";
		}else if(chosenRegion.equals("Kyushu-Okinawa")) {
			jpRegion = "九州/沖縄地方";
		}
			
		title = new JLabel(chosenRegion + " Region (" + jpRegion + ")");
		title.setBounds(50, 50, 800, 100);
		title.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
		title.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(title);
		
		title = new JLabel(chosenSeason + " Season " + emoji);
		title.setBounds(50, 100, 500, 100);
		title.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
		title.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(title);
		
		//checking for the extra column in the database
		if(prefecture2 != null) {
			title = new JLabel("Prefectures: " + prefecture + ", " + prefecture2);
			title.setBounds(50, 150, 800, 100);
			title.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			title.setLayout(new FlowLayout(FlowLayout.CENTER));
			panel.add(title);
		}else {
			title = new JLabel("Prefectures: " + prefecture);
			title.setBounds(50, 150, 800, 100);
			title.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			title.setLayout(new FlowLayout(FlowLayout.CENTER));
			panel.add(title);
		}
		
		if(place2 != null) {
			title = new JLabel("Places: " + place + ", " + place2);
			title.setBounds(50, 200, 800, 100);
			title.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			title.setLayout(new FlowLayout(FlowLayout.CENTER));
			panel.add(title);
		}else {
			title = new JLabel("Places: " + place);
			title.setBounds(50, 200, 800, 100);
			title.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			title.setLayout(new FlowLayout(FlowLayout.CENTER));
			panel.add(title);
		}
		
		title = new JLabel("Food: " + food);
		title.setBounds(50, 250, 500, 100);
		title.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		title.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(title);
		
		title = new JLabel("Events: " + event);
		title.setBounds(50, 300, 500, 100);
		title.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		title.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(title);
		
		title = new JLabel("Words: " + word);
		title.setBounds(50, 350, 700, 100);
		title.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		title.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(title);
		
		//insert image
		img = new JLabel();
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(pic));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found.");
			e.printStackTrace();
		}
		double percent = 0.27;
		Image image = bufferedImage.getScaledInstance((int)(bufferedImage.getWidth() * percent), (int)(bufferedImage.getHeight() * percent), Image.SCALE_SMOOTH);
		img.setIcon(new ImageIcon(image));
		Dimension size = img.getPreferredSize();
		
		
		//insert image2
		img2 = new JLabel();
		BufferedImage bufferedImage2 = null;
		try {
			bufferedImage2 = ImageIO.read(new File(pic2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found.");
			e.printStackTrace();
		}
		double percent2 = 0.27;
		Image image2 = bufferedImage2.getScaledInstance((int)(bufferedImage2.getWidth() * percent2), (int)(bufferedImage2.getHeight() * percent2), Image.SCALE_SMOOTH);
		img2.setIcon(new ImageIcon(image2));
		Dimension size2 = img2.getPreferredSize();
		
		
		//insert image3
		img3 = new JLabel();
		BufferedImage bufferedImage3 = null;
		try {
			bufferedImage3 = ImageIO.read(new File(pic3));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found.");
			e.printStackTrace();
		}
		double percent3 = 0.27;
		Image image3 = bufferedImage3.getScaledInstance((int)(bufferedImage3.getWidth() * percent3), (int)(bufferedImage3.getHeight() * percent3), Image.SCALE_SMOOTH);
		img3.setIcon(new ImageIcon(image3));
		Dimension size3 = img3.getPreferredSize();
		
		int smallestHeight = 0;
		int temp;
		int[] imgHeight = {size.height, size2.height, size3.height};
		
		for(int i = imgHeight.length - 1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				if(imgHeight[j] > imgHeight[j + 1]) {
					temp = imgHeight[j];
					imgHeight[j] = imgHeight[j + 1];
					imgHeight[j + 1] = temp; 
				}
			}
		}
		
		img.setBounds(730, 0, size.width, imgHeight[0]);
		img2.setBounds(730, imgHeight[0], size.width, imgHeight[0]);
		img3.setBounds(730, imgHeight[0] * 2, size.width, imgHeight[0]);
		
		panel.add(img);
		panel.add(img2);
		panel.add(img3);
		
		btnRtn = new JButton("Return");
		btnRtn.setBounds(50, 450, 80, 25);
		btnRtn.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnRtn.addActionListener(this);
		panel.add(btnRtn);
		
		btnPrint = new JButton("Print");
		btnPrint.setBounds(50, 500, 80, 25);
		btnPrint.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnPrint.addActionListener(this);
		panel.add(btnPrint);
		
		btnEmail = new JButton("Email");
		btnEmail.setBounds(50, 550, 80, 25);
		btnEmail.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnEmail.addActionListener(this);
		panel.add(btnEmail);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String season = "";
		String region = "";
		
		//Region
		if(q1.getSelectedItem().equals("Hokkaido　北海道")) {
			region = "Hokkaido";
			chosenRegion = region;
		}else if(q1.getSelectedItem().equals("Tohoku　東北")) {
			region = "Tohoku";
			chosenRegion = region;
		}else if(q1.getSelectedItem().equals("Kanto　関東")) {
			region = "Kanto";
			chosenRegion = region;
		}else if(q1.getSelectedItem().equals("Chubu　中部")) {
			region = "Chubu";
			chosenRegion = region;
		}else if(q1.getSelectedItem().equals("Kansai　関西")) {
			region = "Kansai";
			chosenRegion = region;
		}else if(q1.getSelectedItem().equals("Chugoku　中国")) {
			region = "Chugoku";
			chosenRegion = region;
		}else if(q1.getSelectedItem().equals("Shikoku　四国")) {
			region = "Shikoku";
			chosenRegion = region;
		}else if(q1.getSelectedItem().equals("Kyushu & Okinawa　九州/沖縄")) {
			region = "Kyushu-Okinawa";
			chosenRegion = region;
		}
		
		//Season
		if(q2.getSelectedItem().equals("March") || q2.getSelectedItem().equals("April") || q2.getSelectedItem().equals("May")) {
			season = "Spring";
			chosenSeason = season;
			emoji = "🌸";
		}else if(q2.getSelectedItem().equals("June")|| q2.getSelectedItem().equals("July") || q2.getSelectedItem().equals("August")) {
			season = "Summer";
			chosenSeason = season;
			emoji = "😎";
		}else if(q2.getSelectedItem().equals("September") || q2.getSelectedItem().equals("October") || q2.getSelectedItem().equals("November")) {
			season = "Autumn";
			chosenSeason = season;
			emoji = "🍁";
		}else if(q2.getSelectedItem().equals("December") || q2.getSelectedItem().equals("January") || q2.getSelectedItem().equals("February")) {
			season = "Winter";
			chosenSeason = season;
			emoji = "⛄";
		}
		
		//Connecting to SQL
		Connection con = connectToDatabase();
		Statement stm = null;
		
		try {
			stm = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "SELECT Prefecture, Prefecture2, Place, Place2, Food, Event, Word, Image, Image2, Image3 FROM Japan WHERE Region='" + region + "' and Season='" + season + "'";
		
		ResultSet rs = null;
		try {
			rs = stm.executeQuery(sql);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(e.getSource() == btnNxt) {
			try {
				if(rs.next()) {
					prefecture = rs.getString(1);
					prefecture2 = rs.getString(2);
					place = rs.getString(3);
					place2 = rs.getString(4);
					food = rs.getString(5);
					event = rs.getString(6);
					word = rs.getString(7);
					pic = rs.getString(8);
					pic2 = rs.getString(9);
					pic3 = rs.getString(10);
					
					panel.removeAll();
					panel.revalidate();
					panel.repaint();
					display();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		if(e.getSource() == btnRtn) {
			btnRtn.setBounds(0, 0, 0, 0);
			panel.removeAll();
			panel.revalidate();
			panel.repaint();
			question();
		}
		
		
		//to print entire JFrame
		if(e.getSource() == btnPrint) {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setJobName("Print Data");
			
			job.setPrintable(new Printable(){
				public int print(Graphics pg, PageFormat pf, int pageNum) {
					if(pageNum > 0) {
						return Printable.NO_SUCH_PAGE;
					}
					
					Graphics2D g2 = (Graphics2D)pg;
					g2.translate(pf.getImageableX(), pf.getImageableY());
					g2.scale(0.6,0.7);
					
					frame.paint(g2);
					return Printable.PAGE_EXISTS;
				}
			});
			boolean ok = job.printDialog();
			if(ok) {
				try {
					job.print();
				}
				catch (PrinterException ex) {
				}
			}
		}
		
		if(e.getSource() == btnEmail) {
			Email email = new Email();
		}

	}

}
