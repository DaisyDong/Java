/**
 * ����socketmanager�࣬ʹʵ�ֶ��ڶ��socket����ӡ�ɾ��������socket���������ѡ��socket�������Ϣ�Ĺ���
 */
package chatRoom;
import java.util.*;
import java.net.*;
import java.io.*;

public class SocketManager extends ArrayList {
	//synchronized java�ؼ��֣���ʾͬ������飬��һ���̷߳���ͬ�������ʱ�������̲߳��ܷ���ͬ������飬���ǿ��Է���
	//��ͬ������飬��֤ͬһʱ�����ֻ��һ���̷߳��ʸô����
	synchronized void add(Socket socket) {	//����׽���
		super.add(socket);
	}
	synchronized void delete(Socket socket) {	//ɾ���׽���
		super.remove(socket);
	}
	synchronized void sendClientCount() {	//�����ǰ��������
		String info="��ǰ��������"+size();
		System.out.println(info);
		writeAll(info);
	}
	synchronized void writeAll(String str) {	//ʹ���׽���������������Ϣ
		PrintWriter write=null;
		Socket socket;
		for(int i=0;i<size();i++) {
			socket=(Socket)get(i);	//	��ȡ�׽���
			try {
				write=new PrintWriter(socket.getOutputStream(),true);	//	���������
				if(write != null)
					write.println(str);	//ͨ������������Ϣ
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
