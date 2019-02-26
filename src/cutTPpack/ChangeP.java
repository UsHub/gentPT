package cutTPpack;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JButton;

public class ChangeP extends JFrame implements ActionListener,MouseListener{

	static JFrame frame;
	private JTextField text;
	private ImageIcon p;
	private JButton button2;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private int n=7;

	/**
	 * Launch the application.
	 */	
	public ChangeP(ChangeP cp){
		this.frame=cp;
	}
	
	public static void main(String[] args) {
		try{
				frame=new ChangeP();
				frame.setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public ChangeP() {
		super();
		setBounds(100, 100, 1220, 700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 400, 300);
		getContentPane().add(panel1);
		
		p=new ImageIcon("preview/tu1.jpg");
		label1 = new JLabel(p);
		label1.addMouseListener(this);
		panel1.add(label1);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(410, 0, 400, 300);
		getContentPane().add(panel2);
		
		p=new ImageIcon("preview/tu2.jpg");
		label2 = new JLabel(p);
		label2.addMouseListener(this);
		panel2.add(label2);
		
		JPanel panel3 = new JPanel();
		panel3.setBounds(820, 0, 400, 300);
		getContentPane().add(panel3);
		
		p=new ImageIcon("preview/tu3.jpg");
		label3 = new JLabel(p);
		label3.addMouseListener(this);
		panel3.add(label3);
		
		JPanel panel4 = new JPanel();
		panel4.setBounds(0, 300, 400, 300);
		getContentPane().add(panel4);
		
		p=new ImageIcon("preview/tu4.jpg");
		label4 = new JLabel(p);
		label4.addMouseListener(this);
		panel4.add(label4);
		
		JPanel panel5 = new JPanel();
		panel5.setBounds(410, 300, 400, 300);
		getContentPane().add(panel5);
		
		p=new ImageIcon("preview/tu5.jpg");
		label5 = new JLabel(p);
		label5.addMouseListener(this);
		panel5.add(label5);
		
		JPanel panel6 = new JPanel();
		panel6.setBounds(820, 300, 400, 300);
		getContentPane().add(panel6);
		
		p=new ImageIcon("preview/tu6.jpg");
		label6 = new JLabel(p);
		label6.addMouseListener(this);
		panel6.add(label6);
		
		button2 = new JButton("确定");
		button2.setBounds(0, 600, 1220, 40);
		button2.setFont(new Font("楷体",Font.BOLD,20));
		button2.setBackground(new Color(150,255,250));
		button2.addActionListener(this);
		getContentPane().add(button2);
		
		JMenuBar menuBar1 = new JMenuBar();
		menuBar1.setBounds(0, 0, 1200, 80);
		setJMenuBar(menuBar1);
		
		text = new JTextField();
		menuBar1.add(text);
		text.setColumns(10);
		
		Button button1 = new Button("添加图片");
		button1.addActionListener(new addAction());
		menuBar1.add(button1);
//		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
//	private void initialize() {
//	}
	
	public String getText() {
		return text.getText();
	}

	public void addClick(){
		FileDialog fd = new FileDialog(this, "图片选择", FileDialog.LOAD);
		fd.setVisible(true);
		if(fd.getFile()==null)
			return;
		String imagename = fd.getDirectory() + fd.getFile();
		Copy ff=new Copy(imagename,"preview/tu"+n+".jpg");
		text.setText(imagename);
		JOptionPane.showMessageDialog(this, "图片添加成功！","提示",JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
	
	class addAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			addClick();
			n++;
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==label1){
			text.setText("preview/tu1.jpg");
		}
		if(e.getSource()==label2){
			text.setText("preview/tu2.jpg");
		}
		if(e.getSource()==label3){
			text.setText("preview/tu3.jpg");
		}
		if(e.getSource()==label4){
			text.setText("preview/tu4.jpg");
		}
		if(e.getSource()==label5){
			text.setText("preview/tu5.jpg");
		}
		if(e.getSource()==label6){
			text.setText("preview/tu6.jpg");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==button2){
			System.out.println(getText());
			try {
				AudioClip sound=Applet.newAudioClip(new File("海绵宝宝.mp3").toURL());
				sound.play();
			} catch (MalformedURLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}

}
