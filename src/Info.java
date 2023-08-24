import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Info extends JFrame {

	private JPanel contentPane;
	String program = "This program is created by Wyett to encrypt / decrypt messages. The program will first export an encrypted file that is based on the user’s message. The user can then send the file to the one who will decrypt the message. The receiver then use the decryption function on this program to decrypt the message by typing in the exact same passcode.\r\n"
			+ "\r\n" + "\r\n"
			+ "Please contact Wyett or Mr. Castaneda if you have any advice or want to report bugs\r\n" + "\r\n"
			+ "\r\n" + "Wyett's email: wyettzeng@gmail.com  or  yjlase@163.com\r\n" + "\r\n" + "\r\n"
			+ "Special Thanks:\r\n" + "\r\n" + "CoPrime Algorithm is take from Euclid\r\n" + "\r\n"
			+ "The RSA Algorithm is designed out of Eddie Woo’s video" + "\r\n"
			+ "https://www.youtube.com/watch?v=4zahvcJ9glg&t=287s" + "\r\n" + "\r\n"
			+ "Nth Root function is taken from https://www.cse.iitb.ac.in/~vishalm/lab_submissions/lab_8_251/lab08_outlab/NthRoot.java\r\n";
	String encryption = "Pro-Encryption:\r\n"
			+ "	A one-way encryption method designed by Wyett which encrypts the message according to the passcode user provided, the level of security and required processing power increase as the number of characters in the passcode. This encryption method provides 2 level of securities, one may Brute-force the passcode for Low-level security if he has excellent understanding of the source code and access to a computer with extremely high processing power. The high-level encryption provides higher level of security; however, normal users usually do not have the process power to decrypt the message once the passcode is more than 11 characters long.\r\n"
			+ "\r\n" + "\r\n" + "Substitution:\r\n"
			+ "	First adapted by Julius Caesar to encrypt message. Provides low level of security that would suffice most of the users. It is true that one may brute-force the passcode if he has excellent understanding of this program's source code and advance programing skills.\r\n"
			+ "\r\n" + "\r\n" + "RSA (Rivest–Shamir–Adleman):\r\n"
			+ "A public-key cryptosystems designed by Ron Rivest, Adi Shamir, and Leonard Adleman that is widely used. Provide high level of security. However, it challenges the computer’s processing power especially the High-level encryption. Recommended for important short messages.\r\n"
			+ "\r\n" + "\r\n" + "Passcode Hint:\r\n"
			+ "	A feature that allows user to send unencrypted message along with the encrypted message. The message will not be encrypted and will show up as soon as the other person load the file. An example of a passcode hint could be “mom’s first dog’s name”.\r\n"
			+ "\r\n" + "\r\n" + "Ignore-case:\r\n"
			+ "	By selecting this the program will lower-cased the whole passcode, thus the one who will decrypt this message does not have to worry about Capitalization in their passcode.\r\n"
			+ "";
	String decryption = "In decryption process you should first import the encrypted file, then the program will automatically identify the encryption method and recognize if there is any unencrypted message left by the one who encrypted this message. If there is, then it will show below the button. Moreover, the program will also indicate whether the one who encrypted the message choose to ignore-case the passcode or not. If so, the program will do it automatically.\r\n"
			+ "\r\n" + "\r\n"
			+ "If you happen to forget the passcode of a really important message, and couldn’t reach the one who encrypted this message. You may ask for help from Mr. Castaneda or Wyett.\r\n"
			+ "\r\n" + "\r\n" + "Wyett's email: wyettzeng@gmail.com  or  yjlase@163.com\r\n" + "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info frame = new Info(100);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Info(int help) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(1000, 100, 900, 600);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);

		JLabel label = new JLabel("About this Program");
		label.setBounds(310, 80, 280, 30);
		label.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		label.setForeground(Color.WHITE);
		label.setBackground(new Color(240, 240, 240));
		contentPane.add(label);

		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setForeground(Color.WHITE);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setEditable(false);
		textArea.setText("");
		textArea.setBounds(15, 170, 870, 400);
		contentPane.add(textArea);

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(15, 130, 870, 400);
		scroll.setBorder(null);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scroll);

		JButton btnAboutThisProgram = new JButton("This Program");
		btnAboutThisProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label.setText("About this Program");
				textArea.setText(program);
				textArea.setCaretPosition(0);
			}
		});
		btnAboutThisProgram.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnAboutThisProgram.setBounds(15, 15, 250, 30);
		contentPane.add(btnAboutThisProgram);

		JButton btnDecryption = new JButton("Decryption");
		btnDecryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label.setText("About Decryption");
				textArea.setText(decryption);
				textArea.setCaretPosition(0);
			}
		});
		btnDecryption.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnDecryption.setBounds(635, 15, 250, 30);
		contentPane.add(btnDecryption);

		JButton btnEncryption = new JButton("Encryption");
		btnEncryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label.setText("About Encryption");
				textArea.setText(encryption);
				textArea.setCaretPosition(0);
			}
		});
		btnEncryption.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnEncryption.setBounds(325, 15, 250, 30);
		contentPane.add(btnEncryption);

		// 0 = program, 1 = encryption, 2 = decryption
		if (help == 1) {
			label.setText("About Encryption");
			textArea.setText(encryption);
		} else if (help == 2) {
			label.setText("About Decryption");
			textArea.setText(decryption);
		} else {
			label.setText("About this Program");
			textArea.setText(program);
			textArea.setCaretPosition(0);
		}
	}

}
