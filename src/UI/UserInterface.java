/**  
* @Title: UserInterface.java  
* @Package bankAccount  
* @Description: TODO  
* @author zhan_even  
* @date 2019年5月7日  
* @version V1.0  
*/
package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bankAccount.Bank;

/**  
* @ClassName: UserInterface  
* @Description: TODO
* @author zhan_even  
* @date 2019年5月7日  
*    
*/
public class UserInterface extends TimerTask implements Serializable, ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton user, depositor, employee;
	
	public static Bank bank;
	/**
	 * 
	 */
	public UserInterface() {
		System.out.println("Starting system...");
		bank = new Bank();
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Banking System");

		JLabel north = new JLabel(
				"		Welcome!Please choose the function:");
		north.setPreferredSize(new Dimension(300, 40));
		frame.add(BorderLayout.NORTH, north);

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(0, 3, 10, 10));
		frame.add(BorderLayout.CENTER, center);

		user = new JButton("Customer");
		depositor = new JButton("Depositor");
		employee = new JButton("Management");
		user.addActionListener(this);
		depositor.addActionListener(this);
		employee.addActionListener(this);
		center.add(user);
		center.add(depositor);
		center.add(employee);

		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 1000) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2;

		frame.setResizable(false);
		frame.setLocation(w, h);
		frame.setSize(300, 100);
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
		if (e.getSource().equals(user)) {
			new LoginFrame(bank);
		}
		else if (e.getSource().equals(depositor)) {
			new DepositFrame(bank, 1);
		}
		else if (e.getSource().equals(employee)) {
			new Employee(bank);
		}
			
	}	


	/**
	* <p>Title: run</p>  
	* <p>Description: </p>    
	* @see java.util.TimerTask#run()  
	*/
	@Override
	public void run() {
		bank.clear();
    	System.out.println("clear");
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


