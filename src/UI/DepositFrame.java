/**  
* @Title: DepositFrame.java  
* @Package UI  
* @Description: TODO  
* @author zhan_even  
* @date 2019年5月9日  
* @version V1.0  
*/
package UI;

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
* @ClassName: DepositFrame  
* @Description: TODO
* @author zhan_even  
* @date 2019年5月9日  
*    
*/
public class DepositFrame implements ActionListener, KeyListener, WindowListener {
	private static final int PRECISION = 2;
	private JFrame dep;
	private JTextField depAmount,accField;
	private ButtonGroup group;
	private JButton subdep;
	private JRadioButton cash, cheque;
	String type;
	Bank bank;
	BankAccount acc;
	int n;
	public DepositFrame(Bank b, int a) {
		bank = b;
		n = a;
		dep = new JFrame();
		dep.setLayout(new GridLayout(0, 1, 10, 5));
		dep.setTitle("Deposit");
		
		
		
		JLabel label2 = new JLabel("Deposit amount:");
		depAmount = new JTextField();
		depAmount.setPreferredSize(new Dimension(300, 25));
		depAmount.addKeyListener(this);

		JLabel label3 = new JLabel("Select the deposit type:");
		group = new ButtonGroup();
		JPanel jpopen = new JPanel();
		jpopen.setLayout(new GridLayout(0, 2, 1, 2));
		cash = new JRadioButton("Cash");
		group.add(cash);
		jpopen.add(cash);
		cash.addActionListener(this);
		cheque = new JRadioButton("Cheque");
		group.add(cheque);
		jpopen.add(cheque);
		cheque.addActionListener(this);

		subdep = new JButton("Submit");
		subdep.addActionListener(this);
		if (n == 1) {
			JLabel label1 = new JLabel("Please input account num:");
			accField = new JTextField();
			accField.setPreferredSize(new Dimension(300, 25));
			accField.addKeyListener(this);
			dep.add(label1);
			dep.add(accField);
		}
		
		
		dep.add(label2);
		dep.add(depAmount);
		dep.add(label3);
		dep.add(jpopen);
		dep.add(subdep);

		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 300) / 2;

		dep.setResizable(false);
		dep.setLocation(w, h);
		dep.setSize(300, 200);
		dep.setVisible(true);
		dep.addWindowListener(this);
		
	}
	
	/**
	* <p>Title: actionPerformed</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)  
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(cheque)) {
			type = "cheque";
		} else if (e.getSource().equals(cash)) {
			type = "cash";
		}
		
		if (e.getSource().equals(subdep)) {
			System.out.println(type);
			if (depAmount.getText().equals("") || (type != "cheque" && type != "cash")) {
				if (n == 1) {
					if (accField.getText().equals("")) {
					}
				}
			} 
			
			else {
				if (n == 1) {
					String accN = accField.getText();
					int accNum = Integer.parseInt(accN);
					System.out.println(accNum);
					acc = bank.getBankAccount(accNum);
				}
				else {
					acc =  bank.getBankAccount(n);
				}
				if (acc == null) {
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"The account does not exist!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					double depositamount = Double.parseDouble(depAmount.getText());
					if (type == "cheque") {
						int a = acc.depositByCheque(depositamount);
						if (a == 1) {
							JOptionPane.showMessageDialog(new JFrame().getContentPane(),
									"Error! The account is suspended!",
									"System Message",
									JOptionPane.INFORMATION_MESSAGE);
						}
						else if (a == 2){
							JOptionPane.showMessageDialog(new JFrame().getContentPane(),
									"Error! Deposit amount must be more than 0!",
									"System Message",
									JOptionPane.INFORMATION_MESSAGE);
						}
						else if (a == 0) {
							JOptionPane.showMessageDialog(new JFrame().getContentPane(),
									"Deposit Success: " + depositamount
									+ "\nAfter Deposit balance: " +  acc.getBalance() + "\n" 
									+ "\nAfter Deposit cheque: " +  acc.getCheque(),
									"System Message",
									JOptionPane.INFORMATION_MESSAGE);
							dep.setVisible(false);
						}
						depAmount.setText("");
						System.out.println(acc.getBalance());
						
					}
					else if (type == "cash") {
						int a = acc.depositByCash(depositamount);
						if (a == 1) {
							JOptionPane.showMessageDialog(new JFrame().getContentPane(),
									"Error! The account is suspended!",
									"System Message",
									JOptionPane.INFORMATION_MESSAGE);
						}
						else if (a == 2) {
							JOptionPane.showMessageDialog(new JFrame().getContentPane(),
									"Error! Deposit amount must be more than 0!",
									"System Message",
									JOptionPane.INFORMATION_MESSAGE);	
						}
						else {
							JOptionPane.showMessageDialog(new JFrame().getContentPane(),
									"Deposit Success: " + depositamount
									+ "\nAfter Deposit balance: " +  acc.getBalance() + "\n" 
									+ "\nAfter Deposit cheque: " +  acc.getCheque(),
									"System Message",
									JOptionPane.INFORMATION_MESSAGE);
							dep.setVisible(false);
						}
						depAmount.setText("");
						System.out.println(acc.getBalance());
					}
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
		String text = depAmount.getText();  // 当前输入框内容
		char ch = e.getKeyChar();   // 准备附加到输入框的字符
		int keyChar = e.getKeyChar();
		if (e.getSource().equals(accField) ) {
			if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {

			} else {
				e.consume();
				}
			}
		else if (e.getSource().equals(depAmount)) {
			// 限制不能输入非数字和小数点
		      if(!(ch >= '0' && ch <= '9') && ch != '.') {
		    	  e.consume();    // 销毁当前输入字符
		      }
		      // 限制不能是小数点开头
		      else if("".equals(text) && ch == '.') {
		    	  e.consume();
		    	  } 
		      else if(text.contains(".")){
		    	  // 限制不能重复输入小数点
		    	  if(ch == '.') {
		    		  e.consume();
		    		  }
		    	  // 限制小数位数
		    	  else {
		                int idx = text.indexOf('.');
		                String tmp = text.substring(idx + 1);
		                if(tmp.length() >= PRECISION) { 
		                    e.consume();
		                }
		            }
		        }
		}
		
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
	/**
	* <p>Title: windowOpened</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)  
	*/
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
}
