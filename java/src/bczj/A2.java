/**
 * �����ֽ���InputStream��OutputStream�Ĳ���
 */
package bczj;
import java.io.*;

public class A2 {
	public static void main(String args[]) {
		InputStream in = System.in;
		try{
			byte[] cs=new byte[1204];
			System.out.println("ʹ��byte");
			while(in.read(cs)!=-1){//read()������Ҫ�ֽ�Ϊ������
				String str=new String(cs).trim();
				System.out.println(str);
			}
			in.close();
			
			OutputStream out=System.out;
			cs="����ʹ��outputStream\n".getBytes();
			out.write(cs);
			String str="����ʹ���ַ����滮";
			cs=str.getBytes();
			out.write(cs); 
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
