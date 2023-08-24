import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class Main_Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Menu frame = new Main_Menu();
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
	public Main_Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnEncryption = new JButton("Encryption");
		btnEncryption.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnEncryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Encryption_Advance en = new Encryption_Advance();
				en.setVisible(true);
				dispose();
			}
		});
		btnEncryption.setBounds(100, 90, 200, 30);
		contentPane.add(btnEncryption);
		
		JButton btnDecryption = new JButton("Decryption");
		btnDecryption.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnDecryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decryption dec = new Decryption();
				dec.setVisible(true);
				dispose();
			}
		});
		btnDecryption.setBounds(420, 90, 200, 30);
		contentPane.add(btnDecryption);
		
		JLabel lblThisProgramIs = new JLabel("This program was created by Wyett to");
		lblThisProgramIs.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		lblThisProgramIs.setForeground(Color.WHITE);
		lblThisProgramIs.setBounds(120, 160, 480, 21);
		contentPane.add(lblThisProgramIs);
		
		JLabel lblEncryptDecrypt = new JLabel("encrypt / decrypt messages");
		lblEncryptDecrypt.setForeground(Color.WHITE);
		lblEncryptDecrypt.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		lblEncryptDecrypt.setBounds(190, 196, 340, 21);
		contentPane.add(lblEncryptDecrypt);
		
		JLabel lblMoreInformationAre = new JLabel("More informations are in the info-page");
		lblMoreInformationAre.setForeground(Color.WHITE);
		lblMoreInformationAre.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		lblMoreInformationAre.setBounds(110, 260, 500, 21);
		contentPane.add(lblMoreInformationAre);
		
		JButton btnInfo = new JButton("Info");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info info = new Info(0);
				info.setVisible(true);
			}
		});
		btnInfo.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnInfo.setBounds(285, 325, 150, 30);
		contentPane.add(btnInfo);
	}
}
