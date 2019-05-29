/**  
* @Title: Uitest.java  
* @Package bankAccount  
* @Description: TODO  
* @author zhan_even  
* @date 2019年5月9日  
* @version V1.0  
*/
package UI;

import java.util.Timer;

import bankAccount.Bank;

/**  
* @ClassName: Uitest  
* @Description: TODO
* @author zhan_even  
* @date 2019年5月9日  
*    
*/
public class Uitest {
	public static void main(String args[]) {
		UserInterface Uitest = new UserInterface();
		Timer timer = new Timer();   
		timer.schedule(Uitest, 1000, 20000);
		
	}
}
