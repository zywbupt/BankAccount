/**  
* @Title: LoginFrame.java  
* @Package UI  
* @Description: TODO  
* @author zhan_even  
* @date 2019年5月9日  
* @version V1.0  
*/
package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import bankAccount.Bank;
import bankAccount.BankAccount;

/**  
* @ClassName: LoginFrame  
* @Description: TODO
* @author zhan_even  
* @date 2019年5月9日  
*    
*/
public class LoginFrame implements KeyListener, ActionListener, WindowListener{
	JFrame frame, logFrame, dep;
	Bank bank;
	int accno;
	BankAccount acc;
	private JTextField accNum, depAmount;
	private ButtonGroup group;
	private JButton subLogin, checkBala, deposit, withdraw, closeAcc, subdep;
	private JRadioButton cash, cheque;
	private JPasswordField passtext;
	
	String type;
	
	
	public LoginFrame(Bank b) {
		bank = b;
		frame = new JFrame();
		frame.setLayout(new GridLayout(0, 1, 10, 5));
		frame.setTitle("Login");
		JLabel label1 = new JLabel("Account Number:");
		accNum = new JTextField();
		accNum.setPreferredSize(new Dimension(300, 10));
		accNum.addKeyListener(this);
		JLabel label2 = new JLabel("Enter your PIN:");
		passtext = new JPasswordField();
		passtext.setPreferredSize(new Dimension(300, 10));
		passtext.addKeyListener(this);
		subLogin = new JButton("Submit");
		subLogin.addActionListener(this);

		frame.add(label1);
		frame.add(accNum);
		frame.add(label2);
		frame.add(passtext);
		frame.add(subLogin);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 300) / 2;

		frame.setResizable(false);
		frame.setLocation(w, h);
		frame.setSize(300, 200);
		frame.setVisible(true);
		frame.addWindowListener(this);
		
	}
	
	/**  
	* @Title: depositFrame  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param     参数  
	* @return void    返回类型  
	* @throws  
	*/  
	

	
	
	/**
	* <p>Title: actionPerformed</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)  
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(subLogin)) {
			if (accNum.getText().equals("") || passtext.getText().equals("")) {
				JOptionPane.showMessageDialog(new JFrame().getContentPane(),
						"Please fill in all the information", "System Message",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				
				String accNo = accNum.getText();
				accno = Integer.parseInt(accNo);
				String pin = passtext.getText();
				acc =  bank.getBankAccount(accno);
				if (acc == null) {
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"The account does not exist!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else if (!acc.cheakPin(pin)) {
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"Wrong pin input!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else if (acc.isSuspended()){
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"Account is suspended!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					new UserFrame(bank, accno);
					frame.setVisible(false);
				}
			}
		}
	}


	/**
	* <p>Title: keyTyped</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)  
	*/
	@Override
	public void keyTyped(KeyEvent e) {
		int keyChar = e.getKeyChar();
		if (e.getSource().equals(accNum) || e.getSource().equals(passtext)) {
			if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
			} else {
				e.consume();
			}
		}
		if (e.getSource().equals(depAmount)) {
			if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)
					|| keyChar == 46) {

			} else {
				e.consume();
				}
		}
		
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	* <p>Title: windowClosing</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)  
	*/
	@Override
	public void windowClosing(WindowEvent e) {
		bank.writeBankAccount();
		System.out.println("Saving data....");
	}

	/**
	* <p>Title: windowClosed</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)  
	*/
	@Override
	public void windowClosed(WindowEvent e) {
		bank.writeBankAccount();
		System.out.println("Saving data....");
		
	}

	/**
	* <p>Title: windowIconified</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)  
	*/
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	* <p>Title: windowDeiconified</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)  
	*/
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	* <p>Title: windowActivated</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)  
	*/
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	* <p>Title: windowDeactivated</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)  
	*/
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	* <p>Title: keyPressed</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)  
	*/
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	* <p>Title: keyReleased</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)  
	*/
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
