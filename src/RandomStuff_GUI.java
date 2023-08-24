import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class RandomStuff_GUI extends JFrame {
	File myFile = null;
	private JPanel contentPane;
	JFileChooser chooser = new JFileChooser();
	Component frame = null;
	String[][] data = new String [50][50];
	Timer time = new Timer();
	static JProgressBar progressBar = new JProgressBar();
	static int prograss = 0;


	public static void main(String[] args) {
		int wordcount = 100;
		double ram, ram2 = 0;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandomStuff_GUI frame = new RandomStuff_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		for (int i = 0; i < 101; i ++) {
			ram2 = wordcount;
			ram = i;
			//System.out.println("i: " + i);
			//System.out.println("wordcount: " + wordcount);
			System.out.println(ram / ram2 * 100);
			progressBar.setValue((int) (ram / ram2 * 100));
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public RandomStuff_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar.setStringPainted(true);
		progressBar.setBounds(15, 83, 450, 37);
		contentPane.add(progressBar);
		
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				//progressBar.setValue(prograss);
			}
		}, 0, 50);
	}
}
