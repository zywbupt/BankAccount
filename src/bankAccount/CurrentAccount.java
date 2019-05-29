/**  
* @Title: CurrentAccount.java  
* @Package bankAccount  
* @Description: TODO  
* @author zhan_even  
* @date 2019年5月6日  
* @version V1.0  
*/
package bankAccount;

/**  
* @ClassName: CurrentAccount  
* @Description: TODO
* @author zhan_even  
* @date 2019年5月6日  
*    
*/
public class CurrentAccount extends BankAccount {

	private double overdraftLimit;
	/**
	 * @param customer
	 * @param balance
	 * @param overdraftLimit
	 */
	public CurrentAccount(Customer customer, double balance, double overdraftLimit) {
		super(customer, balance);
		setOverdraftLimit(overdraftLimit);
	}
	/**
	 * @return the overdraftLimit
	 */
	public double getOverdraftLimit() {
		return overdraftLimit;
	}
	/**
	 * @param overdraftLimit the overdraftLimit to set
	 */
	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	
	
	@Override
	public int withdraw(double amount) {
		if (isSuspended()) {
			System.out.println("Error! The account is suspended!");
			return 1;
		} else if (amount > getBalance() + overdraftLimit) {
			System.out.println("Error! No enough balance!");
			return 3;
		} else if (amount <= 0) {
			System.out.println("Error withdraw amount: " + amount + ".");
			return 2;
		} else {
			setBalance(getBalance() - amount); 
			System.out.println("Withdraw Success: " + amount
					+ "\nAfter withdraw balance: " + getBalance());
			return 0;
		}
	}
	/**
	* <p>Title: toString</p>  
	* <p>Description: </p>  
	* @return  
	* @see java.lang.Object#toString()  
	*/
	@Override
	public String toString() {
		return "CurrentAccount [overdraftLimit=" + overdraftLimit + "\n"
				+ super.toString() + "]";
	}

	
	

}
