package Home_Test;

import PTpack.BBtry;

public class BBtry_Test {
	
	//����ģ��BBtry
	public static void main(String[] args) {
		test1();
	}
	
	//׮ģ��BBtry
	public static void test1(){
		try{
		BBtry B_Test=new BBtry();
		B_Test.setVisible(true);
		System.out.println("ƴͼ���ڵ����ɹ���~");
		}catch(Exception e){
			System.out.println("ƴͼ���ڵ���ʧ��~������");
			e.printStackTrace();
		}
	}

}
