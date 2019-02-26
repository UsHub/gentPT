package PTpack;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.awt.AWTUtilities;
import com.sun.glass.events.WindowEvent;

import cutTPpack.ImageDto;
import jdk.nashorn.internal.scripts.JO;
import logon.Logon;
import sqlPack.Stock;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.awt.WindowClosingListener;

public class BBtry extends JFrame implements 
ActionListener,MouseListener,Runnable{

	private JPanel contentPane;
	static BBtry frame=new BBtry() ;
	static Logon log;
	//static AudioPlayer ap;
	private JButton emptyButton;
	private Thread timer=new Thread(this);
	private JButton backButton;//返回按钮
	private JButton changeButton;//换图按钮
	private JButton paushButton;//暂停按钮
	private JButton countinuButton;//继续按钮
	private JButton difficultyButton;//难度按钮
	private JButton restartButton;//重新开始按钮
	private JLabel paushLabelTP;//暂停时界面
	private JPanel PTpanel;//拼图面板
	private JLabel srcTP;//开始面板
	private JPanel labelPane;
	private JPanel paushPane;//暂停面板
	private boolean suspend=true;//控制线程暂停/继续参数
	private String control="";
	private int m=0,f=0,s=0;//时间参数
	private int n=2;
	private int dn=3;
	private String targetDirC="cut/easy";
	private static File inRight;
	private static File inWrong;
	private static URL r;
	private static URL w;
	private static AudioClip acRight;
	private static AudioClip acWrong;
	/**
	 * Launch the application.
	 */
	public BBtry(BBtry frame){
		this.frame=frame;
	}
	
	//获得线程参数
	public Thread getTimer() {
		return timer;
	}
	
	public static void main(String[] args) {
				try {
					log=new Logon();
					log.setVisible(true);
					inRight=new File("backgroundMusic/点击正确按钮声音.wav");
					inWrong=new File("backgroundMusic/点击错误按钮声音.wav");
					r=inRight.toURL();
					w=inWrong.toURL();
					acRight=Applet.newAudioClip(r);
					acWrong=Applet.newAudioClip(w);
//					changeImage("ChangeBefore","tu1",".jpg");
//					cut("ChangeLater","tu1","_2.jpg","cut/easy",140,140);
//					frame=new BBtry();
////					Window frame1 = new BBtry();
////					AWTUtilities.setWindowOpacity(frame1, 0.6f);
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	/**
	 * Create the frame.
	 */
	//桩模块BBtry
	public BBtry() {
		super();
//		setUndecorated(true);
//		AWTUtilities.setWindowOpaque(this, false);
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				// TODO Auto-generated method stub
				//super.windowClosing(e);
				if(PTpanel.isVisible()&&!GameOver()){
					setSuspend(true);
					int ret = JOptionPane.showConfirmDialog(null, "你正在游戏中确定不玩了吗？",
							"友情提示",JOptionPane.OK_OPTION);
					if(ret==JOptionPane.OK_OPTION){
						System.exit(0);
					}else{
						setSuspend(false);
					}
				}else{
					System.exit(0);
				}
			}
		});
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//setUndecorated(true);
		setBounds(500, 100, 425, 485);
		
		setTitle("时间——00:00:00");
		setResizable(false);		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
		
		JMenuBar JMB=new JMenuBar();
		
		backButton=new JButton(" >>返回<< ");
		backButton.setMargin(new Insets(0,0,0,0));
		backButton.addMouseListener(this);
		JMB.add(backButton);
		backButton.setVisible(false);
		
		changeButton=new JButton("  >>换图<<  ");
		changeButton.setMargin(new Insets(0,0,0,0));
//		changeButton.setToolTipText("换图");
		changeButton.addMouseListener(this);
		JMB.add(changeButton);
		
		paushButton=new JButton("  >>暂停<<  ");
		paushButton.setMargin(new Insets(0,0,0,0));
		paushButton.addMouseListener(this);
		paushButton.setVisible(false);
		JMB.add(paushButton);
		
		countinuButton=new JButton("  >>继续<<  ");
		countinuButton.setMargin(new Insets(0,0,0,0));
		countinuButton.addMouseListener(this);
		countinuButton.setVisible(false);
		JMB.add(countinuButton);
		
		restartButton=new JButton(">重新开始<");
		restartButton.setMargin(new Insets(0,0,0,0));
		restartButton.addMouseListener(this);
		JMB.add(restartButton);
		restartButton.setVisible(false);
		
		setJMenuBar(JMB);
		//setIconImage(Toolkit.getDefaultToolkit().getImage());
		
		labelPane=new JPanel();
		srcTP=new JLabel();
		srcTP.setIcon(new ImageIcon("ChangeLater/tu1_2.jpg"));
		srcTP.addMouseListener(this);
		labelPane.add(srcTP,BorderLayout.CENTER);
		contentPane.add(labelPane,BorderLayout.NORTH);
		
		paushLabelTP=new JLabel(new ImageIcon("ChangeLater/tu1_2.jpg"));
		labelPane.add(paushLabelTP,BorderLayout.NORTH);
		paushLabelTP.setVisible(false);
		
		TJTP(3);

	}
	
	//图片随机排序
	private String[][] TPorder(String ImageName){	
		int orderdn=getDn();
		
		String[][] setTPorder=new String[orderdn][orderdn];
		for(int row=0;row<orderdn;row++){
			for(int col=0;col<orderdn;col++){
				setTPorder[row][col]=getTargetDirC()+"/"+ImageName+"_"+row+""+col+".jpg";
			}
		}
		String[][] getTPorder=new String[orderdn][orderdn];
		for(int row=0;row<orderdn;row++){
			for(int col=0;col<orderdn;col++){
				while(getTPorder[row][col]==null){
					int r=(int)(Math.random()*orderdn);
					int c=(int)(Math.random()*orderdn);
					if(setTPorder[r][c]!=null){
						getTPorder[row][col]=setTPorder[r][c];
						setTPorder[r][c]=null;
					}
				}
			}
		}
		return getTPorder;
	}
	
	//添加图片到面板
	public void TJTP(int dn){
		int t=getN();
			t--;
			if(t==0){
				t=5;
			}
		String emptySource="cut/easy/tu"+t+"_22.jpg";
		if(dn==4){
			emptySource="cut/normal/tu"+t+"_33.jpg";
		}
		if(dn==5){
			emptySource="cut/difficult/tu"+t+"_44.jpg";
		}
		PTpanel=new JPanel();
		GridLayout gl_otherPanel = new GridLayout(0,dn);
		PTpanel.setLayout(gl_otherPanel);
		String[][] getTPorder=TPorder("tu"+t);
		for(int row=0;row<dn;row++){
			for(int col=0;col<dn;col++){
				final JButton tryButton=new JButton();
				tryButton.setName(row+""+col);
				tryButton.setIcon(new ImageIcon(getTPorder[row][col]));
				if(getTPorder[row][col].equals(emptySource)){
					tryButton.setIcon(new ImageIcon("nothing"));
					emptyButton=tryButton;
				}
					tryButton.addActionListener(new ImgButtonAction());
					PTpanel.add(tryButton);
			}
		}
		contentPane.add(PTpanel,BorderLayout.CENTER);
		PTpanel.setVisible(false);
	}
	
	//调整图片大小
	public static String changeImage(String ImageFile,String ImageName,String ImageType) throws IOException{
		File file=new File(ImageFile+"/"+ImageName+ImageType);
		Image src;
		src=javax.imageio.ImageIO.read(file);
		int width=420;
		int height=420;
		BufferedImage tag=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(src, 0, 0, width,height,null);
		String ImageCutPath="ChangeLater/"+ImageName+"_2"+ImageType;
		FileOutputStream out=new FileOutputStream(ImageCutPath);
		ImageIO.write(tag, "JPG", out);
		out.close();
		return ImageFile+"/"+ImageName+ImageType;
	}
	public static String changeImage(String ImageFile,String ImageName,String ImageType,int w) throws IOException{
		File file=new File(ImageFile+"/"+ImageName+ImageType);
		Image src;
		src=javax.imageio.ImageIO.read(file);
		int width=w;
		int height=300;
		BufferedImage tag=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(src, 0, 0, width,height,null);
		String ImageCutPath="change/"+ImageName+"_2"+ImageType;
		FileOutputStream out=new FileOutputStream(ImageCutPath);
		ImageIO.write(tag, "JPG", out);
		out.close();
		return ImageFile+"/"+ImageName+ImageType;
	}
	
	//切图
	public static ImageDto cut(String sourceFile,String sourceName,
			String sourceType,String targetDir, int width, int height)
					throws Exception{
		File fileImage=new File(sourceFile+"/"+sourceName+sourceType);
		List<File> list=new ArrayList<File>();
		BufferedImage source=ImageIO.read(fileImage);
		int sWidth=source.getWidth();		//图片宽度
		int sHeight=source.getHeight();		//图片高度
		if(sWidth>width && sHeight>height){
			int cols=0;			//横向切片总数
			int rows=0;			//纵向切片总数
			int eWidth=0;		//末端切片宽度
			int eHeight=0;		//末端切片高度
			if(sWidth%width==0){
				cols=sWidth/width;
			}else{
				eWidth=sWidth%width;
				cols=sWidth/width+1;
			}
			if(sHeight%height==0){
				rows=sHeight/height;
			}else{
				eHeight=sHeight%height;
				rows=sHeight/height+1;
			}
			String fileName=null;
			File file=new File(targetDir);
			if(!file.exists()){
				file.mkdirs();
			}
			BufferedImage image=null;
			int cWidth=0;		//当前切片宽度
			int cHeight=0;		//当前切片高度
			for(int i=0;i<rows;i++){
				for(int j=0;j<cols;j++){
					cWidth=getWidth(j,cols,eWidth,width);
					cHeight=getHeight(i,rows,eHeight,height);
					//x坐标，y坐标，宽度，高度
					image=source.getSubimage(j*width, i*height, cWidth,cHeight);
					fileName=targetDir+"/"+sourceName+"_"+i+""+j+".jpg";
					file=new File(fileName);
					ImageIO.write(image, "JPG", file);
					list.add(file);
				}
			}
		}
		return new ImageDto(sWidth,sHeight,list);
		
	}
	
