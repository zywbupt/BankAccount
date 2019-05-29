/**  
* @Title: User.java  
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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import bankAccount.Bank;
import bankAccount.BankAccount;

/**  
* @ClassName: User  
* @Description: TODO
* @author zhan_even  
* @date 2019年5月10日  
*    
*/
public class Employee implements ActionListener, KeyListener, WindowListener {
	
	private JFrame frame, suspFrame, reinFrame, closeFrame, clearFrame, checkFrame;
	private JButton open, suspend, reinstated, close, checkAccount, clear, sussub, reinSub, closeSub, clearSub;
	private JTextField accField;
	private JPanel panel;
	private JTable table;
	private JScrollPane jscrollpane;
	Bank bank;
	BankAccount acc;
	/**
	 * @param bank
	 */
	public Employee(Bank b) {
		bank = b;
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Management System");

		JLabel north = new JLabel(
				"		Welcome!Please choose the function:");
		north.setPreferredSize(new Dimension(300, 40));
		frame.add(BorderLayout.NORTH, north);

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(0, 2, 10, 10));
		frame.add(BorderLayout.CENTER, center);

		open = new JButton("Open a new account");
		suspend = new JButton("Suspend");
		close = new JButton("Close");
		reinstated = new JButton("Re-instated");
		clear = new JButton("Claer");
		checkAccount = new JButton("Check Account");
		open.addActionListener(this);
		suspend.addActionListener(this);
		close.addActionListener(this);
		reinstated.addActionListener(this);
		clear.addActionListener(this);
		checkAccount.addActionListener(this);
		center.add(open);
		center.add(checkAccount);
		center.add(suspend);      
		center.add(close);
		center.add(reinstated);
		center.add(clear);

		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 1000) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2;

		frame.setResizable(false);
		frame.setLocation(w, h);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	/**
	* <p>Title: actionPerformed</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)  
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(open)) {
			new OpenAccoutFrame(bank);
		}
		else if(e.getSource().equals(suspend)) {
			suspendAcc();
		}
		else if (e.getSource().equals(reinstated)) {
			reinStated();
		}
		else if (e.getSource().equals(close)) {
			closeAccount();
		}
		else if (e.getSource().equals(clear)) {
			clearAccount();
		}
		else if (e.getSource().equals(checkAccount)) {
			checkAccount();
		}
	}
	/**  
	* @Title: checkAccount  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param     参数  
	* @return void    返回类型  
	* @throws  
	*/  
	private void checkAccount() {
		checkFrame = new JFrame();
		panel = new JPanel();
		jscrollpane = new JScrollPane();
		Object[] columnNames = new Object[]{"accNum", "type", "pin", "isSuspended","balance","cheque", "noticeTime", "noticeWithdraw"};//列名
		Object[][] rowData = new Object[101][50];
		ArrayList<BankAccount> bankAcc = bank.getBankAcc();
		int i = 0;
		for (BankAccount acc : bankAcc) {
			rowData[i][0] = acc.getAccNo();
			rowData[i][1] = acc.getType();
			rowData[i][2] = acc.getPin();
			rowData[i][3] = acc.isSuspended();
			rowData[i][4] = acc.getBalance();
			rowData[i][5] = acc.getCheque();
			rowData[i][6] = acc.getNoticeDate();
			rowData[i][7] = acc.getNoticeWithdraw();
			i++;
			
		}
		table = new JTable(rowData, columnNames);
		jscrollpane.setBounds(0, 0, 650, 500);
		jscrollpane.setViewportView(table);//这句很重要
		table.setRowHeight(30); 
		/**
		 * 字居中显示设置
		*/
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();    
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,r);
		panel.add(jscrollpane);
		checkFrame.add(panel);

		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 300) / 2;

		checkFrame.setResizable(false);
		checkFrame.setLocation(w, h);
		checkFrame.setSize(500, 400);
		checkFrame.setVisible(true);
		checkFrame.addWindowListener(this);
	}
	/**  
	* @Title: clearAccount  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param     参数  
	* @return void    返回类型  
	* @throws  
	*/  
	private void clearAccount() {
		clearFrame = new JFrame("Close Account");
		clearFrame.setLayout(new GridLayout(0, 1, 10, 5));
		JLabel label1 = new JLabel("Please input account num:");
		accField = new JTextField();
		accField.setPreferredSize(new Dimension(300, 25));
		accField.addKeyListener(this);
		clearSub = new JButton("close");
		clearSub.setPreferredSize(new Dimension(300, 10));
		clearSub.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String accN = accField.getText();
				int accNum = Integer.parseInt(accN);
				System.out.println(accNum);
				acc = bank.getBankAccount(accNum);
				if (acc == null) {
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"The account does not exist!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					acc.clear();
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"Clear successful" + "\n" + 
							"Balance:" + acc.getBalance()+"\n"+
							"Cheque:" + acc.getCheque()
							, "System Message",
							JOptionPane.INFORMATION_MESSAGE);
					clearFrame.setVisible(false);
				}
				
			}
		});
		clearFrame.add(label1);
		clearFrame.add(accField);
		clearFrame.add(clearSub);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 300) / 2;

		clearFrame.setResizable(false);
		clearFrame.setLocation(w, h);
		clearFrame.setSize(300, 200);
		clearFrame.setVisible(true);
		clearFrame.addWindowListener(this);
		
		
	}
	/**  
	* @Title: closeAccount  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param     参数  
	* @return void    返回类型  
	* @throws  
	*/  
	private void closeAccount() {
		
		closeFrame = new JFrame("CLose Account");
		closeFrame.setLayout(new GridLayout(0, 1, 10, 5));
		JLabel label1 = new JLabel("Please input account num:");
		accField = new JTextField();
		accField.setPreferredSize(new Dimension(300, 25));
		accField.addKeyListener(this);
		closeSub = new JButton("close");
		closeSub.setPreferredSize(new Dimension(300, 10));
		closeSub.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String accN = accField.getText();
				int accNum = Integer.parseInt(accN);
				System.out.println(accNum);
				acc = bank.getBankAccount(accNum);
				String message = " Really Close the account ? ";
                String title = "Close Confrim";
                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                	int num = bank.closeAccount(accNum);
                	if (acc == null) {
    					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
    							"The account does not exist!", "System Message",
    							JOptionPane.INFORMATION_MESSAGE);
    				}
    				else if (num == 1) {
    					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
    							"The account balance is not equal to 0!", "System Message",
    							JOptionPane.INFORMATION_MESSAGE);
    				}
    				else if (num == 2) {
    					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
    						"The account is not suspended!", "System Message",
    						JOptionPane.INFORMATION_MESSAGE);
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
    					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
    							"Close account successfully!", "System Message",
    							JOptionPane.INFORMATION_MESSAGE);
    					closeFrame.setVisible(false);
    				}
                	closeFrame.setVisible(false);
                	accField.setText("");
                }
				
				
			}
		});
		closeFrame.add(label1);
		closeFrame.add(accField);
		closeFrame.add(closeSub);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 300) / 2;

		closeFrame.setResizable(false);
		closeFrame.setLocation(w, h);
		closeFrame.setSize(300, 200);
		closeFrame.setVisible(true);
		closeFrame.addWindowListener(this);
		
	}
	/**  
	* @Title: reinStated  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param     参数  
	* @return void    返回类型  
	* @throws  
	*/  
	private void reinStated() {
		reinFrame = new JFrame("Re-Instated Account");
		reinFrame.setLayout(new GridLayout(0, 1, 10, 5));
		JLabel label1 = new JLabel("Please input account num:");
		accField = new JTextField();
		accField.setPreferredSize(new Dimension(300, 25));
		accField.addKeyListener(this);
		reinSub = new JButton("reinstated");
		reinSub.setPreferredSize(new Dimension(300, 10));
		
		reinSub.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String accN = accField.getText();
				int accNum = Integer.parseInt(accN);
				System.out.println(accNum);
				acc = bank.getBankAccount(accNum);
				if (acc == null) {
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"The account does not exist!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else if (!acc.isSuspended()){
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"The account is not suspended!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					acc.reinstate();
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"Re-instated successfully!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
					reinFrame.setVisible(false);
				}
				accField.setText("");
			}
		});
		reinFrame.add(label1);
		reinFrame.add(accField);
		reinFrame.add(reinSub);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 300) / 2;

		reinFrame.setResizable(false);
		reinFrame.setLocation(w, h);
		reinFrame.setSize(300, 200);
		reinFrame.setVisible(true);
		reinFrame.addWindowListener(this);
	}
	/**  
	* @Title: suspendAcc  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param     参数  
	* @return void    返回类型  
	* @throws  
	*/  
	private void suspendAcc() {
		suspFrame = new JFrame("Suspend Account");
		suspFrame.setLayout(new GridLayout(0, 1, 10, 5));
		JLabel label1 = new JLabel("Please input account num:");
		accField = new JTextField();
		accField.setPreferredSize(new Dimension(300, 25));
		accField.addKeyListener(this);
		sussub = new JButton("suspend");
		sussub.setPreferredSize(new Dimension(300, 10));
		sussub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accN = accField.getText();
				int accNum = Integer.parseInt(accN);
				System.out.println(accNum);
				acc = bank.getBankAccount(accNum);
				if (acc == null) {
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"The account does not exist!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else if (acc.isSuspended()){
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"The account already be suspended!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					acc.suspend();
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"Suspended!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
					suspFrame.setVisible(false);
				}
				accField.setText("");
			}
		});
		suspFrame.add(label1);
		suspFrame.add(accField);
		suspFrame.add(sussub);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 300) / 2;

		suspFrame.setResizable(false);
		suspFrame.setLocation(w, h);
		suspFrame.setSize(300, 200);
		suspFrame.setVisible(true);
		suspFrame.addWindowListener(this);
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
		if (e.getSource().equals(accField) ) {
			if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)
					|| keyChar == 46) {

			} else {
				e.consume();
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
		bank.writeBankAccount();
		System.out.println("Saving data....");
		
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
		// TODO Auto-generated method stub
		
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
