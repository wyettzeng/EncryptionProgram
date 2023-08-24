import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Encryption_Advance extends JFrame {
	int[] prime_numbers = new int[115];
	int[] low_prime_numbers = { 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211,
			223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337,
			347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461,
			463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601,
			607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739,
			743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829 };
	int[] high_prime_numbers = { 2039, 2053, 2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111, 2113, 2129, 2131, 2137,
			2141, 2143, 2153, 2161, 2179, 2203, 2207, 2213, 2221, 2237, 2239, 2243, 2251, 2267, 2269, 2273, 2281, 2287,
			2293, 2297, 2309, 2311, 2333, 2339, 2341, 2347, 2351, 2357, 2371, 2377, 2381, 2383, 2389, 2393, 2399, 2411,
			2417, 2423, 2437, 2441, 2447, 2459, 2467, 2473, 2477, 2503, 2521, 2531, 2539, 2543, 2549, 2551, 2557, 2579,
			2591, 2593, 2609, 2617, 2621, 2633, 2647, 2657, 2659, 2663, 2671, 2677, 2683, 2687, 2689, 2693, 2699, 2707,
			2711, 2713, 2719, 2729, 2731, 2741, 2749, 2753, 2767, 2777, 2789, 2791, 2797, 2801, 2803, 2819, 2833, 2837,
			2843, 2851, 2857, 2861, 2879, 2887, 2897, 2903, 2909, 2917, 2927 };
	String[] description = {
			"This algorithm provides medium level of security while demanding for medium-high processing power.",
			"This algorithm provides medium-low level of security while demanding for low processing power.",
			"This algorithm provides high level of security while demanding for high processing power.",
			"This algorithm provides extreme-high level of security while demanding for extrme-high processing power. Run encryption at your own risk." };
	String[] difficulties2 = { "Low", "High" };
	String[] encryption_method = { "Pro-Encryption", "Substitution", "Rivest, Shamir, and Adelman (RSA)" };
	String[] character = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "0", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "[", "{", "]", "}", ";",
			":", "'", ",", "<", ".", ">", "/", "?", "\"", "\\", "�", "�", "�", "�", "�", " ", "\t", "\r", "\n",
			"\r\n" };
	String[] subs_character = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
			"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "0", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "[", "{", "]", "}",
			";", ":", "'", ",", "<", ".", ">", "/", "?", "\"", "\\", "�", "�", "�", "�", "�" };
	String passcode_text = "";
	String message_text = "";
	int subs_pass = 0;
	int passcode_length = 0;
	int subs_lines;
	int[] passcode_int = new int[31];
	int[] message_int = new int[9100000];
	static double progress = 0;
	String[] encrypted_message_string = new String[9100000];
	BigInteger[] encrypted_message_big = new BigInteger[9100000];
	BigInteger ram = new BigInteger("0");
	boolean hidden_password = true;
	boolean help = true;

	JPanel contentPane;
	static Timer time = new Timer();
	JTextArea textArea = new JTextArea();
	JButton btnSubmit = new JButton("Encrypt\r\n");
	Component frame = null;
	File myFile;
	JFileChooser chooser = new JFileChooser();
	JTextField textField = new JTextField();
	JComboBox comboBox_difficulties2 = new JComboBox(difficulties2);
	JComboBox comboBox_method = new JComboBox(encryption_method);
	JCheckBox chckbxCap = new JCheckBox("Ignore Passcode Capitalization");
	JPasswordField passwordField;
	JTextField hint_lbl = new JTextField();
	JCheckBox chckbxPasscodeHint = new JCheckBox("Passcode Hint");
	// Loading load = new Loading();

	public void reset() {
		ram = new BigInteger("0");
		passcode_text = "";
		message_text = "";
		progress = 0;
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Encryption_Advance frame = new Encryption_Advance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Encryption_Advance() {
		setForeground(Color.DARK_GRAY);
		setBackground(Color.DARK_GRAY);
		textField.setBounds(184, 539, 1100, 25);
		textField.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		textField.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 15, 1440, 1000);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textArea.setBounds(1, 1, 1386, 419);
		textArea.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		contentPane.add(textArea);

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(15, 73, 1388, 421);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scroll);

		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setBounds(14, -46, 138, 21);
		lblMessage.setForeground(Color.WHITE);
		lblMessage.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		contentPane.add(lblMessage);

		JLabel lblPasscode = new JLabel("Passcode:");
		lblPasscode.setBounds(14, 541, 153, 21);
		lblPasscode.setForeground(Color.WHITE);
		lblPasscode.setFont(new Font("OCR A Extended", Font.BOLD, 24));
		contentPane.add(lblPasscode);
		btnSubmit.setBounds(875, 898, 150, 30);
		btnSubmit.setBackground(new Color(240, 255, 255));
		btnSubmit.setFont(new Font("OCR A Extended", Font.PLAIN, 24));

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
				if (hidden_password == true) {
					String pass_ram = new String(passwordField.getPassword());
					passcode_text = pass_ram;
				} else {
					passcode_text = textField.getText();
				}
				message_text = textArea.getText();
				if (chckbxCap.isSelected()) {
					passcode_text = passcode_text.toLowerCase();
				}
				System.out.println(passcode_text);
				System.out.println(message_text);
				if (passcode_text.length() == 0 || message_text.length() == 0) {
					JOptionPane.showMessageDialog(null, "Encryption failed, please type in message/passcode");
					// used to break out of this method
					return;
				}
				if (passcode_text.length() > 31) {
					JOptionPane.showMessageDialog(null,
							"Encryption failed, please type in a passcode that is within 30 characters \nPS: Spaces and special character also count as a character");

					// used to break out of this method
					return;
				}
				for (int i = 0; i < passcode_text.length(); i++) {
					for (int o = 0; o < character.length; o++) {
						if (passcode_text.substring(i, i + 1).equals("\t")
								|| passcode_text.substring(i, i + 1).equals("\n")
								|| passcode_text.substring(i, i + 1).equals("\r")
								|| passcode_text.substring(i, i + 1).equals("\r\n")) {
							JOptionPane.showMessageDialog(null,
									"Encryption Failed!! Unsupported characters exist in passcode");
							return;
						}
						if (passcode_text.substring(i, i + 1).equals(character[o])) {
							break;
						}
						if (o == character.length - 1) {
							JOptionPane.showMessageDialog(null,
									"Encryption Failed!! Unsupported characters exist in passcode: "
											+ passcode_text.substring(i, i + 1));
							return;
						}
					}
				}
				if (chckbxPasscodeHint.isSelected()) {
					for (int i = 0; i < hint_lbl.getText().length(); i++) {
						for (int o = 0; o < character.length; o++) {
							if (hint_lbl.getText().substring(i, i + 1).equals("\t")
									|| hint_lbl.getText().substring(i, i + 1).equals("\n")
									|| hint_lbl.getText().substring(i, i + 1).equals("\r")
									|| hint_lbl.getText().substring(i, i + 1).equals("\r\n")) {
								JOptionPane.showMessageDialog(null,
										"Encryption Failed!! Unsupported characters exist in passcode");
								return;
							}
							if (hint_lbl.getText().substring(i, i + 1).equals(character[o])) {
								break;
							}
							if (o == character.length - 1) {
								JOptionPane.showMessageDialog(null,
										"Encryption Failed!! Unsupported characters exist in passcode: "
												+ passcode_text.substring(i, i + 1));
								return;
							}
						}
					}
				}
				for (int i = 0; i < message_text.length(); i++) {
					for (int o = 0; o < character.length; o++) {
						if (message_text.substring(i, i + 1).equals(character[o])) {
							break;
						}
						if (o == character.length - 1) {
							JOptionPane.showMessageDialog(null,
									"Encryption Failed!! Unsupported characters exist in message: "
											+ message_text.substring(i, i + 1));
							return;
						}
					}
				}
				// Loading.main(null);

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				convert();
			}
		});

		JLabel lblLevelOfEncryption = new JLabel("Level of Encryption:");
		lblLevelOfEncryption.setBounds(779, 779, 339, 21);
		lblLevelOfEncryption.setForeground(Color.WHITE);
		lblLevelOfEncryption.setFont(new Font("OCR A Extended", Font.BOLD, 24));
		contentPane.add(lblLevelOfEncryption);
		contentPane.add(btnSubmit);

		contentPane.add(textField);
		comboBox_difficulties2.setBounds(1133, 777, 146, 27);
		comboBox_difficulties2.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		contentPane.add(comboBox_difficulties2);

		JLabel lblEncryptionMethod = new JLabel("Encryption Method:");
		lblEncryptionMethod.setBounds(14, 779, 302, 21);
		lblEncryptionMethod.setForeground(Color.WHITE);
		lblEncryptionMethod.setFont(new Font("OCR A Extended", Font.BOLD, 24));
		contentPane.add(lblEncryptionMethod);
		comboBox_method.setBounds(309, 776, 455, 27);
		comboBox_method.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		contentPane.add(comboBox_method);

		JButton btnChooseExportLocation = new JButton("Choose Export Location");
		btnChooseExportLocation.setBounds(484, 897, 376, 32);
		btnChooseExportLocation.setBackground(new Color(240, 255, 255));
		btnChooseExportLocation.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnChooseExportLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					myFile = chooser.getSelectedFile();
				}
			}
		});
		contentPane.add(btnChooseExportLocation);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(15, 15, 200, 29);
		btnMainMenu.setBackground(new Color(240, 255, 255));
		btnMainMenu.setForeground(new Color(0, 0, 0));
		btnMainMenu.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int choice = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit the encryption page? \nAny information will be lost", "Warning",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					Main_Menu menu = new Main_Menu();
					menu.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnMainMenu);
		chckbxCap.setBounds(10, 604, 487, 29);

		chckbxCap.setForeground(Color.WHITE);
		chckbxCap.setBackground(Color.DARK_GRAY);
		chckbxCap.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		contentPane.add(chckbxCap);

		passwordField = new JPasswordField();
		passwordField.setBounds(184, 539, 1100, 25);
		passwordField.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		contentPane.add(passwordField);

		JRadioButton rdbtnShowPasscode = new JRadioButton("Show Passcode");
		rdbtnShowPasscode.setBounds(678, 604, 247, 29);
		rdbtnShowPasscode.setBackground(Color.DARK_GRAY);
		rdbtnShowPasscode.setForeground(Color.WHITE);
		rdbtnShowPasscode.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		contentPane.add(rdbtnShowPasscode);
		chckbxPasscodeHint.setBounds(10, 677, 241, 29);
		chckbxPasscodeHint.setForeground(Color.WHITE);
		chckbxPasscodeHint.setBackground(Color.DARK_GRAY);
		chckbxPasscodeHint.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		contentPane.add(chckbxPasscodeHint);
		hint_lbl.setBounds(242, 679, 1038, 27);
		hint_lbl.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		contentPane.add(hint_lbl);
		hint_lbl.setColumns(10);

		JLabel hint6 = new JLabel("This is where you put your messages that you want encrypted");
		hint6.setBounds(14, 505, 670, 21);
		hint6.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		hint6.setForeground(new Color(250, 128, 114));
		contentPane.add(hint6);

		JLabel hint5 = new JLabel(
				"This is where you put your passcode, one can only decrypt this message if they have the exact same passcode");
		hint5.setBounds(14, 572, 1218, 21);
		hint5.setForeground(new Color(250, 128, 114));
		hint5.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		contentPane.add(hint5);

		JLabel hint3 = new JLabel("By selecting this, all passcodes will be made lower-case");
		hint3.setBounds(14, 643, 633, 21);
		hint3.setForeground(new Color(250, 128, 114));
		hint3.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		contentPane.add(hint3);

		JLabel hint4 = new JLabel("By selecting this, the passcode will be made visible");
		hint4.setBounds(678, 643, 602, 21);
		hint4.setForeground(new Color(250, 128, 114));
		hint4.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		contentPane.add(hint4);

		JTextArea hint2 = new JTextArea();
		hint2.setBounds(20, 717, 1260, 60);
		hint2.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		hint2.setBackground(Color.DARK_GRAY);
		hint2.setForeground(new Color(250, 128, 114));
		hint2.setLineWrap(true);
		hint2.setWrapStyleWord(true);
		hint2.setText(
				"By selecting this, you will be given the option of transferring non-encrypted messages to the other person. NOTE: EVERYTHING in this box will be non-encrypted. For more information please refer to the info page.");
		hint2.setFocusable(false);
		contentPane.add(hint2);

		JTextArea hint_method = new JTextArea();
		hint_method.setBounds(14, 809, 1260, 60);
		hint_method.setWrapStyleWord(true);
		hint_method.setText("Encryption description");
		hint_method.setLineWrap(true);
		hint_method.setForeground(new Color(250, 128, 114));
		hint_method.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		hint_method.setBackground(Color.DARK_GRAY);
		hint_method.setFocusable(false);
		contentPane.add(hint_method);

		JTextArea hint1 = new JTextArea();
		hint1.setFocusable(false);
		hint1.setBounds(14, 884, 455, 63);
		hint1.setWrapStyleWord(true);
		hint1.setText("This button allow you to choose the export location. More information will be in the info page");
		hint1.setLineWrap(true);
		hint1.setForeground(new Color(250, 128, 114));
		hint1.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		hint1.setBackground(Color.DARK_GRAY);
		contentPane.add(hint1);

		hint1.setVisible(false);
		hint2.setVisible(false);
		hint3.setVisible(false);
		hint4.setVisible(false);
		hint5.setVisible(false);
		hint6.setVisible(false);
		hint_method.setVisible(false);

		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(1250, 15, 153, 29);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (help == true) {
					hint1.setVisible(true);
					hint2.setVisible(true);
					hint3.setVisible(true);
					hint4.setVisible(true);
					hint5.setVisible(true);
					hint6.setVisible(true);
					hint_method.setVisible(true);
					help = false;
				} else {
					hint1.setVisible(false);
					hint2.setVisible(false);
					hint3.setVisible(false);
					hint4.setVisible(false);
					hint5.setVisible(false);
					hint6.setVisible(false);
					hint_method.setVisible(false);
					help = true;
				}
			}
		});
		btnHelp.setBackground(new Color(240, 255, 255));
		btnHelp.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		contentPane.add(btnHelp);

		JButton btnMoreInformaiton = new JButton("More Info");
		btnMoreInformaiton.setBounds(630, 15, 180, 29);
		btnMoreInformaiton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info info = new Info(1);
				info.setVisible(true);
			}
		});
		btnMoreInformaiton.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnMoreInformaiton.setBackground(new Color(240, 255, 255));
		contentPane.add(btnMoreInformaiton);

		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (chckbxPasscodeHint.isSelected()) {
					hint_lbl.setVisible(true);
				} else {
					hint_lbl.setVisible(false);
				}

				if (comboBox_method.getSelectedItem().equals("Substitution")) {
					hint_method.setText(description[1] + " See more information at info-page");
					comboBox_difficulties2.setVisible(false);
					lblLevelOfEncryption.setVisible(false);
				} else if (comboBox_method.getSelectedItem().equals("Pro-Encryption")) {
					if (comboBox_difficulties2.getSelectedItem().equals("Low")) {
						hint_method.setText(description[0] + " See more information at info-page");
					} else {
						hint_method.setText(description[3] + " See more information at info-page");
					}
					comboBox_difficulties2.setVisible(true);
					lblLevelOfEncryption.setVisible(true);
				} else if (comboBox_method.getSelectedItem().equals("Rivest, Shamir, and Adelman (RSA)")) {
					if (comboBox_difficulties2.getSelectedItem().equals("Low")) {
						hint_method.setText(description[2] + " See more information at info-page");
					} else {
						hint_method.setText(description[3] + " See more information at info-page");
					}
					comboBox_difficulties2.setVisible(true);
					lblLevelOfEncryption.setVisible(true);
				}

				if (rdbtnShowPasscode.isSelected()) {
					if (hidden_password == true) {
						String pass_ram = new String(passwordField.getPassword());
						textField.setText(pass_ram);
					}
					hidden_password = false;
					passwordField.setVisible(false);
					textField.setVisible(true);
				} else {
					if (hidden_password == false) {
						passwordField.setText(textField.getText());
					}
					hidden_password = true;
					passwordField.setVisible(true);
					textField.setVisible(false);
				}
			}
		}, 0, 50);
	}

	public void convert() {
		if (comboBox_method.getSelectedItem().equals("Pro-Encryption")) {
			for (int i = 0; i < passcode_text.length(); i++) {
				for (int o = 0; o < character.length; o++) {
					if (passcode_text.substring(i, (i + 1)).equals(character[o])) {
						passcode_int[i] = o + 1;
						break;
					}
				}
			}

			for (int i1 = 0; i1 < message_text.length(); i1++) {
				for (int z = 0; z < character.length; z++) {
					if (message_text.substring(i1, (i1 + 1)).equals(character[z])) {
						message_int[i1] = z + 1;
						break;
					}
				}
			}

			if (comboBox_difficulties2.getSelectedItem().equals("High")) {
				encrypt();
				write();
			} else if (comboBox_difficulties2.getSelectedItem().equals("Low")) {
				encrypt_easy();
				write();
			}
		} else if (comboBox_method.getSelectedItem().equals("Substitution")) {
			for (int i = 0; i < passcode_text.length(); i++) {
				for (int o = 0; o < character.length; o++) {
					if (passcode_text.substring(i, (i + 1)).equals(character[o])) {
						subs_pass = subs_pass + o;
						break;
					}
				}
			}
			subs_pass = subs_pass % subs_character.length;

			if (subs_pass == 0) {
				for (int i = 0; i < passcode_text.length(); i++) {
					if (passcode_text.substring(i, i + 1).equals("a")) {
						subs_pass = 17;
					} else {
						for (int o = 0; o < subs_character.length; o++) {
							if (passcode_text.substring(i, i + 1).equals(character[o])) {
								subs_pass = o;
								break;
							}
						}
					}
				}
			}

			System.out.println("sum of passcode: " + subs_pass);

			// 1001 = " ", 1002 = "\t", 1003 = "\r", 1004 = "\n", 1005 = "\r\n"
			// turning message into int
			for (int i = 0; i < message_text.length(); i++) {
				if (message_text.substring(i, (i + 1)).equals(" ")) {
					message_int[i] = 1001;
				}
				if (message_text.substring(i, (i + 1)).equals("\t")) {
					message_int[i] = 1002;
				}
				if (message_text.substring(i, (i + 1)).equals("\n")) {
					message_int[i] = 1004;
				}
				if (message_text.substring(i, (i + 1)).equals("\r")) {
					message_int[i] = 1003;
				}
				if (message_text.substring(i, (i + 1)).equals("\r\n")) {
					message_int[i] = 1005;
				} else {
					for (int o = 0; o < subs_character.length; o++) {
						if (message_text.substring(i, (i + 1)).equals(subs_character[o])) {
							message_int[i] = o;
							break;
						}
					}
				}
			}
			substitution_encryption();
			write();
		} else if (comboBox_method.getSelectedItem().equals("Rivest, Shamir, and Adelman (RSA)")) {

			if (comboBox_difficulties2.getSelectedItem().equals("Low")) {
				for (int i = 0; i < 115; i++) {
					prime_numbers[i] = low_prime_numbers[i];
				}
			} else if (comboBox_difficulties2.getSelectedItem().equals("High")) {
				for (int i = 0; i < 115; i++) {
					prime_numbers[i] = high_prime_numbers[i];
				}
			}

			passcode_length = passcode_text.length();
			System.out.println("passcode: " + passcode_text);
			System.out.println("message: " + message_text);

			for (int i = 0; i < passcode_text.length(); i++) {
				for (int o = 0; o < character.length; o++) {
					if (passcode_text.substring(i, (i + 1)).equals(character[o])) {
						passcode_int[i] = prime_numbers[o];
						break;
					}
				}
			}

			// checking if there are 2 prime numbers that are the same
			for (int i = 0; i < passcode_text.length() - 2; i++) {
				if (passcode_int[i] == passcode_int[i + 2]) {
					System.out.println(passcode_int[i]);
					System.out.println(passcode_int[i + 2]);
					if (passcode_int[i] != 0) {
						for (int z = i + 2; z < passcode_text.length(); z++) {
							passcode_int[z] = passcode_int[z + 1];
						}
						i--;
						passcode_length--;
					}
				}
			}

			// Converting message into int value
			for (int i = 0; i < message_text.length(); i++) {
				for (int z = 0; z < character.length; z++) {
					if (message_text.substring(i, (i + 1)).equals(character[z])) {
						message_int[i] = (z + 1);
						break;
					}
				}
			}
			rsa_encrypt();
			write();
		}
	}

	public void encrypt_easy() {
		// 0: Add, 1: Multiple, 2:subtract
		int count = 0;
		for (int i = 0; i < message_text.length(); i++) {
			double time_ram = message_text.length();
			double time_ram2 = i;
			progress = time_ram2 / time_ram * 100;

			ram = BigInteger.valueOf(message_int[i]);
			System.out.println("original: " + ram);
			count = 0;
			for (int o = 0; o < passcode_text.length(); o++) {
				if (count == 0) {
					count = 1;
					ram = ram.add(BigInteger.valueOf(passcode_int[o]));
					System.out.println("add: " + BigInteger.valueOf(passcode_int[o]) + "=" + ram);
				} else if (count == 1) {
					count = 2;
					ram = ram.multiply(BigInteger.valueOf(passcode_int[o]));
					System.out.println("multiply: " + BigInteger.valueOf(passcode_int[o]) + "=" + ram);
				} else if (count == 2) {
					count = 0;
					ram = ram.subtract(BigInteger.valueOf(passcode_int[o]));
					System.out.println("subtract: " + BigInteger.valueOf(passcode_int[o]) + "=" + ram);
				}
				if (o == (passcode_text.length() - 1)) {
					encrypted_message_big[i] = ram.pow(3);
					System.out.println("Final: " + ram);
				}
			}
			System.out.println("");
		}
	}

	public void encrypt() {
		// 0: Add, 1: Multiple, 2: sqaured, 3:subtract
		int count = 0;
		System.out.println("A new word:");
		for (int i = 0; i < message_text.length(); i++) {

			double time_ram = message_text.length();
			double time_ram2 = i;
			progress = time_ram2 / time_ram * 100;

			ram = BigInteger.valueOf(message_int[i]);
			System.out.println("original: " + ram);
			count = 0;
			for (int o = 0; o < passcode_text.length(); o++) {
				if (count == 0) {
					count = 1;
					ram = ram.add(BigInteger.valueOf(passcode_int[o]));
					System.out.println("add" + BigInteger.valueOf(passcode_int[o]) + " = " + ram);
				} else if (count == 1) {
					count = 2;
					ram = ram.multiply(BigInteger.valueOf(passcode_int[o]));
					System.out.println("multiply " + BigInteger.valueOf(passcode_int[o]) + " = " + ram);
				} else if (count == 2) {
					count = 3;
					if (ram.compareTo(BigInteger.valueOf(0)) == 1) {
						ram = ram.pow(passcode_int[o]);
					}
					System.out.println("powered " + BigInteger.valueOf(passcode_int[o]) + " = " + ram);
				} else if (count == 3) {
					count = 0;
					ram = ram.subtract(BigInteger.valueOf(passcode_int[o]));
					System.out.println("subtract " + BigInteger.valueOf(passcode_int[o]) + " = " + ram);
				}
				if (o == (passcode_text.length() - 1)) {
					System.out.println("end: " + ram);
					System.out.println("");
					System.out.println("");
					encrypted_message_big[i] = ram;
				}
			}
		}
		/*
		 * for (int i = 0; i < message_text.length(); i++) {
		 * System.out.println(encrypted_message_big[i]); }
		 */
	}

	public void substitution_encryption() {
		for (int i = 0; i < subs_character.length; i++) {
			if (i < subs_pass) {
				subs_character[i] = character[(i + subs_character.length - subs_pass)];
			} else {
				subs_character[i] = character[(i - subs_pass)];
			}
		}

		// String ram = "";
		// for (int i = 0; i < subs_character.length; i++) {
		// ram = ram + subs_character[i];
		// }
		// System.out.println("characters: " + ram);

		// Adding the sum of the password to the value of the letter and convert
		// 1001 = " ", 1002 = "tab", 1003 = "\r", 1004 = "\n", 1005 = "\r\n"
		for (int i = 0; i < message_text.length(); i++) {
			encrypted_message_string[i] = "";
		}

		subs_lines = 0;
		
		for (int i = 0; i < message_text.length(); i++) {
			//double time_ram = message_text.length();
			//double time_ram2 = i;
			//progress = time_ram2 / time_ram * 100;

			if (message_int[i] == 1001) {
				System.out.println(i + ": " + message_int[i]);
				System.out.println("line " + subs_lines);
				encrypted_message_string[subs_lines] = encrypted_message_string[subs_lines] + " ";
			} else if (message_int[i] == 1002) {
				System.out.println(i + ": " + message_int[i]);
				System.out.println("line " + subs_lines);
				encrypted_message_string[subs_lines] = encrypted_message_string[subs_lines] + "\t";
			} else if (message_int[i] == 1003 || message_int[i] == 1004 || message_int[i] == 1005) {
				System.out.println(i + ": " + message_int[i]);
				System.out.println("line " + subs_lines);
				subs_lines++;
			} else {
				System.out.println(i + ": " + message_int[i]);
				System.out.println("line " + subs_lines);
				encrypted_message_string[subs_lines] = encrypted_message_string[subs_lines]
						+ subs_character[message_int[i]];
			}
		}
	}

	public void rsa_encrypt() {
		/*
		 * try { File outFile = new File("Encryption record.txt"); FileOutputStream
		 * outFileStream = new FileOutputStream(outFile); PrintWriter outStream = new
		 * PrintWriter(outFileStream);
		 */
		int encryption_key = 0;
		// calculate the product of the passcode
		int product = 0;
		// This is for the operation, because it goes over the max of int
		String name_ram, name_ram2 = "";
		BigInteger ram, ram1;
		for (int i = 0; i < message_text.length(); i++) {
			double time_ram = message_text.length();
			double time_ram2 = i;
			progress = time_ram2 / time_ram * 100;

			name_ram2 = "";
			name_ram = "";
			ram1 = BigInteger.valueOf(message_int[i]);
			System.out.println("original message: " + ram1);
			if ((i % passcode_length) > 0 && (i % passcode_length) != (passcode_length - 1)) {
				encryption_key = encrypt_key(passcode_int[((i % passcode_length) - 1)],
						passcode_int[((i % passcode_length) + 1)]);
				product = passcode_int[((i % passcode_length) - 1)] * passcode_int[((i % passcode_length) + 1)];
				System.out.println("not min or max");
				System.out.println("p: " + passcode_int[((i % passcode_length) - 1)] + "q: "
						+ passcode_int[((i % passcode_length) + 1)]);
			} else if ((i % passcode_length) == 0) {
				encryption_key = encrypt_key(prime_numbers[110], passcode_int[((i % passcode_length) + 1)]);
				product = prime_numbers[110] * passcode_int[((i % passcode_length) + 1)];
				System.out.println("min passcode");
				System.out.println("p: " + prime_numbers[110] + "q: " + passcode_int[((i % passcode_length) + 1)]);
			} else if ((i % passcode_length) == (passcode_length - 1)) {
				encryption_key = encrypt_key(passcode_int[((i % passcode_length) - 1)], prime_numbers[110]);
				product = passcode_int[((i % passcode_length) - 1)] * prime_numbers[110];
				System.out.println("max passcode");
				System.out.println("p: " + passcode_int[((i % passcode_length) - 1)] + "q: " + prime_numbers[110]);
			}
			System.out.println("product: " + product);
			System.out.println("encrypt key: " + encryption_key);
			ram = ram1.pow(encryption_key);
			System.out.println("original message powered");
			ram = ram.mod(BigInteger.valueOf(product));
			System.out.println("original message modulued: " + ram);
			System.out.println("");
			// turning the stored value to characters
			name_ram = ram.toString();
			for (int o = 0; o < name_ram.length(); o++) {
				name_ram2 = name_ram2 + character[Integer.parseInt(name_ram.substring(o, (o + 1)))];
			}
			encrypted_message_string[i] = name_ram2;
		}
		// outStream.close();
		// } catch (IOException e) {

		// }
	}

	public void write() {
		// load.dispose();
		try {
			FileOutputStream outFileStream;
			PrintWriter outStream;
			String file_name = "Encrypted_data";
			if (myFile == null) {
				file_name = JOptionPane.showInputDialog("What would be your file name?");
				// Set a name in case the user didn't type anything
				if (file_name.equals("")) {
					file_name = "Encrypted_data";
				}
				outFileStream = new FileOutputStream(new File(
						FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + "/" + file_name));
				outStream = new PrintWriter(outFileStream);
			} else {
				outFileStream = new FileOutputStream(myFile);
				outStream = new PrintWriter(outFileStream);
			}
			// inFile associated correctly

			// First line indicates encryption method
			// 999431 = High, 000431 = Low, JC3 = Substitution, RivestAdelmanShamir0124 =
			// RSA low, RivestAdelmanShamir7548 = RSA high

			// Second Line Indicates whether the ignoring capital is on
			// IGC = ignore case, NOIGC = case matters

			// Followed by the encrypted data
			// End file with eof

			if (comboBox_method.getSelectedItem().equals("Pro-Encryption")) {
				if (comboBox_difficulties2.getSelectedItem().equals("High")) {
					outStream.println("999431");
				} else {
					outStream.println("000431");
				}
				if (chckbxCap.isSelected()) {
					outStream.println("IGC");
				} else {
					outStream.println("NOIGC");
				}
				if (chckbxPasscodeHint.isSelected()) {
					outStream.println(hint_lbl.getText());
				} else {
					outStream.println("");
				}

				for (int i = 0; i < message_text.length(); i++) {
					outStream.println(encrypted_message_big[i]);
				}
				outStream.println("eof");
			} else if (comboBox_method.getSelectedItem().equals("Substitution")) {
				outStream.println("JC3");
				if (chckbxCap.isSelected()) {
					outStream.println("IGC");
				} else {
					outStream.println("NOIGC");
				}
				if (chckbxPasscodeHint.isSelected()) {
					outStream.println(hint_lbl.getText());
				} else {
					outStream.println("");
				}
				for (int i = 0; i <= subs_lines; i++) {
					outStream.println(encrypted_message_string[i]);
				}

				outStream.println("eof");
			} else if (comboBox_method.getSelectedItem().equals("Rivest, Shamir, and Adelman (RSA)")) {
				if (comboBox_difficulties2.getSelectedItem().equals("Low")) {
					outStream.println("RivestAdelmanShamir0124");
				} else {
					outStream.println("RivestAdelmanShamir7458");
				}
				if (chckbxCap.isSelected()) {
					outStream.println("IGC");
				} else {
					outStream.println("NOIGC");
				}
				if (chckbxPasscodeHint.isSelected()) {
					outStream.println(hint_lbl.getText());
				} else {
					outStream.println("");
				}
				for (int i = 0; i < message_text.length(); i++) {
					outStream.println(encrypted_message_string[i]);
				}
				outStream.println("eof");
			}
			outStream.close();
			// inFile not associated correctly
			if (myFile == null) {
				JOptionPane.showMessageDialog(null,
						"Encryption Done! \nYour file " + file_name + " has been saved to your desktop");
			} else {
				JOptionPane.showMessageDialog(null, "Encryption Done!");
			}
		} catch (IOException e) {
			System.out.println("Output error");
		}
	}

	public int encrypt_key(int q, int p) {
		System.out.println("P: " + p + " q: " + q);
		int phi = ((q - 1) * (p - 1));
		// getting the encrypt key
		for (int i = 2; i < phi; i++) {
			if (isCoprime(i, (q * p)) == true) {
				if (isCoprime(i, phi) == true) {
					return i;
				}
			}
		}
		JOptionPane.showMessageDialog(null,
				"Good Job!!! You break the System \nContact Wyett or Mr. Castaneda for an update \nWyett's email: wyettzeng@gamil.com");
		System.exit(0);
		return 0;
	}

	// thanks to Euclid
	public boolean isCoprime(int u, int v) {
		// If both numbers are even, then they are not coprime.
		if (((u | v) & 1) == 0)
			return false;

		// Now at least one number is odd. Eliminate all the factors of 2 from u.
		while ((u & 1) == 0)
			u >>= 1;

		do {
			// Eliminate all the factors of 2 from v, because we know that u and v do not
			// have any 2's in common.
			while ((v & 1) == 0)
				v >>= 1;

			// One is coprime with everything else by definition.
			if (v == 1)
				return true;

			// Swap if necessary to ensure that v >= u.
			if (u > v) {
				long t = v;
				v = u;
				u = (int) t;
			}

			// We know that GCD(u, v) = GCD(u, v - u).
			v -= u;
		} while (v != 0);

		// When we reach here, we have v = 0 and GCD(u, v) = current value of u, which
		// is greater than 1.
		return false;
	}
}