//	public static ImageDto cut(String source,String targetDir,
//			int width,int height)throws Exception{
//		return cut(new File(source),targetDir,width,height);
//	}
	
	private static int getWidth(int index, int cols, int endWidth, int width){
		if(index==cols-1){
			if(endWidth!=0){
				return endWidth;
			}
		}
		return width;
	}
	
	private static int getHeight(int index,int rows, int endHeight,
			int height){
		if(index==rows-1){
			if(endHeight!=0){
				return endHeight;
			}
		}
		return height;
	}
	
	//计时器线程
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String miao,fen,shi;
		while(true){
			synchronized(control){
				if(!suspend){
					try {
						//control.wait();
						timer.sleep(990);
						if(GameOver()){
							setSuspend(true);
							Stock st=new Stock();
							st.setname(Logon.getN_text());
							st.settime(getTitle());
							st.insertStock(st);
							JOptionPane.showMessageDialog(null, getTitle(),"SUCCESSFULLY ! ! ~",JOptionPane.OK_OPTION,
									new ImageIcon("MenuImage/tu2_11.jpg"));
							this.dispose();
							log.setVisible(true);
							
						}
						m++;
						if(m>0&&m%60==0){
							f++;
						m=0;
						}
						if(f>0&&f%60==0){
							s++;
							f=0;
						}
						if(m<10){
							miao="0"+m;
						}else{
							miao=""+m;
						}
						if(f<10){
							fen="0"+f;
						}else{
							fen=""+f;
						}
						if(s<10){
							shi="0"+s;
						}else{
							shi=""+s;
						}
						frame.setTitle("时间——"+shi+":"+fen+":"+miao);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
	}
	
	//判断游戏是否结束
	public boolean GameOver(){
		int dn=getDn();
		int t=getN(),i=0,row=0,col=0;
		t--;
		if(t==0){
			t=5;
		}
		String ifSource="cut/easy/tu";
		if(dn==4){
			ifSource="cut/normal/tu";
		}
		if(dn==5){
			ifSource="cut/difficult/tu";
		}
		int ifnum=dn;
		for(row=0;row<dn;row++){
			if(row==dn-1){
				ifnum--; 
			}
			for(col=0;col<ifnum;col++){
				final JButton ifButton=(JButton)PTpanel.getComponent(i++);
				if(!(ifButton.getIcon().toString().equals(ifSource+t+"_"+row+""+col+".jpg"))){
					break;
				}
			}
			if(col!=ifnum){
				break;
			}
		}
		if(row==dn&&col==dn-1){
			return true;
		}else{
			return false;
		}
	}
	
	public void setSuspend(boolean suspend){
		if(!suspend){
			synchronized(control){
				control.notifyAll();
			}
		}
		this.suspend=suspend;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	public int getN() {
		return n;
	}
	
	public void setDn(int dn) {
		this.dn = dn;
	}
	public int getDn() {
		return dn;
	}
	
	public void setTargetDirC(String targetDirC) {
		this.targetDirC = targetDirC;
	}
	public String getTargetDirC() {
		return targetDirC;
	}
	
	//拼图按钮监听器
	class ImgButtonAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int n=frame.getN();
			if(n==1){
				n=5;
			}else{
				n--;
			}
			String emptyName=emptyButton.getName();
			
			char emptyRow=emptyName.charAt(0);
			char emptyCol=emptyName.charAt(1);
			JButton clickButton=(JButton)e.getSource();
			
			String clickName=clickButton.getName();
			char clickRow=clickName.charAt(0);
			char clickCol=clickName.charAt(1);
			if(Math.abs(clickRow-emptyRow)+Math.abs(clickCol-emptyCol)==1){
				emptyButton.setIcon(clickButton.getIcon());
				clickButton.setIcon(new ImageIcon("nothing"));
				emptyButton=clickButton;
				acRight.play();
			}
			else{
				acWrong.play();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton){
			s=0;f=0;m=-1;
			setSuspend(true);
			PTpanel.setVisible(false);
			paushButton.setVisible(false);
			countinuButton.setVisible(false);
			restartButton.setVisible(false);
			srcTP.setVisible(true);
		}else if(e.getSource()==changeButton){
			//new ChangeP().setVisible(true); 
			String changeDir=getTargetDirC();
			try {
				changeImage("ChangeBefore","tu"+n,".jpg");
				cut("ChangeLater","tu"+n,"_2.jpg",changeDir,140,140);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			srcTP.setIcon(new ImageIcon("ChangeLater/tu"+n+"_2.jpg"));
			paushLabelTP.setIcon(new ImageIcon("ChangeLater/tu"+n+"_2.jpg"));
			String[][] getTPorder=TPorder("tu"+n);
			int changedn=getDn();
			int i=0;
			for(int row=0;row<changedn;row++){
				for(int col=0;col<changedn;col++){
					final JButton tryButton=(JButton)PTpanel.getComponent(i++);
					tryButton.setIcon(new ImageIcon(getTPorder[row][col]));
					if(getTPorder[row][col].equals(changeDir+"/tu"+n+"_"+"22.jpg")){
						tryButton.setIcon(new ImageIcon("nothing"));
						emptyButton=tryButton;
					}
				}
			}
			n++;
			if(n>=6){
				n=1;
			}
		}
		else if(e.getSource()==srcTP){
			Object[] obj={"简单","普通","困难"};
			String s=(String) JOptionPane.showInputDialog(null,"请选择难度:\n","难度选择",
					JOptionPane.PLAIN_MESSAGE,new ImageIcon("MenuImage/tu2_11.jpg"),obj,"简单");
			if(s==null){
				//do nothing !
			}else{
				if(s=="简单"){
					int t=n;
					t--;
					if(t==0){
						t=5;
					}
					try {
						cut("ChangeLater","tu"+t,"_2.jpg","cut/easy",140,140);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					TJTP(3);
				
				}else if(s=="普通"){
					dn=4;
					targetDirC="cut/normal";
					int t=n;
					t--;
					if(t==0){
						t=5;
					}
					try {
						cut("ChangeLater","tu"+t,"_2.jpg",targetDirC,105,105);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					TJTP(4);
			}else if(s=="困难"){
				dn=5;
				targetDirC="cut/difficult";
				int t=n;
				t--;
				if(t==0){
					t=5;
				}
				try {
					cut("ChangeLater","tu"+t,"_2.jpg",targetDirC,84,84);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				TJTP(5);
				
			}
			setSuspend(false);
			srcTP.setVisible(false);
			PTpanel.setVisible(true);
			backButton.setVisible(true);
			changeButton.setVisible(false);
			paushButton.setVisible(true);
			restartButton.setVisible(true);
			//frame.getLayeredPane().setLayout(null);
			//frame.getLayeredPane().add(contentPane,new Integer(Integer.MAX_VALUE));
				
			}
		}else if(e.getSource()==paushButton){
				setSuspend(true);
				PTpanel.setVisible(false);
				paushLabelTP.setVisible(true);
				paushButton.setVisible(false);
				countinuButton.setVisible(true);
				restartButton.setVisible(false);
		}else if(e.getSource()==countinuButton){
				setSuspend(false);
				paushLabelTP.setVisible(false);
				PTpanel.setVisible(true);
				paushButton.setVisible(true);
				countinuButton.setVisible(false);
				restartButton.setVisible(true);
			
		}else if(e.getSource()==restartButton){
			m=0;f=0;s=0;
			int t=n;
			t--;
			if(t==0){
				t=5;
			}
			String emptySource="cut/easy/tu"+t+"_22.jpg";
			if(dn==4){
				emptySource="cut/normal/tu"+t+"_33.jpg";
			}
			if(dn==5){
				emptySource="cut/difficult/tu"+t+"_44.jpg";
			}
			String[][] getTPorder=TPorder("tu"+t);
			int i=0;
			for(int row=0;row<dn;row++){
				for(int col=0;col<dn;col++){
					final JButton tryButton=(JButton)PTpanel.getComponent(i++);
					tryButton.setIcon(new ImageIcon(getTPorder[row][col]));
					if(getTPorder[row][col].equals(emptySource)){
						tryButton.setIcon(new ImageIcon("nothing"));
						emptyButton=tryButton;
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==srcTP){
			srcTP.setIcon(new ImageIcon("MenuImage/srcEnterTP.jpg"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==srcTP){
			int m=getN();
			m--;
			if(m==0){
				m=5;
			}
			srcTP.setIcon(new ImageIcon("ChangeLater/tu"+m+"_2.jpg"));
		}
	}

}