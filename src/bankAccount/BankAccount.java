/**  
* @Title: BankAccount.java  
* @Package bankAccount  
* @Description: TODO  
* @author zhan_even  
* @date 2019年4月17日  
* @version V1.0  
*/
package bankAccount;

import java.io.Serializable;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**  
* @ClassName: BankAccount  
* @Description: TODO
* @author zhan_even  
* @date 2019年4月17日  
*    
*/
public class BankAccount  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private int accNo;
	private String pin;
	private double balance;
	private boolean isSuspended;
	private double cheque;
	private String type;
	
	/**
	 * @param customer
	 * @param accNo
	 * @param pin
	 * @param balance
	 * @param isSuspended
	 * @param cheque
	 */
	public BankAccount(Customer customer, int accNo, String pin, double balance, boolean isSuspended, double cheque) {
		this.customer = customer;
		setRandomNo();
		this.pin = pin;
		this.balance = balance;
		this.isSuspended = isSuspended;
		this.cheque = cheque;
	}
	/**
	 * @param customer
	 * @param balance
	 */
	public BankAccount(Customer customer, double balance) {
		setRandomNo();
		this.customer = customer;
		this.balance = balance;
		this.cheque = 0;
		this.isSuspended = false;
	}

	
	
	/**  
	* @Title: setRandomNo  
	* @Description:   generate random accNo
	* @param     参数  
	* @return void    返回类型  
	* @throws  
	*/  
	public void setRandomNo() {
		accNo = 10000000 + (int) (Math.random() * 90000000);
		
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the accNo
	 */
	public int getAccNo() {
		return accNo;
	}

	
	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * @param pin the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return true if suspended else false
	 */
	public boolean isSuspended() {
		return isSuspended;
	}

	/**
	 * @param isSuspended the isSuspended to set
	 */
	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	/**
	 * @return the cheque
	 */
	public double getCheque() {
		return cheque;
	}

	/**
	 * @param cheque the cheque to set
	 */
	public void setCheque(double cheque) {
		this.cheque = cheque;
	}
	
	/**
	 * This function check if the parameter is equal to pin.
	 *
	 * @param pin The string which you want to check.
	 * @return If the parameter is equal to pin, returns true.
	 */
	public boolean cheakPin(String pIN) {
		return this.pin.equals(pIN);
	}
	
	/**
	 * This function deposit money to cash.
	 *
	 * @param the amount of money you want to deposit
	 * @return if deposit successfully return 0, else return 1 for having been suspended 2 for error input
	 */
	public int depositByCash(double amount) {
		if (isSuspended()) {
			return 1;
		}
		if (amount <= 0) {
					
			return 2;
		}
		balance += amount;
		return 0;
	}
	
	/**
	 * This function deposit money to cheque.
	 *
	 * @param the amount of money you want to deposit
	 * @return if deposit successfully return 0, else return 1 for having been suspended, 2 for wrong input
	 */
	public int depositByCheque(double amount) {
		if (isSuspended) {
			return 1;
		}
		if (amount <= 0) {
			return 2;
		}
		cheque += amount;
		return 0;
	}
	
	/**
	 * This function withdraw money
	 *
	 * @param the amount of money you want to withdraw
	 * @return if deposit successfully return 0, else return 1 for having been suspended, 2 for wrong input, 3 for too no enough money
	 */
	public int withdraw(double amount) {
		if (isSuspended) {
			System.out.println("Error! The account is suspended!");
			return 1;
		} else if (amount > balance) {
			System.out.println("Error! No enough balance!");
			return 3;
		} else if (amount <= 0) {
			System.out.println("Error withdraw amount!");
			return 2;
		} else {
			balance = balance - amount;
			System.out.println("Withdraw Success: " + amount
					+ "\nAfter withdraw balance: " + balance);
			return 0;
		}
	}
	
	/**
	 * This function is used to suspend an account
	 */
	public void suspend() {
		isSuspended = true;
		System.out.println("Suspend account successfully.");
	}
	
	/**
	 * This function reinstate the account.
	 */
	public void reinstate() {
		isSuspended = false;
		System.out.println("Reinstate account successfully.");
	}

	
	/**
	 * This function clears funds.
	 */
	public void clear() {
		balance += cheque;
		cheque = 0;
	}
	
	
	/**
	 * This function is used to notice withdraw of saver account.
	 */
	public int noticeWithdraw(double amount) {
		if (!type.equals("saver")) {
			System.out.println("Not a saver Account!");
			return 7;
		}
		return 0;
		
	}
	
	/**
	* <p>Title: toString</p>  
	* <p>Description: </p>  
	* @return  
	* @see java.lang.Object#toString()  
	*/
	@Override
	public String toString() {
		return "BankAccount [customer=" + customer + "\naccNo=" + accNo + "\npin=" + pin + "\nbalance=" + balance
				+ "\nisSuspended=" + isSuspended + "\ncheque=" + cheque + "\n]";
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**  
	* @Title: getNoticeDate  
	* @Description: only used for saver account 
	* @param @return    参数  
	* @return Object    返回类型  
	* @throws  
	*/  
	public String getNoticeDate() {
		return null;
		
	}
	/**  
	* @Title: getNoticeWithdraw  
	* @Description: only used for saver account 
	* @param @return    参数  
	* @return double    返回类型  
	* @throws  
	*/  
	public double getNoticeWithdraw() {
		return 0;
		
	}
	
	
	
}
