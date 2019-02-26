package Home_Test;

import PTpack.BBtry;

public class BBtry_Test {
	
	//驱动模块BBtry
	public static void main(String[] args) {
		test1();
	}
	
	//桩模块BBtry
	public static void test1(){
		try{
		BBtry B_Test=new BBtry();
		B_Test.setVisible(true);
		System.out.println("拼图窗口弹出成功！~");
		}catch(Exception e){
			System.out.println("拼图窗口弹出失败~！！！");
			e.printStackTrace();
		}
	}

}
