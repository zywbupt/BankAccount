/**  
* @Title: test.java  
* @Package bankAccount  
* @Description: TODO  
* @author zhan_even  
* @date 2019年5月23日  
* @version V1.0  
*/
package bankAccount;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;

import UI.UserInterface;

/**  
* @ClassName: test  
* @Description: TODO
* @author zhan_even  
* @date 2019年5月23日  
*    
*/
public class test {

	
	/**
	 * @throws IOException   
	* @Title: main  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param args    参数  
	* @return void    返回类型  
	* @throws  
	*/
	public static void main(String[] args) throws IOException {
		
		String cmdString = String.format("python run_with_socket.py");
		Process  pcsProcess = Runtime.getRuntime().exec(cmdString);
		 // 定义Python脚本的返回值
        String result = null;
        // 获取CMD的返回流
        BufferedInputStream in = new BufferedInputStream(pcsProcess.getInputStream());
        // 字符流转换字节流
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        // 这里也可以输出文本日志

        String lineStr = null;
        while ((lineStr = br.readLine()) != null) {
            result = lineStr;
        }
        // 关闭输入流
        br.close();
        in.close();

        System.out.println(result);

	}

}
