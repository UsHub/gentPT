package Home_Test;

import sqlPack.Stock;

public class Stock_Test {
	
	//Stock����ģ��
	public static void main(String[] args) {
		test2();
	}
	
	//Stock׮ģ��
	public static void test2(){
		try{
			Stock S_test=new Stock();
			S_test.setname("����");
			S_test.settime("00:55:12");
			S_test.insertStock(S_test);
			System.out.println("�������ݿ���ɹ�~��");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("�������ݿ���ʧ��~��");
		}
	}

}
