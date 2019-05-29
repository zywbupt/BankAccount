/**  
* @Title: Withdraw.java  
* @Package UI  
* @Description: TODO  
* @author zhan_even  
* @date 2019年5月10日  
* @version V1.0  
*/
package UI;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bankAccount.Bank;
import bankAccount.BankAccount;

/**  
* @ClassName: Withdraw  
* @Description: TODO
* @author zhan_even  
* @date 2019年5月10日  
*    
*/
public class Withdraw implements KeyListener, ActionListener {
	final int PRECISION = 2;    // FIXME 小数位数为2， 建议可配
	private Frame frame;
	private JTextField withAmount;
	private JButton withdrawButton;
	Bank bank;
	BankAccount bankAccount;
	/**
	 * @param bank
	 * @param accno
	 */
	public Withdraw(Bank b, int accno) {
		bank = b;
		bankAccount = bank.getBankAccount(accno);
		frame = new JFrame("Withdraw money");
		frame.setLayout(new GridLayout(0, 1, 10, 5));
		
		JLabel label2 = new JLabel("Withdraw amount:");
		withAmount = new JTextField();
		withAmount.setPreferredSize(new Dimension(300, 25));
		withAmount.addKeyListener(this);
		
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setPreferredSize(new Dimension(40, 25));
		withdrawButton.addActionListener(this);
		
		frame.add(label2);
		frame.add(withAmount);
		frame.add(withdrawButton);
		
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 300) / 2;

		frame.setResizable(false);
		frame.setLocation(w, h);
		frame.setSize(300, 200);
		frame.setVisible(true);
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
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
				bank.writeBankAccount();
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
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
	* <p>Title: keyTyped</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)  
	*/
	@Override
	public void keyTyped(KeyEvent e) {
		  String text = withAmount.getText();  // 当前输入框内容
	      char ch = e.getKeyChar();   // 准备附加到输入框的字符

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
	* <p>Title: actionPerformed</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)  
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String withString = withAmount.getText();
		if (withString.equals("")) {
			
		}
		else {
			double amount = Double.parseDouble(withString);
			int num = bankAccount.withdraw(amount);
			if (num == 1) {
				withAmount.setText("");
				JOptionPane.showMessageDialog(new JFrame().getContentPane(),
						"Error! The account is suspended!", "System Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else if (num == 2) {
				withAmount.setText("");
				JOptionPane.showMessageDialog(new JFrame().getContentPane(),
						"Error withdraw amount!", "System Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else if (num == 3) {
				withAmount.setText("");
				JOptionPane.showMessageDialog(new JFrame().getContentPane(),
						"Error! No enough balance!", "System Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else if (num == 4) {
				withAmount.setText("");
				JOptionPane.showMessageDialog(new JFrame().getContentPane(),
						"Does not reach the notice date", "System Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(new JFrame().getContentPane(),
						"Withdraw succssfully!" + "\n" +
						"Balance: " + bankAccount.getBalance()
						, "System Message",
						JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
			}
			
		}
		
	}

}
