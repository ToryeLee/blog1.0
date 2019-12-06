package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Counter extends HttpServlet {


     Connection conn=null;
     PreparedStatement ps=null;
     DBConn dbc=new DBConn();

    public Counter() {
        super();
    }

    public  void writeCount(String articleid, int count) {
    	dbc.LoadDatabase();
		conn=dbc.ConnecteDatabase();
		String sql="update article set readcount=? where articleid=?";
		System.out.println("here is running 11111 + count="+count);
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, count);
			ps.setString(2, articleid);
			ps.executeUpdate();
			dbc.Close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public  int readCount(String articleid) {
        dbc.LoadDatabase();
        conn=dbc.ConnecteDatabase();
        ResultSet re;
        int count=0;
        String sql="select readcount from article where articleid=?";
        try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, articleid);
			re=ps.executeQuery();
			if(re.next()){
				count=re.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return count;
    }

    public void init() throws ServletException {
        // Put your code here
    }

}