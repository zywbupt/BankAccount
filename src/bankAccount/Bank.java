/**  
* @Title: Bank.java  
* @Package bankAccount  
* @Description: TODO  
* @author zhan_even  
* @date 2019年5月6日  
* @version V1.0  
*/
package bankAccount;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


/**  
* @ClassName: Bank  
* @Description: TODO
* @author zhan_even  
* @date 2019年5月6日  
*    
*/

public class Bank implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<BankAccount> bankAcc = new ArrayList<BankAccount>();
	

	public Bank() {
		bankAcc = readBankAccount();
	}
	

	public BankAccount getBankAccount(int accno) {
		for (BankAccount accs : bankAcc) {
			if (accs.getAccNo() == accno) {
				return accs;
			}
		}
		return null;
	}
	
	
	/**
	 * 
	* @Title: openSaverAccount  
	* @Description: open a saver Account
	* @param @param customer
	* @param @param initBalance
	* @param @param pin
	* @param @return    参数  
	* @return SaverAccount      
	* @throws
	 */
	public SaverAccount openSaverAccount(Customer customer, double initBalance, String pin) {
		if (checkCredit(customer) == false) {
			return null;
		}
		try {
			SaverAccount account = new SaverAccount(customer, initBalance);
			account.setRandomNo();
			account.setPin(pin);
			account.setBalance(initBalance);
			account.setType("saver");
			System.out.println("Open saverAccount sucessfully");
			String accInformation = "Account no: " + account.getAccNo() + ", PIN: "  + account.getPin() + customer;
			System.out.println(accInformation);
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("bankAccounts.txt"));
				writer.write(accInformation);
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			bankAcc.add(account);
			return account;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	* @Title: openJuniorAccount  
	* @Description: open a junior account
	* @param @param customer
	* @param @param initBalance
	* @param @param pin
	* @param @return    参数  
	* @return JuniorAccount    返回类型  
	* @throws
	 */
	public JuniorAccount openJuniorAccount(Customer customer, double initBalance, String pin) {
		if (checkCredit(customer) == false) {
			return null;
		}
		else {
			try {
				Calendar rightNow = Calendar.getInstance();
				rightNow.add(Calendar.YEAR, -16);
				Calendar birth = customer.getBirth();
				if (rightNow.after(birth)) {
					JuniorAccount account = new JuniorAccount(customer, 0);
					return account;
				}
				JuniorAccount account = new JuniorAccount(customer, initBalance);
				account.setRandomNo();
				account.setPin(pin);
				account.setBalance(initBalance);
				account.setType("junior");
				System.out.println("Open juniorAccount sucessfully");
				String accInformation = "Account no: " + account.getAccNo() + ", PIN: "  + account.getPin() + customer;
				System.out.println(accInformation);
				
				bankAcc.add(account);
				return account;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
	}
	
	/**
	 * 
	* @Title: openCurrentAccount  
	* @Description: open a current account
	* @param @param customer
	* @param @param initBalance
	* @param @param pin
	* @param @return    参数  
	* @return CurrentAccount    返回类型  
	* @throws
	 */
	public CurrentAccount openCurrentAccount(Customer customer, double initBalance, String pin) {
		return openCurrentAccount( customer, initBalance, pin, 500);
	}
	
	
	/**  
	* @Title: openCurrentAccount  
	* @Description: open a current account
	* @param @param customer
	* @param @param initBalance
	* @param @param pin
	* @param @param i    参数  
	* @return void    返回类型  
	* @throws  
	*/  
	private CurrentAccount openCurrentAccount(Customer customer, double initBalance, String pin, double overdraftLimit) {
		if (checkCredit(customer) == false) {
			return null;
		}
		try {
			CurrentAccount account = new CurrentAccount(customer, initBalance, overdraftLimit);
			account.setRandomNo();
			account.setPin(pin);
			account.setBalance(initBalance);
			account.setType("current");
			System.out.println("Open Current Account sucessfully");
			String accInformation = "Account no: " + account.getAccNo() + ", PIN: "  + account.getPin() + customer;
			System.out.println(accInformation);
			
			bankAcc.add(account);
			return account;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	/**  
	* @Title: readBankAccount  
	* @Description: 读取文件 
	* @param @return    参数  
	* @return ArrayList<BankAccount>    返回类型  
	* @throws  
	*/  
	private ArrayList<BankAccount> readBankAccount() {
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		try {
			System.out.println("reading");
			
			FileInputStream fileInputStream = new FileInputStream("bankAccount.ser");
			ObjectInputStream inputStream  = new ObjectInputStream(fileInputStream);
			@SuppressWarnings("unchecked")
			ArrayList<BankAccount> tmpAccounts = (ArrayList<BankAccount>) inputStream.readObject();
			inputStream.close();
			fileInputStream.close();
			bankAccounts = tmpAccounts;
			System.out.println("read done");
		} catch (FileNotFoundException ignored) {
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bankAccounts;
	}

	/**  
	* @Title: writeBankAccount  
	* @Description: 写文件 
	* @param @return    参数  
	* @return 
	* @throws  
	*/  
	public void writeBankAccount() {
		for (BankAccount accs : bankAcc) {
			System.out.println(accs.getAccNo());
			}
		try {
			
			FileOutputStream fileOutputStream = new FileOutputStream("bankAccount.ser");
			ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
			outputStream.writeObject(bankAcc);
			outputStream.close();
			fileOutputStream.close();
//			System.out.println(bankAcc);
			
			System.out.println("write down");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	




	/**  
	* @Title: checkCredit  
	* @Description: checkCredit 
	* @param @param customer
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws  
	*/  
	private boolean checkCredit(Customer customer) {
		if (customer.getName().equals("bad")) {
			return false;
		}
		return true;
	}


	


	/**  
	* @Title: closeAccount  
	* @Description: closeAccount 
	* @param @param accNum
	* @param @return    accNO
	* @return int    1 if balance != 0, 2 if cheque!=0, 0 remove successfully, 3 accNo not exists, 4 bad credit  
	* @throws  
	*/  
	public int closeAccount(int accNum) {
		for (BankAccount acc : bankAcc) {
			if (acc.getAccNo() == accNum ) {
				if (acc.getBalance() != 0 ) {
					return 1;
				}
				else if (acc.getCheque() != 0 ) {
					return 2;
				}
				else if (checkCredit(acc.getCustomer()) == false) {
					return 4;
				}
				else {
					bankAcc.remove(acc);
					writeBankAccount();
					return 0;
					
				}
			}
		}
		System.out.println("Please write right accNo");
		return 3;
	}


	/**
	 * @return the bankAcc
	 */
	public ArrayList<BankAccount> getBankAcc() {
		return bankAcc;
	}


	/**  
	* @Title: clear  
	* @Description: clear in time
	* @param     参数  
	* @return void    返回类型  
	* @throws  
	*/  
	public void clear() {
		for (BankAccount acc : bankAcc) {
			if (acc.equals(null)) {
				return ;
			}
			acc.clear();
			System.out.println("clear");
		}
		
	}
	
}