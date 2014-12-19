package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui extends Frame {
	Button send, tcpStart, udpStart;
	TextField textField;
	TextArea textArea;
	TextField ipArea, portArea;

	String ip, port;

	public Gui(String title) {
		Frame frame = new Frame(title);
		frame.setLayout(new FlowLayout());
		frame.setBackground(Color.orange);
		frame.setMinimumSize(new Dimension(250, 350));

		send = new Button("Send");
		send.setBackground(Color.pink);
		textField = new TextField(15);
		ipArea = new TextField("ip", 7);
		portArea = new TextField("port", 7);
		tcpStart = new Button("tcp");
		udpStart = new Button("udp");

		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				appendNewText(textField.getText().toString());
				textField.setText("");
			}
		});

		udpStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		tcpStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		textArea = new TextArea(16, 22);
		textArea.setBackground(Color.green);
		textArea.setForeground(Color.BLUE);
		frame.addWindowListener(new W1());

		frame.add(ipArea);
		frame.add(portArea);
		frame.add(tcpStart);
		frame.add(udpStart);

		frame.add(textField);
		frame.add(send);
		frame.add(textArea);

		setFont(new Font("Arial", Font.BOLD, 20));

		frame.setLocation(500, 200);// set the location
		frame.setVisible(true);
		frame.validate();
	}

	private class W1 extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}

	public static void main(String args[]) {
		Gui gui = new Gui("TCP-UDP");
	}

	private void appendNewText(String newMessage) {
		StringBuilder sb = new StringBuilder();
		sb.append(textArea.getText().toString());
		sb.append("\n");
		sb.append(newMessage);

		textArea.setText(sb.toString());
	}
}