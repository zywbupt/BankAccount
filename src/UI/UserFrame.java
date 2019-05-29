/**  
* @Title: UserFrame.java  
* @Package UI  
* @Description: TODO  
* @author zhan_even  
* @date 2019年5月10日  
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
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import bankAccount.Bank;
import bankAccount.BankAccount;

/**  
* @ClassName: UserFrame  
* @Description: TODO
* @author zhan_even  
* @date 2019年5月10日  
*    
*/
public class UserFrame implements ActionListener {
	private JFrame logFrame, closeFrame;
	private JButton deposit, checkBala, withdraw, closeAcc, noticeWith ,  closeSub;
	private JPasswordField pinField;
	private int accno;
	Bank bank;
	public UserFrame(Bank b, int no) {
		bank = b;
		accno = no;
		logFrame = new JFrame();
		logFrame.setLayout(new BorderLayout());
		logFrame.setTitle("Account Management");

		JLabel north = new JLabel(
				"			Account Management");
		north.setPreferredSize(new Dimension(300, 40));
		logFrame.add(BorderLayout.NORTH, north);

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(0, 3, 10, 10));
		logFrame.add(BorderLayout.CENTER, center);

		checkBala =  new JButton("Check Banlance");
		deposit = new JButton("Deposit");
		withdraw = new JButton("Withdraw");
		noticeWith = new JButton("Notice Withdraw");
		closeAcc = new JButton("Close");

		checkBala.addActionListener(this);
		deposit.addActionListener(this);
		withdraw.addActionListener(this);
		closeAcc.addActionListener(this);
		noticeWith.addActionListener(this);
		
		center.add(checkBala);
		center.add(deposit);
		center.add(withdraw);
		center.add(noticeWith);
		center.add(closeAcc);
		

		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 1000) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2;

		logFrame.setResizable(false);
		logFrame.setLocation(w, h);
		logFrame.setSize(350, 250);
		logFrame.setVisible(true);
	
	}
	/**
	* <p>Title: actionPerformed</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)  
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(deposit)) {
			new DepositFrame(bank, accno);
		}
		if (e.getSource().equals(checkBala)) {
			checkBalance();
		}
		if (e.getSource().equals(withdraw)) {
			if (!bank.getBankAccount(accno).getType().equals("saver")) {
				System.out.println("not saver");
				new Withdraw(bank, accno);
			}
			else {
				int num = bank.getBankAccount(accno).withdraw(0);
				if (num == 4) {
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"Does not reach the notice date",
							"System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"Withdraw succssfully!" + "\n" +
									"Balance: " + bank.getBankAccount(accno) .getBalance()
									, "System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		}
		if (e.getSource().equals(noticeWith)) {
			new NoticeWith(bank, accno);
		}
		if (e.getSource().equals(closeAcc)) {
			closeAcc();
		}
	}
	
	/**  
	* @Title: closeAcc  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param     参数  
	* @return void    返回类型  
	* @throws  
	*/  
	private void closeAcc() {
		closeFrame = new JFrame("Close Account");
		closeFrame.setLayout(new GridLayout(0, 1, 10, 5));
		JLabel label1 = new JLabel("Please input pin to confirm:");
		pinField = new JPasswordField();
		pinField.setPreferredSize(new Dimension(300, 25));
		pinField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (e.getSource().equals(pinField)) {
					if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) ) {
					} else {
						e.consume();
						}
					}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		closeSub = new JButton("close");
		closeSub.setPreferredSize(new Dimension(300, 10));
		closeSub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pIN = pinField.getText();
				BankAccount acc = bank.getBankAccount(accno);
				String message = " Really Close the account ? ";
                String title = "Close Confrim";
                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                	
                	if (!acc.cheakPin(pIN)) {
    					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
    							"The account does not exist!", "System Message",
    							JOptionPane.INFORMATION_MESSAGE);
    				}
                	else {
                		int num = bank.closeAccount(accno);
                		if (num == 1) {
        					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
        							"The account balance is not equal to 0!", "System Message",
        							JOptionPane.INFORMATION_MESSAGE);
        					
        				}
        				else if (num == 2) {
        					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
        						"The account is not suspended!", "System Message",
        						JOptionPane.INFORMATION_MESSAGE);
        					closeFrame.setVisible(false);
        				}
        				else if (num == 4) {
        					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
        							"The customer is in bad credit!", "System Message",
        							JOptionPane.INFORMATION_MESSAGE);
        				}
        				else if (num == 3) {
        					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
        							"The account does not exist!", "System Message",
        							JOptionPane.INFORMATION_MESSAGE);
        				}
        				else {
        					closeFrame.setVisible(false);
        					logFrame.setVisible(false);
        					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
        							"Close account successfully!", "System Message",
        							JOptionPane.INFORMATION_MESSAGE);
        			}
                		closeFrame.setVisible(false);
                  }
                }
				
				
			}
		});
		closeFrame.add(label1);
		closeFrame.add(pinField);
		closeFrame.add(closeSub);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 300) / 2;

		closeFrame.setResizable(false);
		closeFrame.setLocation(w, h);
		closeFrame.setSize(300, 200);
		closeFrame.setVisible(true);
		closeFrame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				bank.writeBankAccount();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				bank.writeBankAccount();				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	/**  
	* @Title: checkBalance  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param     参数  
	* @return void    返回类型  
	* @throws  
	*/  
	private void checkBalance() {
		BankAccount acc = bank.getBankAccount(accno);
		if (acc.getType().equals("saver")) {
			JOptionPane.showMessageDialog(new JFrame().getContentPane(),
					"Account balance: " + acc.getBalance()+"\n"+
					"Cheque:" + acc.getCheque()+"\n"+
					"Notice withdraw time: " + acc.getNoticeDate() +"\n"+
					"Notice withdraw amount: " + acc.getNoticeWithdraw(),
					"System Message",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (acc.getType().equals("junior")) {
			JOptionPane.showMessageDialog(new JFrame().getContentPane(),
					"Account balance: " + acc.getBalance()+"\n",
					"Cheque:" + acc.getCheque()+"\n"+
					"System Message",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (acc.getType().equals("current")) {
			JOptionPane.showMessageDialog(new JFrame().getContentPane(),
					"Account balance: " + acc.getBalance()+"\n",
					"Cheque:" + acc.getCheque()+"\n"+
					"System Message",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
	
	
}
