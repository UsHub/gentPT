package logon;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import PTpack.BBtry;
import sqlPack.Stock;





public class Logon extends JFrame implements ActionListener{
	private static Logon frame;
	private JButton s_button;
	private static JTextField n_text;
	private JLabel t1;
	private JLabel t2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
			frame=new Logon();	
			//AWTUtilities.setWindowOpaque(frame, false);
			frame.setVisible(true);
			}

	/**
	 * Create the application.
	 */
	public Logon(){
		super();
		//setBackground(bgColor);
//		setUndecorated(true);
//		AWTUtilities.setWindowOpaque(this, false);
		setResizable(false);
		setTitle("��½����");
		setBounds(650,300,400,350);
		ImageIcon icon=new ImageIcon("1.png");
		this.setIconImage(icon.getImage()); //����ͼ��
		String path="����ͼƬ3.jpg";
		ImageIcon background=new ImageIcon(path);
		JLabel label = new JLabel(background);  //������ͼƬ�ŵ�һ����ǩ��
		label.setBounds(0, 0 ,this.getWidth(), this.getHeight()); //����ǩ�Ĵ�С����ΪͼƬ�պ�����������	
		JPanel imagePanel = (JPanel) this.getContentPane(); //�����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		imagePanel.setOpaque(false); 
		this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); //�ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����ͼƬ
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t1=new JLabel("����ƴͼ",JLabel.CENTER);
		t1.setBorder(new TitledBorder(null,"",TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION,null,null));
		t1.setForeground(new Color(100,100,200));
		t1.setFont(new Font("����",Font.BOLD,30));
		t1.setBounds(39,28,320,60);
		getContentPane().add(t1);
		t2=new JLabel();
		t2.setText("�û���");
		t2.setForeground(new Color(100,100,200));
		t2.setFont(new Font("����",Font.BOLD,25));
		t2.setBounds(80, 105, 100, 70);
		getContentPane().add(t2);
		n_text=new JTextField();
		n_text.setBounds(170, 120, 140, 40);
		//n_text.setOpaque(false);
		n_text.setBackground(new Color(173,174,168));
		getContentPane().add(n_text);
		s_button=new JButton();
		s_button.setText("��ʼ��Ϸ");
		s_button.setForeground(new Color(100,100,200));
		s_button.setFont(new Font("����",Font.BOLD,18));
		s_button.setBounds(140,200,120,40);
		getContentPane().add(s_button);
		s_button.addActionListener(this); 
		
	}
	
	public static String getN_text() {
		return n_text.getText();
	}
	
	//��ʼ��ť������
	@Override
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
		String wname=n_text.getText();
		if(wname.equals("")){
			JOptionPane.showMessageDialog(getContentPane(), "�������û�����",
					"��Ϣ��ʾ��",JOptionPane.INFORMATION_MESSAGE);
			
		}
		else{
			Stock stock=new Stock();
			stock.setname(wname);
			stock.insertStock(stock);
			BBtry framePT=new BBtry();
			new BBtry(framePT);
			framePT.getTimer().start();
			framePT.setVisible(true);
//			frame.setVisible(false);
			this.setVisible(false);
			
		}	
		
	}
}
