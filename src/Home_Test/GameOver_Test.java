package Home_Test;

public class GameOver_Test {
	
	//GameOver����ģ��
	public static void main(String[] args) {
		test4();
	}
	
	//GameOver׮ģ��
	public static void test4(){
		try{
			if(GameOver()){
				System.out.println("Ӯ�ˡ�����Ϸ�ж���ɹ�");
			}else{
				System.out.println("���ˡ�����Ϸ�ж���ɹ�");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("��Ϸ�ж���ʧ��~��");
		}
	}

	private static boolean GameOver() {
		// TODO Auto-generated method stub
		return false;
	}

}
