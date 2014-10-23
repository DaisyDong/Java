/*
 * �����ҿͻ��˳��򣬹����˴���Ĳ��֣������ͻ���socket
 * ͨ��������������ݣ���������ȡ�������˷��͵����ݣ�������ʾ���ı�����
 */
package chatRoom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame implements Runnable {
	private JPanel jpanel=new JPanel();
	private JLabel nameLable=new JLabel("������");	//��ǩ����
	private JTextField nameField=new JTextField();	//�ı������
	private JTextArea msgArea=new JTextArea();	//�ı������
	private JTextField sendField=new JTextField();
	private JScrollPane jScrollPanel=new javax.swing.JScrollPane();	//
	private BufferedReader reader;
	private PrintWriter write;
	private Socket socket;
	public Client(String title) {
		super(title);	
		this.setSize(360,340);	//���崰���ش�С
		this.add(jpanel);
		jpanel.setLayout(null);	//���岼��
		msgArea.setEditable(false);	//���ɱ���
		jpanel.add(nameLable);
		nameLable.setBounds(10,10,60,20);
		jpanel.add(nameField);
		nameField.setBounds(60,10,270,21);
		jpanel.add(sendField);
		sendField.setBounds(10,270,320,21);
		msgArea.setColumns(20);
		msgArea.setRows(5);
		jScrollPanel.setViewportView(msgArea);
		jpanel.add(jScrollPanel);
		jScrollPanel.setBounds(10,40,320,220);
		ActionListener aListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				write.println(nameField.getText()+":"+sendField.getText());
				sendField.setText("");
			}
		};
		sendField.addActionListener(aListener);
	}
	public void run() {
		while(true) {
			try {
				msgArea.append(reader.readLine()+"\n");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	void getSocket() {
		msgArea.append("���������������");
		try {
			socket=new Socket("172.17.132.170",9999);
			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			write=new PrintWriter(socket.getOutputStream(),true);
			new Thread(this).start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		Client client=new Client("ChatRoom!");
		client.setVisible(true);
		client.getSocket();
	}
}
