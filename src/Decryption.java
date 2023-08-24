import java.awt.Component;
import java.awt.EventQueue;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class Decryption extends JFrame {
	String[] difficulties = { "Low", "High" };
	String[] encryption_method = { "Pro-Encryption", "Substitution", "Rivest, Shamir, and Adelman (RSA)" };
	String[] character = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "0", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "[", "{", "]", "}", ";",
			":", "'", ",", "<", ".", ">", "/", "?", "\"", "\\", "“", "”", "—", "’", "‘", " ", "\t", "\r", "\n",
			"\r\n" };
	String[] subs_character = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
			"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "0", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "[", "{", "]", "}",
			";", ":", "'", ",", "<", ".", ">", "/", "?", "\"", "\\", "“", "”", "—", "’", "‘" };
	String[] message_string = new String[9100000];
	String passcode_text = "";
	String message_text = "";
	String hint_text = "";

	int[] prime_numbers = new int[120];
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
	int[] passcode_int = new int[31];
	int[] message_int = new int[9100000];
	int subs_pass = 0;
	int method = 100;
	int wordcount = 0;
	int count = 0;
	int passcode_length = 0;

	BigInteger[] message_big = new BigInteger[9100000];
	BigInteger ram = new BigInteger("0");

	boolean ignore_case;
	boolean hidden_password = true;
	boolean help = false;
	boolean hard_to_decrypt = false;

	Timer time = new Timer();
	JPanel contentPane;
	JTextField textField;
	JButton btnDecrypt;
	JTextArea textArea = new JTextArea();
	JFileChooser chooser = new JFileChooser();
	Component frame = null;
	File myFile;
	private JPasswordField passwordField;
	JTextArea user_info = new JTextArea();
	JScrollPane info_scroll = new JScrollPane(user_info);
	// Loading load = new Loading();

	void reset() {
		subs_pass = 0;
		passcode_text = "";
		message_text = "";
		textArea.setText("");
	}

	private BigInteger BigInteger(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Decryption frame = new Decryption();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Decryption() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 15, 1440, 1000);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPasscode = new JLabel("PassCode:");
		lblPasscode.setForeground(Color.WHITE);
		lblPasscode.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		lblPasscode.setBounds(15, 60, 149, 21);
		contentPane.add(lblPasscode);

		textField = new JTextField();
		textField.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		textField.setBounds(140, 55, 1000, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setForeground(Color.WHITE);
		lblMessage.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		lblMessage.setBounds(15, 327, 128, 21);
		contentPane.add(lblMessage);

		textArea.setBackground(Color.GRAY);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("OCR A Extended", Font.PLAIN, 21));

		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(15, 116, 635, 369);

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(15, 363, 1404, 540);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scroll);

		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				if (hard_to_decrypt == true) {
					int choice = JOptionPane.showConfirmDialog(null,
							"This message is encrypted in such way that it requires very high process power to run "
									+ "\nProceed at your own rist\nYou you want to proceed? \nMore information are available in the help page",
							"Warning", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.NO_OPTION) {
						return;
					}
				}

				if (hidden_password == true) {
					String pass_ram = new String(passwordField.getPassword());
					passcode_text = pass_ram;
				} else {
					passcode_text = textField.getText();
				}

				if (ignore_case == true) {
					passcode_text = passcode_text.toLowerCase();
				}
				System.out.println(passcode_text);
				System.out.println(message_text);
				if (passcode_text.length() == 0) {
					JOptionPane.showMessageDialog(null, "Decryption failed, please type in message/passcode");
					// used to break out of this method
					return;
				}
				if (passcode_text.length() > 30) {
					JOptionPane.showMessageDialog(null,
							"Decryption failed, please type in a passcode that is within 30 characters \nPS: Spaces and special character also count as a character");
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
				if (myFile == null) {
					JOptionPane.showMessageDialog(null, "Please import a file!!!");
					return;
				}
				// load.setVisible(true);
				convert();
			}
		});
		btnDecrypt.setBounds(645, 915, 150, 30);
		contentPane.add(btnDecrypt);

		JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					myFile = chooser.getSelectedFile();
				}
				read();
			}
		});
		btnImport.setBounds(755, 135, 150, 29);
		contentPane.add(btnImport);

		JButton btnMoreInformaiton = new JButton("More Info");
		btnMoreInformaiton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info info = new Info(2);
				info.setVisible(true);
			}
		});
		btnMoreInformaiton.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnMoreInformaiton.setBounds(630, 15, 180, 29);
		contentPane.add(btnMoreInformaiton);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit the decryption page? \nAny information will be lost", "Warning",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					Main_Menu menu = new Main_Menu();
					menu.setVisible(true);
					dispose();
				}
			}
		});
		btnMainMenu.setBounds(15, 15, 180, 29);
		contentPane.add(btnMainMenu);

		JRadioButton rdbtnShowPassword = new JRadioButton("Show Passcode");
		rdbtnShowPassword.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		rdbtnShowPassword.setBackground(Color.DARK_GRAY);
		rdbtnShowPassword.setForeground(Color.WHITE);
		rdbtnShowPassword.setBounds(7, 135, 233, 29);
		contentPane.add(rdbtnShowPassword);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		passwordField.setBounds(140, 55, 1000, 30);
		contentPane.add(passwordField);

		JLabel hint2 = new JLabel(
				"Passcode is crucial for decryption, wrong passcode will result in wrong message (most likely unreadable)");
		hint2.setForeground(new Color(250, 128, 114));
		hint2.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		hint2.setBounds(15, 96, 1404, 21);
		contentPane.add(hint2);

		JLabel hint1 = new JLabel("By selecting this, the passcode will be made visible");
		hint1.setForeground(new Color(250, 128, 114));
		hint1.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		hint1.setBounds(15, 177, 721, 21);
		contentPane.add(hint1);

		JLabel hint3 = new JLabel("Import the encrypted file");
		hint3.setForeground(new Color(250, 128, 114));
		hint3.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		hint3.setBounds(755, 177, 342, 21);
		contentPane.add(hint3);

		user_info.setWrapStyleWord(true);
		user_info.setLineWrap(true);
		user_info.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		user_info.setBackground(Color.DARK_GRAY);
		user_info.setEditable(false);
		user_info.setForeground(Color.WHITE);
		user_info.setBounds(15, 213, 1404, 99);
		user_info.setVisible(false);
		user_info.setFocusable(false);
		contentPane.add(user_info);

		info_scroll.setVisible(false);
		info_scroll.setBackground(Color.DARK_GRAY);
		info_scroll.setBounds(15, 213, 1404, 99);
		info_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(info_scroll);

		JLabel hint4 = new JLabel("This is where the decrypted message will be displayed");
		hint4.setForeground(new Color(250, 128, 114));
		hint4.setFont(new Font("OCR A Extended", Font.PLAIN, 21));
		hint4.setBounds(716, 327, 703, 21);
		contentPane.add(hint4);

		hint1.setVisible(false);
		hint2.setVisible(false);
		hint3.setVisible(false);
		hint4.setVisible(false);

		JButton btnHelo = new JButton("Help");
		btnHelo.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		btnHelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (help == false) {
					hint1.setVisible(true);
					hint2.setVisible(true);
					hint3.setVisible(true);
					hint4.setVisible(true);
					help = true;
				} else {
					hint1.setVisible(false);
					hint2.setVisible(false);
					hint3.setVisible(false);
					hint4.setVisible(false);
					help = false;
				}
			}
		});
		btnHelo.setBounds(1245, 15, 180, 29);
		contentPane.add(btnHelo);

		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (rdbtnShowPassword.isSelected()) {
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

	public void read() {
		// 999431 = High, 000431 = Low, JC3 = Substitution, RivestAdelmanShamir0124 =
		// RSA low, RivestAdelmanShamir7548 = RSA high
		// method 1 = low, 2 = high, 3 = subs, 4 = rsa
		try {
			String str = "";
			File inFile = myFile;
			FileReader fileReader = new FileReader(inFile);
			BufferedReader bufReader = new BufferedReader(fileReader);
			str = bufReader.readLine();
			if (str.equals("000431")) {
				hard_to_decrypt = false;
				method = 1;
			} else if (str.equals("999431")) {
				hard_to_decrypt = true;
				method = 2;
			} else if (str.equals("JC3")) {
				hard_to_decrypt = false;
				method = 3;
			} else if (str.equals("RivestAdelmanShamir0124") || str.equals("RivestAdelmanShamir1688")
					|| str.equals("RivestAdelmanShamir7458")) {
				method = 4;
				if (str.equals("RivestAdelmanShamir0124")) {
					hard_to_decrypt = false;
					prime_numbers = low_prime_numbers;
				} else {
					hard_to_decrypt = true;
					prime_numbers = high_prime_numbers;
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Please import a file that has been encrypted by the same program! \nOther Files are not supported!");
				bufReader.close();
				return;
			}
			str = bufReader.readLine();
			if (str.equals("IGC")) {
				ignore_case = true;
			} else {
				ignore_case = false;
			}

			str = bufReader.readLine();
			hint_text = str;

			if (ignore_case == false) {
				info_scroll.setVisible(true);
				user_info.setVisible(true);
				if (hint_text.equals("")) {
					user_info.setText(
							"The one who encrypted this data choose to distinguish CAPITAL and non-capital letter, make sure you are careful when you are typing the passcode");
				} else {
					user_info.setText(
							"The one who encrypted this data choose to distinguish CAPITAL and non-capital letter, make sure you are careful when you are typing the passcode "
									+ "\nThe hint left by the person who encrypted this message:\n" + hint_text);
				}
			} else {
				if (hint_text.equals("")) {
					info_scroll.setVisible(true);
					user_info.setVisible(true);
					user_info.setText("The one who encrypted this data choose ingnore-case the passcode");
				} else {
					user_info.setVisible(true);
					info_scroll.setVisible(true);
					user_info.setText("The one who encrypted this data choose ingnore-case the passcode"
							+ "\nThe hint left by the person who encrypted this message:\n" + hint_text);
				}
			}

			if (method == 1 || method == 2) {
				for (int i = 0; i < 9100001; i++) {
					str = bufReader.readLine();
					if (str.equals("eof")) {
						wordcount = i;
						break;
					}
					message_big[i] = new BigInteger(str);
				}
			} else if (method == 3) {
				for (int i = 0; i < 9100001; i++) {
					message_string[i] = "";
					str = bufReader.readLine();
					if (str.equals("eof")) {
						wordcount = i;
						break;
					} else {
						message_string[i] = str;
					}
				}
			} else if (method == 4) {
				for (int i = 0; i < 9100001; i++) {
					str = bufReader.readLine();
					if (str.equals("eof")) {
						wordcount = i;
						break;
					}
					message_string[i] = str;
				}
			}
			bufReader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File Error!");
		}
	}

	public void convert() {
		count = 0;
		if (method == 1 || method == 2) {
			for (int z = passcode_text.length(); z > 0; z--) {
				for (int o = 0; o < character.length; o++) {
					if (passcode_text.substring((z - 1), z).equals(character[o])) {
						passcode_int[count] = o + 1;
						count++;
						break;
					}
				}
			}

			if (method == 1) {
				decrypt_easy();
			} else {
				decrypt();
			}
		}

		if (method == 3) {
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
			substitution_decryption();
		}

		else if (method == 4) {
			// Converting passcode into int value
			passcode_length = passcode_text.length();
			System.out.println("passcode: " + passcode_text);
			System.out.println("message: " + message_text);
			for (int i = 0; i < passcode_text.length(); i++) {
				for (int o = 0; o < character.length; o++) {
					if (passcode_text.substring(i, (i + 1)).equals(character[o])) {
						passcode_int[i] = prime_numbers[o];
						count++;
						break;
					}
				}
			}

			for (int i = 0; i < passcode_text.length(); i++) {
				System.out.println(i + ": " + passcode_int[i]);
			}

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
			// Converting encrypted message into int value
			// Converting encrypted message into int value
			String ram = "";
			for (int y = 0; y < wordcount; y++) {
				ram = "";
				for (int i = 0; i < message_string[y].length(); i++) {
					for (int z = 0; z < character.length; z++) {
						if (message_string[y].substring(i, (i + 1)).equals(character[z])) {
							ram = ram + z;
							break;
						}
					}
				}
				message_int[y] = Integer.parseInt(ram);
			}
			rsa_decrypt();
		}
	}

	public void decrypt() {
		// 0: Add, 1: Multiple, 2: sqaured, 3:subtract
		int mod = 0;
		for (int i = 0; i < wordcount; i++) {

			ram = message_big[i];
			count = 0;
			mod = passcode_text.length() % 4;
			// System.out.println("mod " + mod);
			if (mod == 1) {
				count = 4;
			} else if (mod == 2) {
				count = 3;
			} else if (mod == 3) {
				count = 2;
			} else if (mod == 0) {
				count = 1;
			}
			// System.out.println("count" + count);
			System.out.println("original: " + ram);
			for (int o = 0; o < passcode_text.length(); o++) {
				if (count == 1) {
					count = 2;
					ram = ram.add(BigInteger.valueOf(passcode_int[o]));
					System.out.println("add " + BigInteger.valueOf(passcode_int[o]) + " = " + ram);
				} else if (count == 2) {
					count = 3;
					if (ram.compareTo(BigInteger.valueOf(0)) == 1) {
						ram = NthRootfunc(ram, passcode_int[o]);
					}
					System.out.println("root " + BigInteger.valueOf(passcode_int[o]) + " = " + ram);
				} else if (count == 3) {
					count = 4;
					ram = ram.divide(BigInteger.valueOf(passcode_int[o]));
					System.out.println("divide" + BigInteger.valueOf(passcode_int[o]) + " = " + ram);
				} else if (count == 4) {
					count = 1;
					ram = ram.subtract(BigInteger.valueOf(passcode_int[o]));
					System.out.println("subtract" + BigInteger.valueOf(passcode_int[o]) + " = " + ram);
				}
				if (o == (passcode_text.length() - 1)) {
					System.out.println("end: " + ram);
					System.out.println("");
					System.out.println("");
					message_int[i] = ram.intValue();
				}
			}
		}
		translate(0);
	}

	public void decrypt_easy() {
		System.out.println("passcode: " + passcode_text);
		// 0: Add, 1: Multiple, 3:subtract
		int mod = 0;
		for (int i = 0; i < wordcount; i++) {
			ram = message_big[i];

			System.out.println("original: " + ram);
			if (ram.compareTo(BigInteger.valueOf(0)) != 0) {
				ram = NthRootfunc(ram, 3);
			}

			count = 0;
			mod = passcode_text.length() % 3;
			// System.out.println("mod " + mod);
			if (mod == 0) {
				count = 1;
			} else if (mod == 1) {
				count = 3;
			} else if (mod == 2) {
				count = 2;
			}
			// System.out.println("count" + count);

			for (int o = 0; o < passcode_text.length(); o++) {
				if (count == 1) {
					count = 2;
					ram = ram.add(BigInteger.valueOf(passcode_int[o]));
					System.out.println("add " + BigInteger.valueOf(passcode_int[o]) + " = " + ram);
				} else if (count == 2) {
					count = 3;
					ram = ram.divide(BigInteger.valueOf(passcode_int[o]));
					System.out.println("divide" + BigInteger.valueOf(passcode_int[o]) + " = " + ram);
				} else if (count == 3) {
					count = 1;
					ram = ram.subtract(BigInteger.valueOf(passcode_int[o]));
					System.out.println("subtract" + BigInteger.valueOf(passcode_int[o]) + " = " + ram);
				}
				if (o == (passcode_text.length() - 1)) {
					message_int[i] = ram.intValue();
				}
			}
			System.out.println("");
		}
		translate(0);
	}

	public void substitution_decryption() {
		// modifying the subs_character
		for (int i = 0; i < subs_character.length; i++) {
			if (i < subs_pass) {
				subs_character[i] = character[(i + subs_character.length - subs_pass)];
			} else {
				subs_character[i] = character[(i - subs_pass)];
			}
		}

		// 1001 = " ", 1002 = "tab", 1003 = "\n"
		// NOT DONE!! DECRYPTING
		int subs_count = 0;
		for (int i = 0; i < wordcount; i++) {
			if (message_string[i].equals("")) {
				message_int[subs_count] = 1003;
				subs_count++;
			}
			for (int z = 0; z < message_string[i].length(); z++) {
				if (message_string[i].substring(z, z + 1).equals(" ")) {
					message_int[subs_count] = 1001;
					subs_count++;
				} else if (message_string[i].substring(z, z + 1).equals("\t")) {
					message_int[subs_count] = 1002;
					subs_count++;
				} else {
					for (int o = 0; o < subs_character.length; o++) {
						if (message_string[i].substring(z, z + 1).equals(subs_character[o])) {
							message_int[subs_count] = o;
							subs_count++;
							break;
						}
					}
				}
				if (z == message_string[i].length() - 1) {
					message_int[subs_count] = 1003;
					subs_count++;
				}
			}
		}

		translate(subs_count);

	}

	public void rsa_decrypt() {
		/*
		 * try { File outFile = new File("Decryption Record.txt"); FileOutputStream
		 * outFileStream = new FileOutputStream(outFile); PrintWriter outStream = new
		 * PrintWriter(outFileStream);
		 */

		// RAM for encrypt key
		int decrypt_key = 0;
		// calculate the product of the passcode
		int product = 0;
		// This is for the operation, because it goes over the max of int
		BigInteger ram, ram1;
		for (int i = 0; i < wordcount; i++) {

			ram1 = BigInteger.valueOf(message_int[i]);
			System.out.println("original message: " + ram1);
			if ((i % passcode_length) > 0 && (i % passcode_length) != (passcode_length - 1)) {
				System.out.println("not min or max");
				System.out.println("p: " + passcode_int[((i % passcode_length) - 1)] + "q: "
						+ passcode_int[((i % passcode_length) + 1)]);
				decrypt_key = decrypt_key(passcode_int[((i % passcode_length) - 1)],
						passcode_int[((i % passcode_length) + 1)]);
				product = passcode_int[((i % passcode_length) - 1)] * passcode_int[((i % passcode_length) + 1)];
			} else if ((i % passcode_length) == 0) {
				decrypt_key = decrypt_key(prime_numbers[110], passcode_int[((i % passcode_length) + 1)]);
				product = prime_numbers[110] * passcode_int[((i % passcode_length) + 1)];
				System.out.println("min passcode");
				System.out.println("p: " + prime_numbers[110] + "q: " + passcode_int[((i % passcode_length) + 1)]);

			} else if ((i % passcode_length) == (passcode_length - 1)) {
				decrypt_key = decrypt_key(passcode_int[((i % passcode_length) - 1)], prime_numbers[110]);
				product = passcode_int[((i % passcode_length) - 1)] * prime_numbers[110];
				System.out.println("max passcode");
				System.out.println("p: " + passcode_int[((i % passcode_length) - 1)] + "q: " + prime_numbers[110]);
			}
			System.out.println("product: " + product);
			System.out.println("decrypt key: " + decrypt_key);
			ram = ram1.pow(decrypt_key);
			System.out.println("original message powered");
			ram = ram.mod(BigInteger.valueOf(product));
			System.out.println("original message modulued: " + ram);
			System.out.println("ram: " + ram);
			message_int[i] = (ram.intValue() - 1);
			System.out.println("");
			// message_text = message_text + message_int[i] + " ";
		}
		translate(0);
		/*
		 * outStream.close(); } catch (IOException e) {
		 *
		 * }
		 */
	}

	public void translate(int subs_num) {
		if (method == 1 || method == 2) {
			boolean wrong_passcode = false;
			for (int i = 0; i < wordcount; i++) {
				// this is just checking if the passcode is wrong
				if (message_int[i] > character.length || message_int[i] <= 0) {
					message_int[i] = Math.abs(message_int[i] % character.length);
					wrong_passcode = true;
				}
				// this line is the one that actually translate
				message_text = message_text + character[Math.abs((message_int[i] - 1))];
			}

			if (wrong_passcode == true) {
				message_text = message_text
						+ "\n\n\nYou PassCode is probably wrong, but the program decrypted it anyway \nCheck you spelling and capital letter";
			}

		} else if (method == 3) {

			for (int i = 0; i < subs_num; i++) {
				if (message_int[i] == 1001) {
					message_text = message_text + " ";
				} else if (message_int[i] == 1002) {
					message_text = message_text + "\t";
				} else if (message_int[i] == 1003) {
					message_text = message_text + "\n";
				} else {
					message_text = message_text + character[Math.abs(message_int[i]) % character.length];
				}
			}

		} else if (method == 4) {
			for (int i = 0; i < wordcount; i++) {
				// System.out.println(message_int[i]);
				message_text = message_text + character[Math.abs(message_int[i]) % character.length];
			}
		}
		// load.dispose();
		textArea.setText(message_text);
	}

	// I wrote this
	public int decrypt_key(int q, int p) {
		/*
		 * try { File outFile = new File("decryption_key record.txt"); FileWriter fw =
		 * new FileWriter(outFile.getAbsoluteFile(), true); BufferedWriter outStream =
		 * new BufferedWriter(fw);
		 */

		int phi = ((q - 1) * (p - 1));
		int encrypt_key = 0;
		// It actually needs the encryption key to do the decryption
		for (int i = 2; i < phi; i++) {
			if (isCoprime(i, (q * p)) == true) {
				if (isCoprime(i, phi) == true) {
					encrypt_key = i;
					break;
				}
			}
		}
		// outStream.write("Encrypt key: " + encrypt_key);
		// outStream.write("");
		// outStream.write("");
		// outStream.write("");
		// getting decrypt key
		BigInteger decrypt_key = BigInteger.valueOf(10);

		while (true) {
			if ((decrypt_key.multiply(BigInteger.valueOf(encrypt_key)).mod(BigInteger.valueOf(phi)).intValue()) == 1) {
				// outStream.close();
				return decrypt_key.intValue();
			}
			if (decrypt_key.intValue() == 2147483647) {
				JOptionPane.showMessageDialog(null,
						"Congradulation!!! Whoever encrypted this message literally Broke the System \nContact Wyett or Mr. Castaneda for an update \nWyett's email: wyettzeng@gamil.com");
				System.exit(0);
			} else {
				decrypt_key = decrypt_key.add(BigInteger.valueOf(1));
				// System.out.println(decrypt_key);
			}
		}
		/*
		 * } catch (IOException e) { System.exit(0); return 0; }
		 */
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

	// This function was taken from
	// https://www.cse.iitb.ac.in/~vishalm/lab_submissions/lab_8_251/lab08_outlab/NthRoot.java,
	// I couldn't find the author
	public BigInteger NthRootfunc(BigInteger num, int n) {
		ArrayList<BigDecimal> arr = new ArrayList<BigDecimal>();
		Integer i = 1;
		BigDecimal[] tArray = new BigDecimal[5];
		BigDecimal N = new BigDecimal(n);
		BigDecimal A = new BigDecimal(num);
		arr.add(0, A);

		while (true) {
			tArray[0] = (arr.get(i - 1).multiply(new BigDecimal(n - 1)));
			tArray[1] = ((arr.get(i - 1)).pow(n - 1));
			tArray[2] = A.divide(tArray[1], 6, RoundingMode.FLOOR);
			tArray[3] = tArray[0].add(tArray[2]);
			tArray[4] = tArray[3].divide(N, 6, RoundingMode.FLOOR);

			arr.add(i, tArray[4]);
			i = i + 1;

			if ((arr.get(i - 1)).compareTo(arr.get(i - 2)) == 0) {
				break;
			}
		}

		return (arr.get(i - 1)).toBigInteger();
	}
}
