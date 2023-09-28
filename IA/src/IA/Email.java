package IA;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class Email implements ActionListener{
	private static JTextField email;
	private static JButton button;
	public JFrame frame = new JFrame();
	
	public Email() {
		JPanel panel = new JPanel();
		frame.setTitle("Email");
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(panel);
		
		panel.setLayout(null);
		
		email = new JTextField();
		email.setBounds(100, 20, 165, 25);
		panel.add(email);
		
		button = new JButton("Email");
		button.setBounds(100, 40, 165, 25);
		button.addActionListener(this);
		panel.add(button);
		
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String emailAddress = email.getText();
		if(e.getSource() == button) {
			try {
				JavaMailUtil.sendMail(emailAddress);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}


