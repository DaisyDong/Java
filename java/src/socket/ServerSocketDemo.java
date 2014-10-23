/**
 * ������socket������˳���serverSocket���յ��ͻ��˵�����ʱaccept��������һ��socket��
 * ��ȡ�ͻ��˳����������Ϣ�����������ʵ����ͻ��˵�ͨ��
 */
package socket;
import java.net.*;
import java.io.*;

public class ServerSocketDemo {
	public static void main(String args[]) {
		try {
			ServerSocket serverSocket=new ServerSocket(4331);
			Socket clientSocket=null;
			String str;
			DataInputStream in=null;
			DataOutputStream out=null;
			
			clientSocket = serverSocket.accept();//���ܿͻ���������
			in=new DataInputStream(clientSocket.getInputStream());//��ȡ�׽��ֵ�������
			out=new DataOutputStream(clientSocket.getOutputStream());//��ȡ�׽��ֵ������
			while(true){
				str=in.readUTF();
				out.writeUTF(str);;
				System.out.println("�������յ���"+str);
				Thread.sleep(1000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
