package IA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPQ2 implements ActionListener{
	
	private static JLabel questionTwoLabel;
	private static JRadioButton spring, summer, autumn, winter; 
	private static JButton btnNxt;
	
	public JPQ2() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setTitle("Question 2");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		panel.setLayout(null);
		frame.add(panel);
		
		questionTwoLabel = new JLabel("When are you planning to go?");
		questionTwoLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
		questionTwoLabel.setBounds(210, 120, 500, 50);
		panel.add(questionTwoLabel);
		
		spring = new JRadioButton("March ~ May");
		spring.setBounds(300, 200, 300, 50);
		spring.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		spring.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		summer = new JRadioButton("June ~ August");
		summer.setBounds(300, 250, 300, 50);
		summer.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		summer.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		autumn = new JRadioButton("September ~ November");
		autumn.setBounds(300, 300, 300, 50);
		autumn.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		autumn.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		winter = new JRadioButton("December ~ February");
		winter.setBounds(300, 350, 300, 50);
		winter.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		winter.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		btnNxt = new JButton("Next");
		btnNxt.setBounds(350, 500, 80, 25);
		btnNxt.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnNxt.addActionListener(this);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(spring);
		btnGroup.add(summer);
		btnGroup.add(autumn);
		btnGroup.add(winter);
		
		panel.add(spring);
		panel.add(summer);
		panel.add(autumn);
		panel.add(winter);
		panel.add(btnNxt);
		
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(spring.isSelected() && e.getSource() == btnNxt) {
			JOptionPane.showMessageDialog(null, "Selected: Spring");
		}else if(summer.isSelected() && e.getSource() == btnNxt) {
			JOptionPane.showMessageDialog(null, "Selected: Summer");
		}else if(autumn.isSelected() && e.getSource() ==btnNxt){
			JOptionPane.showMessageDialog(null, "Selected: Autumn");
		}else if(winter.isSelected() && e.getSource() == btnNxt){
			JOptionPane.showMessageDialog(null, "Selected: Winter");
		}else {
			JOptionPane.showMessageDialog(null, "Please select one");
		}
	}

}
