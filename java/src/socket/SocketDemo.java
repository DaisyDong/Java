/**
 * socket,�ͻ��˳���
 * ͨ��IP�Ͷ˿����ӵ������������ϣ�ʵ�ֺͷ�������ͨ��
 */
package socket;
import java.net.*;
import java.io.*;

public class SocketDemo {
	public static void main(String args[]) {
		try {
			Socket clientSocket=new Socket("172.17.132.170",4331);
			String str=null;
			DataInputStream in=null;
			DataOutputStream out=null;
			
			in=new DataInputStream(clientSocket.getInputStream());
			out=new DataOutputStream(clientSocket.getOutputStream());
			out.writeUTF("���ǿͻ�����");
			int i=0;
			while(true) {
				str=in.readUTF();
				out.writeUTF(i++ +"");
				System.out.println("�ͻ����յ���"+str);
				Thread.sleep(1000);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}