package Home_Test;

public class ImageDto_Test {
	
	//ImageDto驱动模块
	public static void main(String[] args) {
		test3();
	}
	
	//ImageDto桩模块
	public static void test3(){
		try{
			ImageDto("ChangeLater","tu1","_2.jpg","cut/easy",140,140);
			System.out.println("切图类成功~！");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("切图类失败~！");
		}
		
	}

	private static void ImageDto(String string, String string2, String string3, String string4, int i, int j) {
		// TODO Auto-generated method stub
		
	}

}
