/**
 * UDP��̡����㲥���ݱ���ʵ���������ϵ���ͻ���������Ϣ���ͻ��˲��ϵؽ�������
 */
package udp;

import java.net.*; 

public class Weather extends Thread {
	String weather="����Ԥ���������������½�����ע�Ᵽů��";
	int port=9999;
	InetAddress iaddress=null;
	MulticastSocket socket=null;	//�������㲥�׽���
	Weather() {	//��ʾ����
		try {
				iaddress=InetAddress.getByName("224.255.10.0");	//ʵ����inetaddress����
				socket=new MulticastSocket(port);	//
				socket.setTimeToLive(1);	//ָ�����ͷ�Χ�Ǳ�������
				socket.joinGroup(iaddress);	//����㲥��
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			DatagramPacket packet=null;	//���ݱ���������UDP�Ĵ���
			byte data[]=weather.getBytes();	//�����ֽ�����
			packet=new DatagramPacket(data,data.length,iaddress,port);	//ָ�����ݰ��ڴ�Ŀռ�ʹ�С�������ݰ���Ŀ���ַ��˿�
			
			System.out.println(new String(data));
			try {
				socket.send(packet);
				sleep(3000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]) {
		Weather w=new Weather();
		w.start();
	}
}
