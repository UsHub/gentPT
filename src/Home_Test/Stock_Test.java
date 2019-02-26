package Home_Test;

import sqlPack.Stock;

public class Stock_Test {
	
	//Stock驱动模块
	public static void main(String[] args) {
		test2();
	}
	
	//Stock桩模块
	public static void test2(){
		try{
			Stock S_test=new Stock();
			S_test.setname("测试");
			S_test.settime("00:55:12");
			S_test.insertStock(S_test);
			System.out.println("连接数据库类成功~！");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("连接数据库类失败~！");
		}
	}

}
