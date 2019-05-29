/**  
* @Title: OpenAccoutFrame.java  
* @Package bankAccount  
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import bankAccount.CurrentAccount;
import bankAccount.Customer;
import bankAccount.JuniorAccount;
import bankAccount.SaverAccount;

/**  
* @ClassName: OpenAccoutFrame  
* @Description: TODO
* @author zhan_even  
* @date 2019年5月9日  
*    
*/
public class OpenAccoutFrame implements ActionListener, KeyListener {
	private JFrame openAcc;
	private JTextField nametext, addtext, birtext, cretext;
	private ButtonGroup group;
	private JButton submit;
	private JPasswordField passtext;
	private JRadioButton current, junior, saver;
	String type;
	Bank bank = null;
	
	public OpenAccoutFrame(Bank b){
		bank = b;
		openAcc = new JFrame();
		openAcc.setLayout(new GridLayout(0, 1, 10, 5));
		openAcc.setTitle("Open new account");

		JLabel label1 = new JLabel("Name:");
		nametext = new JTextField();
		nametext.setPreferredSize(new Dimension(300, 25));

		JLabel label2 = new JLabel("Address:");
		addtext = new JTextField();
		addtext.setPreferredSize(new Dimension(300, 25));

		JLabel label3 = new JLabel("Birthday: (Example: 1998-10-06)");
		birtext = new JTextField();
		birtext.setPreferredSize(new Dimension(300, 25));

		JLabel label4 = new JLabel("Select the account type:");
		group = new ButtonGroup();
		JPanel jpopen = new JPanel();
		jpopen.setLayout(new GridLayout(0, 3, 1, 2));
		current = new JRadioButton("Current Account");
		group.add(current);
		jpopen.add(current);
		current.addActionListener(this);
		junior = new JRadioButton("Junior Account");
		group.add(junior);
		jpopen.add(junior);
		junior.addActionListener(this);
		saver = new JRadioButton("Saver Account");
		group.add(saver);
		jpopen.add(saver);
		saver.addActionListener(this);

		JLabel label5 = new JLabel("Set your PIN: (6 numbers)");
		passtext = new JPasswordField();
		passtext.setPreferredSize(new Dimension(300, 25));
		passtext.addKeyListener(this);

		JLabel label6 = new JLabel("Initial credit: (Min: 100):");
		cretext = new JTextField();
		cretext.addKeyListener(this);
		cretext.setPreferredSize(new Dimension(300, 25));

		submit = new JButton("Submit");
		submit.addActionListener(this);

		openAcc.add(label1);
		openAcc.add(nametext);
		openAcc.add(label2);
		openAcc.add(addtext);
		openAcc.add(label3);
		openAcc.add(birtext);
		openAcc.add(label4);
		openAcc.add(jpopen);
		openAcc.add(label5);
		openAcc.add(passtext);
		openAcc.add(label6);
		openAcc.add(cretext);
		openAcc.add(submit);

		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 370) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 500) / 2;

		openAcc.setResizable(false);
		openAcc.setLocation(w, h);
		openAcc.setSize(370, 500);
		openAcc.setVisible(true);
	}
	
	
	private boolean checkBirth(String birth) {
		if (birth.length() == 10) {
			String yy = birth.substring(0, 4);
			String mm = birth.substring(5, 7);
			String dd = birth.substring(8, 10);
			int year = Integer.parseInt(yy);
			int month = Integer.parseInt(mm);
			int day = Integer.parseInt(dd);

			if (year < 2019 && year > 1970 && month < 13 && month > 0
					&& day < 32 && day > 0) {
				return true;
				
			} else
				JOptionPane.showMessageDialog(new JFrame().getContentPane(),
						"Birthday format error! Example: 1998-10-16\n" + 
						"Create new account failed!", 
						"System Message",
						JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(new JFrame().getContentPane(),
					"Birthday format error! Example: 1998-10-16\n" + 
					"Create new account failed!",
					"System Message",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return false;

		
	}


	
	/**
	* <p>Title: actionPerformed</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)  
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(current)) {
			type = "current";
			System.out.println(type);
		} 
		else if (e.getSource().equals(junior)) {
			type = "junior";
			System.out.println(type);
		} 
		else if (e.getSource().equals(saver)) {
			type = "saver";
			System.out.println(type);
		}
		if (e.getSource().equals(submit)) {
			if (nametext.getText().equals("") || addtext.getText().equals("") || birtext.getText().equals("") || cretext.getText().equals("") || passtext.getText().equals("")) {
				JOptionPane.showMessageDialog(new JFrame().getContentPane(),
						"Please fill in all the information", "System Message",
						JOptionPane.INFORMATION_MESSAGE);
			} 
			else if (checkPin() == false) {
				JOptionPane.showMessageDialog(new JFrame().getContentPane(),
						"The pin must been consisted by six digits. ", "System Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				String name = nametext.getText();
				String add = addtext.getText();
				String birth = birtext.getText();
				String pin = passtext.getText();
				double credit = Double.parseDouble(cretext.getText());
				if (credit < 100) {
					JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							"Initial credit must be greater than 100!", "System Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if (checkBirth(birth) == false) {
					}
					else {
						SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
						Date date;
						try {
							date = sdf.parse(birth);
						} catch (ParseException e1) {
							date = null;
							e1.printStackTrace();
						}
						
						Calendar birthday = Calendar.getInstance();
						birthday.setTime(date);
						Customer customer = new Customer(name, add, birthday);
						
				
						System.out.println(type);
						if (type == "current") {
							CurrentAccount acc = bank.openCurrentAccount(customer, credit, pin);
							if (acc == null) {
								JOptionPane.showMessageDialog(new JFrame().getContentPane(),
										"Bad credit customer! Can not open a account", "System Message",
										JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								nametext.setText("");
								addtext.setText("");
								birtext.setText("");
								cretext.setText("");
								passtext.setText("");
								System.out.println("Saving data...");
								bank.writeBankAccount();
								JOptionPane.showMessageDialog(new JFrame().getContentPane(),
										"Create new account success!\nYour account number: "+ acc.getAccNo() + "\n"
										+ "Your pin number is: " + acc.getPin(), "System Message",
										JOptionPane.INFORMATION_MESSAGE);
								openAcc.setVisible(false);
							}
							
						}
						else if (type == "junior") {
							JuniorAccount jun = bank.openJuniorAccount(customer, credit, pin);
							if (jun == null) {
								JOptionPane.showMessageDialog(new JFrame().getContentPane(),
										"Bad credit customer! Can not open a account", "System Message",
										JOptionPane.INFORMATION_MESSAGE);
							}
							if (jun.getCustomer() == null) {
						
									System.out.println("Age > 16 can not open junior account.");
									JOptionPane.showMessageDialog(new JFrame().getContentPane(),
											"Warning! Age > 16 can not open junior account.", "System Message",
											JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								nametext.setText("");
								addtext.setText("");
								birtext.setText("");
								cretext.setText("");
								passtext.setText("");
								System.out.println("Saving data...");
								bank.writeBankAccount();
								JOptionPane.showMessageDialog(new JFrame().getContentPane(),
										"Create new account success!\nYour account number: "+ jun.getAccNo() + "\n"
										+ "Your pin number is: " + jun.getPin(), "System Message",
										JOptionPane.INFORMATION_MESSAGE);
								openAcc.setVisible(false);
							}
						}
						else if (type == "saver") {
							SaverAccount account = bank.openSaverAccount(customer, credit, pin);
							if (account == null) {
								JOptionPane.showMessageDialog(new JFrame().getContentPane(),
										"Bad credit customer! Can not open a account", "System Message",
										JOptionPane.INFORMATION_MESSAGE);
								
							}
							else {
								nametext.setText("");
								addtext.setText("");
								birtext.setText("");
								cretext.setText("");
								passtext.setText("");
								System.out.println("Saving data...");
								bank.writeBankAccount();
								JOptionPane.showMessageDialog(new JFrame().getContentPane(),
										"Create new account success!\nYour account number: "+ account.getAccNo() + "\n"
										+ "Your pin number is: " + account.getPin(), "System Message",
										JOptionPane.INFORMATION_MESSAGE);
								openAcc.setVisible(false);
							}
							
						}
						
					}
				}
			}
		}
				

	}


	/**  
	* @Title: checkPin  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws  
	*/  
	private boolean checkPin() {
		String pin = passtext.getText();
		int length = pin.length();
		if (length!=6) {
			return false;
		}
		return true;
	}


	/**
	* <p>Title: keyTyped</p>  
	* <p>Description: </p>  
	* @param e  
	* @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)  
	*/
	@Override
	public void keyTyped(KeyEvent e) {
		
		
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
