package bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import user.*;

public class MessageOp {
	Connection conn=null;
    PreparedStatement ps=null;
    DBConn dbc=new DBConn();

	public int insertMsg(String msg, String logname) {
		// TODO Auto-generated method stub
		dbc.LoadDatabase();
		conn=dbc.ConnecteDatabase();
		String sql="insert into msg values(?,?,?)";
		Date times = new Date(System.currentTimeMillis());
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, logname);
			ps.setString(2, msg);
			ps.setDate(3, times);
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArrayList<Messages> getMessages(){
		dbc.LoadDatabase();
		conn=dbc.ConnecteDatabase();
		ResultSet re;
		String sql="select * from msg ";
		ArrayList<Messages> Mlist = new ArrayList<Messages>();
		
//		System.out.println("here is runing 1;number is "+currentPage+"pageCount is "+pageCount+"number is "+number);
		
		try {
			ps=conn.prepareStatement(sql);
			re=ps.executeQuery();
//			System.out.println("here is runing 2; sql is "+sql);
			while( re.next() ){
				Messages messages=new Messages();
				messages.setLogname(re.getString(1));
				messages.setMsg(re.getString(2));
				messages.setTimes(re.getDate(3));
				Mlist.add(messages);
//				System.out.println("here is runing 3: bookname is " +book.getBname());
			}
			
			dbc.Close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Mlist;
		
	}

}
