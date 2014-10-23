package udp;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.net.*;

public class Client extends JFrame implements Runnable,ActionListener {
	int port;
	MulticastSocket socket=null;
	InetAddress group=null;
	JButton ince=new JButton("��ʼ����");
	JButton stop=new JButton("ֹͣ����");
	JTextArea inceAr=new JTextArea(10,10);
	JTextArea inced=new JTextArea(10,10);
	Thread thread;
	boolean b=false;
	public Client() {
		super("�㲥���ݱ�");
		thread=new Thread(this);
		ince.addActionListener(this);	//�󶨰�ťince�ĵ����¼�
		stop.addActionListener(this);	//�󶨰�ťstop�����¼�
		inceAr.setForeground(Color.blue);	//ָ���ı��������ֵ���ɫ
		JPanel north=new JPanel();	//����JPanel����
		north.add(ince);	//����ť��ӵ������
		north.add(stop);
		add(north,BorderLayout.NORTH);
		JPanel center=new JPanel();
		center.setLayout(new GridLayout(1,2));	//������岼��
		center.add(inceAr);
		center.add(inced);
		add(center,BorderLayout.CENTER);
		validate();
		port=9999;
		try {
			group=InetAddress.getByName("224.255.10.0");	//��㲥�͵�ַ��������
			socket=new MulticastSocket(port);	//�󶨶��㲥�׽���
			socket.joinGroup(group);	//����㲥��
		}catch(Exception e){
			e.printStackTrace();
		}
		setBounds(100,50,360,380);
		setVisible(true);
	}
	public void run() {
		while(true) {
			byte data[]=new byte[1024];
			DatagramPacket packet=null;
			packet=new DatagramPacket(data,data.length,group,port);
			try {
				socket.receive(packet);
				String message=new String(packet.getData(),0,packet.getLength());	//��ȡ���ݱ��е�����
				inceAr.setText("���ڽ��ܵ����ݣ�\n"+message);
				inced.append(message+"\n");
			}catch(Exception e){
				e.printStackTrace();
			}
			if(b==true){
				break;
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ince) {
			ince.setBackground(Color.pink);	//���ð�ť����ɫ
			stop.setBackground(Color.gray);
			if(!(thread.isAlive())) {
				thread=new Thread(this);
			}
			thread.start();
			b=false;
		}
		if(e.getSource()==stop) {
			ince.setBackground(Color.yellow);
			stop.setBackground(Color.red);
			b=true;
		}
	}
	public static void main(String args[]) {
		Client rec=new Client();
		rec.setSize(460,200);
	}
}
