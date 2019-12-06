package bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import user.Article;
import user.Comment;

public class CommentOp {
	Connection conn=null;
    PreparedStatement ps=null;
    DBConn dbc=new DBConn();

	public int insertComment(String msg, String articleid, String logname) {
		// TODO Auto-generated method stub
		dbc.LoadDatabase();
		conn=dbc.ConnecteDatabase();
		String sql="insert into comment values(?,?,?,?)";
		Date times = new Date(System.currentTimeMillis());
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, logname);
			ps.setString(2, articleid);
			ps.setString(3, msg);
			ps.setDate(4, times);
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArrayList<Comment> getComment(String articleid){
		dbc.LoadDatabase();
		conn=dbc.ConnecteDatabase();
		ResultSet re;
		String sql="select * from comment where articleid=?";
		ArrayList<Comment> Clist = new ArrayList<Comment>();
		
//		System.out.println("here is runing 1;number is "+currentPage+"pageCount is "+pageCount+"number is "+number);
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, articleid);
			re=ps.executeQuery();
//			System.out.println("here is runing 2; sql is "+sql);
			while( re.next() ){
				Comment comment=new Comment();
				comment.setLogname(re.getString(1));
				comment.setArticleid(re.getString(2));
				comment.setMsg(re.getString(3));
				comment.setTimes(re.getDate(4));
				Clist.add(comment);
//				System.out.println("here is runing 3: bookname is " +book.getBname());
			}
			
			dbc.Close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Clist;
		
	}

}
