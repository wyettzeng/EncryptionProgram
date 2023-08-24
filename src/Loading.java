import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Loading extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loading frame = new Loading();
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
	public Loading() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(Loading.class.getResource("/img/loading.gif")));
		icon.setBounds(130, 15, 340, 340);
		contentPane.add(icon);
		
		JLabel lblTheProgramIs = new JLabel("The program is processing data");
		lblTheProgramIs.setForeground(Color.WHITE);
		lblTheProgramIs.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		lblTheProgramIs.setBounds(100, 365, 400, 21);
		contentPane.add(lblTheProgramIs);
		
		JLabel lblYouMayChoose = new JLabel("You may choose to exit quit the whole\r\n");
		lblYouMayChoose.setForeground(Color.WHITE);
		lblYouMayChoose.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		lblYouMayChoose.setBounds(50, 400, 500, 21);
		contentPane.add(lblYouMayChoose);
		
		JLabel lblProgramByClicking = new JLabel("program by clicking the button");
		lblProgramByClicking.setForeground(Color.WHITE);
		lblProgramByClicking.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		lblProgramByClicking.setBounds(100, 435, 400, 21);
		contentPane.add(lblProgramByClicking);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit the program? \nAny information will be lost", "Warning",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnExit.setBounds(238, 485, 124, 30);
		contentPane.add(btnExit);
	}
}
