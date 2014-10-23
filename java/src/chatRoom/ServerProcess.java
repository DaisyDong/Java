/*
 * ����˳��򣬴���������׽��֣����տͻ��˵��������������̣߳����������׽�����ӵ�������
 */
package chatRoom;
import java.net.*;
import java.io.*;

public class ServerProcess {
	private SocketManager socketMan=new SocketManager();
	void getServer() {
		try {
			ServerSocket serverSocket=new ServerSocket(9999);	//�����������׽���
			System.out.println("�������׽���������~");
			while(true) {
				Socket socket=serverSocket.accept();	//�ȴ�����
				new write_Thread(socket).start();	//�����߳�
				socketMan.add(socket);	//	����׽���
				socketMan.sendClientCount();	//����ǰ�׽����������
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	class write_Thread extends Thread {
		Socket socket=null;
		private BufferedReader reader;
		private PrintWriter write;
		public write_Thread(Socket socket) {	//����socket���������µ��߳�
			this.socket=socket;
		}
		public void run() {
			try {
				reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				write=new PrintWriter(socket.getOutputStream(),true);
				String msg;
				while((msg=reader.readLine())!=null) {
					System.out.println(msg);
					socketMan.writeAll(msg);	//���ͻ��������Ϣд������
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]) {
		ServerProcess server=new ServerProcess();
		server.getServer();
	}
}
