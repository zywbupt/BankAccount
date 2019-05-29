/**  
* @Title: SaverAccount.java  
* @Package bankAccount  
* @Description: TODO  
* @author zhan_even  
* @date 2019年4月18日  
* @version V1.0  
*/
package bankAccount;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.omg.CORBA.PUBLIC_MEMBER;

/**  
* @ClassName: SaverAccount  
* @Description: TODO
* @author zhan_even  
* @date 2019年4月18日  
*    
*/
public class SaverAccount extends BankAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int withdrawLimit = 1;
	private Calendar noticeDate; //date that user can withdraw the balance
//	private double saverBalance; //balance of saver account
	private double noticeWithdraw; //the amount of money withdrawed last time
	
	/**
	 * @param customer
	 * @param accNo
	 * @param pin
	 * @param balance
	 * @param isSuspended
	 * @param cheque
	 */
	public SaverAccount(Customer customer, int accNo, String pin, double balance, boolean isSuspended, double cheque) {
		super(customer, accNo, pin, balance, isSuspended, cheque);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param customer
	 * @param balance
	 */
	public SaverAccount(Customer customer, double balance) {
		super(customer, 0);
		this.noticeDate = null;
		this.noticeWithdraw = 0;
	}
	
	
	
	/**
	 * This function clears funds.
	 */
	@Override
	public void clear() {
		setBalance(getBalance()+getCheque());
		setCheque(0);
		//true如果此Calendar表示的时间是当对象所表示的时间之后;否则为false。
//		if (noticeDate != null) {
//			if (noticeDate.after(Calendar.getInstance())) {
//				setBalance(getBalance()+noticeWithdraw);
//				noticeWithdraw = 0;
//			}
//		}
		
	}
	
	/**
	* <p>Title: withdraw</p>  
	* <p>Description: </p>  
	* @param amount
	* @return  if withdraw successfully return 0, if not reach the noticed time return 4
	* @see bankAccount.BankAccount#noticeWithdraw(double)  
	*/
	@Override
	public int withdraw(double amount) {
		Calendar nowDate = Calendar.getInstance(); 
//		if (isSuspended()) {
//			System.out.println("Error! The account is suspended!");
//			return 1;
//		}
//		else if (amount > getBalance()) {
//			System.out.println("Error! No enough balance!");
//			return 3;
//		}
//		else if (amount <= 0) {
//			System.out.println("Error withdraw amount!");
//			return 2;
//		} 
//		else 
		if (nowDate.before(noticeDate)) {
			System.out.println("Does not reach the notice date");
			return 4;
		}
		else {
			setBalance(getBalance() - noticeWithdraw);
			noticeDate = null;
			noticeWithdraw = 0;
			return 0;
		}
		
	}
	
	
	/**
	 * 
	* <p>Title: noticeWithdraw</p>  
	* <p>Description: </p>  
	* @param amount
	* @return  1 if account is suspended, 2 if account already have a noticewithdarw, 3 if there is not enough balance, 4 if input a error withdraw balance
	* @see bankAccount.BankAccount#noticeWithdraw(double)
	 */
	@Override
	public int noticeWithdraw(double amount) {
		if (isSuspended()) {
			System.out.println("Error! The account is suspended!");
			return 1;
		} else if (noticeWithdraw > 0) {
			System.out.println("You have already had a noticeWithdraw.");
			return 2;
		} else if (amount > getBalance()) {
			System.out.println("Error! No enough balance!");
			return 3;
		} else if (amount <= 0) {
			System.out.println("Error withdraw amount! ");
			return 4;
		} 
		else {
//			setBalance(getBalance()-amount);
			noticeWithdraw += amount;
			noticeDate = Calendar.getInstance();
			noticeDate.add(Calendar.DAY_OF_YEAR, withdrawLimit);
			System.out.println("Notice withdraw with amount: " + noticeWithdraw);
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
		return "SaverAccount[customer=" + getCustomer() + "\naccNo=" + getAccNo() + "\nnoticeDate=" + noticeDate + "\nbalance=" + getBalance() + "\nnoticeWithdraw="
				+ noticeWithdraw + "]";
	}

	/**
	* <p>Title: getNoticeDate</p>  
	* <p>Description: </p>  
	* @return  
	* @see bankAccount.BankAccount#getNoticeDate()  
	*/
	@Override
	public String getNoticeDate() {
		super.getNoticeDate();
		if (noticeDate!=null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateStr = sdf.format(noticeDate.getTime());
			return dateStr;
		}
		return null;
	}

	/**
	* <p>Title: getNoticeWithdraw</p>  
	* <p>Description: </p>  
	* @return  
	* @see bankAccount.BankAccount#getNoticeWithdraw()  
	*/
	@Override
	public double getNoticeWithdraw() {
		super.getNoticeWithdraw();
		return noticeWithdraw;
	}
	
	
	
	
	

}
