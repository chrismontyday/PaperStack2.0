

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class PaperStack {

	PaperMaker pMaker = new PaperMaker();
	public int paperStack, timerSpeed, grampaNumber, grampaPrice, superNumber, superPrice, mechaNumber, mechaPrice,
			tuskNumber, tuskPrice;
	double perSecond;
	boolean timerOn;
	public JTextArea textArea;
	public JButton button1, button2, button3, button4;
	JLabel counterLabel;
	JLabel perSecondLabel;
	String label = " Paper Stack";
	Timer timer;
	MouseHandler mHandler = new MouseHandler();
	boolean grampaUnlocked, superUnlocked, coolUnlocked, tuskUnlocked = false;
	Font fontOne = new Font("Arial Black", Font.PLAIN, 26);
	Font fontTwo = new Font("Arial Black", Font.PLAIN, 15);
	Font fontThree = new Font("Arial Black", Font.PLAIN, 22);
	Font fontFour = new Font("Arial Black", Font.PLAIN, 17);

	public static void main(String[] args) {

		new PaperStack();
	}

	public PaperStack() {
		timerOn = false;
		perSecond = 0;
		paperStack = 0;
		grampaNumber = 0;
		grampaPrice = 10;
		superNumber = 0;
		superPrice = 100;
		mechaNumber = 0;
		mechaPrice = 300;
		tuskNumber = 0;
		tuskPrice = 600;
		createUI();
		label = paperStack + label;
	}

	public void createUI() {

		JFrame window = new JFrame();
		window.setSize(900, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.DARK_GRAY);
		window.setLayout(null);

		JPanel paperPresserPanel = new JPanel();
		paperPresserPanel.setBounds(150, 220, 200, 200);
		paperPresserPanel.setBackground(Color.DARK_GRAY);
		window.add(paperPresserPanel);

		ImageIcon paperPresser = new ImageIcon(getClass().getClassLoader().getResource("paperPresser200.jpg.png"));

		JButton paperButton = new JButton();
		paperButton.setBackground(Color.DARK_GRAY);
		paperButton.setBorder(null);
		paperButton.setFocusPainted(false);
		paperButton.addActionListener(pMaker);
		paperButton.setActionCommand("Paper");
		paperButton.setIcon(paperPresser);
		paperPresserPanel.add(paperButton);

		JPanel counterPanel = new JPanel();
		counterPanel.setBounds(150, 100, 250, 100);
		counterPanel.setBackground(Color.DARK_GRAY);
		counterPanel.setLayout(new GridLayout(2, 1));
		window.add(counterPanel);

		counterLabel = new JLabel(paperStack + " Paper Stack");
		counterLabel.setFont(fontOne);
		counterLabel.setForeground(Color.white);
		counterPanel.add(counterLabel);

		perSecondLabel = new JLabel();
		perSecondLabel.setForeground(Color.white);
		perSecondLabel.setFont(fontTwo);
		counterPanel.add(perSecondLabel);

		window.setVisible(true);

		JPanel grampaPanel = new JPanel();
		grampaPanel.setBounds(500, 170, 250, 250);
		grampaPanel.setBackground(Color.blue);
		grampaPanel.setLayout(new GridLayout(4, 1));
		window.add(grampaPanel);

		button1 = new JButton("Grampa");
		button1.setFont(fontOne);
		button1.setFocusPainted(false);
		button1.addActionListener(pMaker);
		button1.setActionCommand("Grampa");
		button1.addMouseListener(mHandler);
		grampaPanel.add(button1);
		button2 = new JButton("?");
		button2.setFont(fontFour);
		button2.setFocusPainted(false);
		button2.addActionListener(pMaker);
		button2.setActionCommand("Super Grampa");
		button2.addMouseListener(mHandler);
		grampaPanel.add(button2);
		button3 = new JButton("?");
		button3.setFont(fontFour);
		button3.setFocusPainted(false);
		button3.addActionListener(pMaker);
		button3.setActionCommand("Mecha Grampa");
		button3.addMouseListener(mHandler);
		grampaPanel.add(button3);
		button4 = new JButton("?");
		button4.setFont(fontFour);
		button4.setFocusPainted(false);
		button4.addActionListener(pMaker);
		button4.setActionCommand("Ellon Tusk");
		button4.addMouseListener(mHandler);
		grampaPanel.add(button4);

		JPanel messagePanel = new JPanel();
		messagePanel.setBounds(500, 70, 250, 250);
		messagePanel.setBackground(Color.DARK_GRAY);
		window.add(messagePanel);

		textArea = new JTextArea();
		textArea.setBounds(500, 70, 250, 250);
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setFont(fontTwo);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		messagePanel.add(textArea);

		window.add(messagePanel);
	}

	public void setTimer() {

		timer = new Timer(timerSpeed, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				paperStack++;
				counterLabel.setText(paperStack + " Paper Stack");

				if (superUnlocked == false) {
					if (paperStack >= 100) {
						superUnlocked = true;
						button2.setText("Super Grampa");
					}
				}
				if (coolUnlocked == false) {
					if (paperStack >= 200) {
						coolUnlocked = true;
						button3.setText("Mecha Grampa");
					}
				}
				if (tuskUnlocked == false) {
					if (paperStack >= 500) {
						tuskUnlocked = true;
						button4.setText("Ellon Tusk");
					}
				}
			}
		});
	}

	public void timerUpdate() {
		if (timerOn == false) {
			timerOn = true;
		} else if (timerOn == true) {
			timer.stop();
		}

		double speed = 1 / perSecond * 1000;
		timerSpeed = (int) Math.round(speed);

		String secondLimiter = String.format("%.1f", perSecond);
		perSecondLabel.setText("per second: " + secondLimiter);

		setTimer();
		timer.start();
	}

	public class PaperMaker implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			String action = event.getActionCommand();

			switch (action) {
			case "Paper":
				paperStack++;
				counterLabel.setText(paperStack + " Paper Stack");
				break;
			case "Grampa":
				if (paperStack >= grampaPrice) {
					paperStack = paperStack - grampaPrice;
					grampaPrice = grampaPrice + 5;
					counterLabel.setText(paperStack + " Paper Stack");
					grampaNumber++;
					button1.setText("Grampa " + "(" + grampaNumber + ")");
					perSecond = perSecond + 0.11;
					timerUpdate();
				} else {
					textArea.setText("Your stack ain't big enough bruh");
				}
				break;
			case "Super Grampa":
				if (paperStack >= superPrice) {
					paperStack = paperStack - superPrice;
					superPrice = superPrice + 50;
					counterLabel.setText(paperStack + " Paper Stack");
					superNumber++;
					button2.setText("Super Grampa " + "(" + superNumber + ")");
					perSecond = perSecond + 0.4;
					timerUpdate();
				} else {
					textArea.setText("Your stack ain't big enough bruh");
				}
				break;
			case "Mecha Grampa":
				if (paperStack >= mechaPrice) {
					paperStack = paperStack - mechaPrice;
					mechaPrice = mechaPrice + 100;
					counterLabel.setText(paperStack + " Paper Stack");
					mechaNumber++;
					button3.setText("Mecha Grampa " + "(" + mechaNumber + ")");
					perSecond = perSecond + 0.82;
					timerUpdate();
				} else {
					textArea.setText("Your stack ain't big enough bruh");
				}
				break;
			case "Ellon Tusk":
				if (paperStack >= tuskPrice) {
					paperStack = paperStack - tuskPrice;
					tuskPrice = tuskPrice + 150;
					counterLabel.setText(paperStack + " Paper Stack");
					tuskNumber++;
					button4.setText("Ellon Tusk " + "(" + tuskNumber + ")");
					perSecond = perSecond + 1.49;
					timerUpdate();
				} else {
					textArea.setText("Your stack ain't big enough bruh");
				}
				break;
			}

		}

	}

	public class MouseHandler implements MouseListener {

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton button = (JButton) e.getSource();

			if (button == button1) {
				textArea.setText("Grampa\n(price: " + grampaPrice + ")\nMakes a sheet every 10 seconds.");
			}

			else if (button == button2) {
				textArea.setText("This choice is currently locked!");

			} else if (button == button3) {
				textArea.setText("This choice is currently locked!");

			} else if (button == button4) {
				textArea.setText("This choice is currently locked!");
			}

			if (superUnlocked == true) {
				if (button == button1) {
					textArea.setText("Grampa\n(price: " + grampaPrice + ")\nMakes a sheet every 10 seconds.");
				}

				else if (button == button2) {
					textArea.setText("Super Grampa\n(price: " + superPrice + ")\nMakes a sheet every 4 seconds.");

				} else if (button == button3) {
					textArea.setText("This choice is currently locked!");

				} else if (button == button4) {
					textArea.setText("This choice is currently locked!");
				}
			}

			if (coolUnlocked == true) {
				if (button == button1) {
					textArea.setText("Grampa\n(price: " + grampaPrice + ")\nMakes a sheet every 10 seconds.");
				}

				else if (button == button2) {
					textArea.setText("Super Grampa\n(price: " + superPrice + ")\nMakes a sheet every 6 seconds.");

				} else if (button == button3) {
					textArea.setText("Mecha Grampa\n(price: " + mechaPrice + ")\nMakes a sheet every 3 seconds.");

				} else if (button == button4) {
					textArea.setText("This choice is currently locked!");
				}
			}
			if (tuskUnlocked == true) {
				if (button == button1) {
					textArea.setText("Grampa\n(price: " + grampaPrice + ")\nMakes a sheet every 10 seconds.");
				}

				else if (button == button2) {
					textArea.setText("Super Grampa\n(price: " + superPrice + ")\nMakes a sheet every 6 seconds.");

				} else if (button == button3) {
					textArea.setText("Mecha Grampa\n(price: " + mechaPrice + ")\nMakes a sheet every 3 seconds.");

				} else if (button == button4) {
					textArea.setText("Ellon Tusk\n(price: " + tuskPrice + ")\nMakes a sheet every second.");
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton button = (JButton) e.getSource();

			if (button == button1) {
				textArea.setText(null);
			} else if (button == button2) {
				textArea.setText(null);
			} else if (button == button3) {
				textArea.setText(null);
			} else if (button == button4) {
				textArea.setText(null);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

	}

}