/**  
* @Title: Customer.java  
* @Package bankAccount  
* @Description: TODO  
* @author zhan_even  
* @date 2019年4月17日  
* @version V1.0  
*/
package bankAccount;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**  
* @ClassName: Customer  
* @Description: TODO
* @author zhan_even  
* @date 2019年4月17日  
*    
*/
public class Customer  implements Serializable {
	
	private String name;
	private String address;
	private Calendar birth;
	/**
	 * @param name
	 * @param address
	 * @param birth
	 */
	public Customer(String name, String address, Calendar birth) {
		super();
		this.name = name;
		this.address = address;
		this.birth = birth;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the birth
	 */
	public Calendar getBirth() {
		return birth;
	}
	/**
	 * @param birth the birth to set
	 */
	public void setBirth(Calendar birth) {
		this.birth = birth;
	}
	/**
	* <p>Title: toString</p>  
	* <p>Description: </p>  
	* @return  
	* @see java.lang.Object#toString()  
	*/
	@Override
	public String toString() {
		return "Customer[" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", birth=" + (new SimpleDateFormat("yyyy-MM-dd")).format(birth.getTime()) +
				']';
	}
	
	
}
