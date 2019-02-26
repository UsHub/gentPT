package sqlPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Stock {
	private String username;
	private String time;
	public void setname(String n){
		this.username=n;
	}
	public String getname(){
		return this.username;
	}
	public void settime(String t){
		this.time=t;
	}
	public String gettime(){
		return this.time;
	}
	//向数据库添加信息方法
	public void insertStock(Stock stock){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql:"
			 		+ "//127.0.0.1:3306/choose?serverTimezone=UTC&useSSL=false&user=root&password="
			 		+ "970517.Dw");
			PreparedStatement st=con.prepareStatement("insert into ranking values(?,?)");
			PreparedStatement st2=con.prepareStatement("update ranking set TIME=? where PLAYER='"+
			getname()+"'");
			st2.clearBatch();
			Statement stmt=con.createStatement();
			String sql="select* from ranking";
			ResultSet rs=stmt.executeQuery(sql);
			rs.beforeFirst();
			while(rs.next()){
				if(rs.getString(1).equals(getname())){
					if(gettime()!=null){
						st2.setString(1, gettime());
						st2.addBatch();
						st2.executeBatch();
					}
					break;
				}
			}
			if(rs.isAfterLast()){
				st.setString(1, stock.getname());
				st.setString(2, stock.gettime());
				st.executeUpdate();
			}
			}catch(Exception e){
				e.printStackTrace();
			}

		
	}
}
