/**  
* @Title: JuniorAccount.java  
* @Package bankAccount  
* @Description: TODO  
* @author zhan_even  
* @date 2019年4月18日  
* @version V1.0  
*/
package bankAccount;

import java.util.Calendar;

/**  
* @ClassName: JuniorAccount  
* @Description: TODO
* @author zhan_even  
* @date 2019年4月18日  
*    
*/
public class JuniorAccount extends BankAccount {
	
	public static final int ageLimit = 16;
	/**
	 * @param customer
	 * @param balance
	 */
	public JuniorAccount(Customer customer, double balance) {
		super(customer, balance);
		Calendar timeNow = Calendar.getInstance();
		timeNow.add(Calendar.YEAR, -ageLimit);
		Calendar birth = customer.getBirth();
		if (timeNow.after(birth))
			System.out.println("IllegalAge!");
	}
	/**
	* <p>Title: toString</p>  
	* <p>Description: </p>  
	* @return  
	* @see java.lang.Object#toString()  
	*/
	@Override
	public String toString() {
		return "JuniorAccount [getCustomer()=" + getCustomer() + "\n getAccNo()=" + getAccNo() + "\n getPin()=" + getPin()
				+ "\n getBalance()=" + getBalance() + "\n isSuspended()=" + isSuspended() + "\n getCheque()=" + getCheque()
				+ "]";
	}

	
	
}
