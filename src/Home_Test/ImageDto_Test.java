package Home_Test;

public class ImageDto_Test {
	
	//ImageDto����ģ��
	public static void main(String[] args) {
		test3();
	}
	
	//ImageDto׮ģ��
	public static void test3(){
		try{
			ImageDto("ChangeLater","tu1","_2.jpg","cut/easy",140,140);
			System.out.println("��ͼ��ɹ�~��");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("��ͼ��ʧ��~��");
		}
		
	}

	private static void ImageDto(String string, String string2, String string3, String string4, int i, int j) {
		// TODO Auto-generated method stub
		
	}

}
