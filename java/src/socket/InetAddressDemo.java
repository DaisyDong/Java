/**
 * ��inetAddress���ѧϰ�Ͳ��ԣ����ɡ���Ҫ����
 */
package socket;

import java.net.UnknownHostException;
import java.net.*;
public class InetAddressDemo {
	public static void main(String args[]) {
		InetAddress inetAddress;
		try {
			inetAddress=InetAddress.getLocalHost();
			String localname=inetAddress.getHostName();
			String localIp=inetAddress.getHostAddress();
			System.out.println("��������"+localname);
			System.out.println("IP��ַ��"+localIp);
		}catch (UnknownHostException e){
			e.printStackTrace();
		}
	}
}
