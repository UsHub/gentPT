package Home_Test;

public class GameOver_Test {
	
	//GameOver驱动模块
	public static void main(String[] args) {
		test4();
	}
	
	//GameOver桩模块
	public static void test4(){
		try{
			if(GameOver()){
				System.out.println("赢了——游戏判断类成功");
			}else{
				System.out.println("输了——游戏判断类成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("游戏判断类失败~！");
		}
	}

	private static boolean GameOver() {
		// TODO Auto-generated method stub
		return false;
	}

}
