package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikesOp {
	Connection conn=null;
    PreparedStatement ps=null;
    DBConn dbc=new DBConn();

	public int Likesadd(String articleid) {
		// TODO Auto-generated method stub
		dbc.LoadDatabase();
		conn=dbc.ConnecteDatabase();
		String sql="update article set likes=likes+1 where articleid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, articleid);
			ps.executeUpdate();
			return 1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
	public int Likesreduce(String articleid){
		dbc.LoadDatabase();
		conn=dbc.ConnecteDatabase();
		String sql="update article set likes=likes-1 where articleid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, articleid);
			ps.executeUpdate();
			return 1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

}
