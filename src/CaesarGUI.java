/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * Encrypts and Decrypts text using the Caesar Cihper algorithm.
 *
 * @author Invisible Computer, JTN
 *
 */
public class CaesarGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private JTextField shiftFactor;
	private JTextArea inputTA;
	private JTextArea outputTA;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CaesarGUI().setVisible(true);
	}

	public void encryptText() throws InterruptedException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int shift;
		// Get the text from the app and store it in a String variable.
		String textNum = this.shiftFactor.getText();
		// Check to see if a "Shift Factor" value was entered.
		// If there wasn't, set shift to zero,
		// Otherwise parse the input value to an integer so we can use it.
		if (!textNum.equals("")) {
			shift = Integer.parseInt(textNum) % 26;
		} else {
			shift = 0;
		}
		// Map every letter of the alphabet to another letter in the alphabet,
		// shifted by x places.
		String input = inputTA.getText();
		String output = "";
		if (shift!=0)
		{
			for (int x = 0; x < input.length(); x++) {
			Random rand = new Random();
			list.add((int) input.charAt(x));
			list.add((int) input.charAt(rand.nextInt(input.length() - 1)));
		}				
		}
		else
		{
			for (int x = 0; x < input.length(); x++) {
				list.add((int) input.charAt(x));				
			}				
		}
		for (int x : list) {
			x += shift;
			output += String.valueOf((char) x);

		}
		// Output the encrypted text
		outputTA.setText(output);
	}

	public void decryptText() throws InterruptedException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int shift;
		// Get the text from the app and store it in a String variable.
		String textNum = this.shiftFactor.getText();
		// Check to see if a "Shift Factor" value was entered.
		// If there wasn't, set shift to zero,
		// Otherwise parse the input value to an integer so we can use it.
		if (!textNum.equals("")) {
			shift = Integer.parseInt(textNum) % 26;
		} else {
			shift = 0;
		}
		// Map every letter of the alphabet to another letter in the alphabet,
		// shifted by x places.
		String input = inputTA.getText();
		String output = "";
		if (shift != 0) {
			for (int x = 0; x < input.length(); x = x + 2) {
				list.add((int) input.charAt(x));
			}
		}
		else
		{
			for (int x = 0; x < input.length(); x++) {
				list.add((int) input.charAt(x));
			}
		}
		for (int x : list) {
			x -= shift;
			output += String.valueOf((char) x);

		}
		// Output the encrypted text
		outputTA.setText(output);
	}

	public CaesarGUI() {
		setTitle("Caesar Cipher");
		setVisible(true);
		setDefaultCloseOperation(3);

		Container content = getContentPane();
		GridLayout layout = new GridLayout(3, 0, 0, 10);
		content.setLayout(layout);

		inputTA = new JTextArea(
				"Insert the text to be encrypted/decrypted here, then press the appropriate button.",
				12, 40);
		inputTA.setLineWrap(true);
		inputTA.setWrapStyleWord(true);
		inputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		JScrollPane scroller = new JScrollPane(inputTA);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		content.add(scroller);

		outputTA = new JTextArea("Output text.", 12, 40);
		outputTA.setLineWrap(true);
		outputTA.setWrapStyleWord(true);
		outputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		JScrollPane scroller2 = new JScrollPane(outputTA);
		scroller2
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		content.add(scroller2);

		JPanel box1 = new JPanel();
		box1.setLayout(new FlowLayout());
		JButton decryptButton = new JButton("Decrypt");
		JButton encryptButton = new JButton("Encrypt");
		decryptButton.addActionListener(this);
		encryptButton.addActionListener(this);
		box1.add(decryptButton);
		box1.add(encryptButton);
		box1.add(new JLabel("Shift Factor"));
		box1.add(this.shiftFactor = new JTextField(20));
		content.add(box1);

		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Encrypt")) {
			try {
				encryptText();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("Decrypt")) {
			try {
				decryptText();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
