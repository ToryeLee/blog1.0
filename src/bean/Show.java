package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import user.Article;

public class Show {
	Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs = null;
    Statement stmt = null;
    DBConn dbc=new DBConn();
    
    public int getCount(){
    	dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
        String sql = "select count(*) from article";	
		int flag = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			if(rs.next())
				flag = rs.getInt(1);
			
			dbc.Close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
    }
    
    public int getTCount(String atype){
    	dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
    	int flag = 0;
    	
    	if(!atype.equals("¡Ù—‘")){
    		String sql = "select count(*) from article where atype=?";			
    		try {
    			ps = conn.prepareStatement(sql);
    			ps.setString(1, atype);
    			rs = ps.executeQuery();
			
    			if(rs.next())
    				flag = rs.getInt(1);
			
    			dbc.Close();
			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
    	else{
    		String sql="select count(*) from msg";
    		try {
    			ps = conn.prepareStatement(sql);
    			rs = ps.executeQuery();
			
    			if(rs.next())
    				flag = rs.getInt(1);
			
    			dbc.Close();
			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
		
		return flag;
    } 
    
    
    
    public ArrayList<Article> getArticle(int currentPage, int pageCount){

//    	String sql="select * from book limit ?,?";
    	
    	int number=currentPage*pageCount;
    	
		String sql = "select * from article a where articleid in("+
				     "select top "+pageCount+" articleid from("+
					 "select top "+number+" articleid from article order by articleid asc )"+
					 "aa order by aa.articleid desc) order by a.articleid asc";
//		String sql="select * from book order by bname";
		dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
		
		ArrayList<Article> Alist = new ArrayList<Article>();
		
//		System.out.println("here is runing 1;number is "+currentPage+"pageCount is "+pageCount+"number is "+number);
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("here is runing 2; sql is "+sql);
			while( rs.next() ){
				Article article = new Article();
				article.setUserid(rs.getString(1));
				article.setArticleid(rs.getString(2));
				article.setTitle(rs.getString(3));
				article.setAtype(rs.getString(4));
				article.setIntro(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setTimes(rs.getDate(7));
				article.setLikes(rs.getInt(8));
				article.setReadcount(rs.getInt(9));
				Alist.add(article);
//				System.out.println("here is runing 3: bookname is " +book.getBname());
			}
			
			dbc.Close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Alist;
    }

    public ArrayList<Article> getTArticle(int currentPage, int pageCount,String atype){

//    	String sql="select * from book limit ?,?";
    	
    	int number=currentPage*pageCount;
    	
		String sql = "select * from article a where atype='"+atype+"'and articleid in("+
				     "select top "+pageCount+" articleid from("+
					 "select top "+number+" articleid from article order by articleid asc )"+
					 "aa order by aa.articleid desc) order by a.articleid asc";
//		String sql="select * from book order by bname";
		dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
		
		ArrayList<Article> Alist = new ArrayList<Article>();
		
//		System.out.println("here is runing 1;number is "+currentPage+"pageCount is "+pageCount+"number is "+number);
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("here is runing 2; sql is "+sql);
			while( rs.next() ){
				Article article = new Article();
				article.setUserid(rs.getString(1));
				article.setArticleid(rs.getString(2));
				article.setTitle(rs.getString(3));
				article.setAtype(rs.getString(4));
				article.setIntro(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setTimes(rs.getDate(7));
				article.setLikes(rs.getInt(8));
				article.setReadcount(rs.getInt(9));
				Alist.add(article);
//				System.out.println("here is runing 3: bookname is " +book.getBname());
			}
			
			dbc.Close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Alist;
    }
    
    
	public ArrayList<Article> searchArticle(String search) {
		// TODO Auto-generated method stub
		dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
    	Statement stmt = null;
    	search="%"+search+"%";
        String sql="select * from article where title like '"+search+"' or atype like '"+search+"' or content like '"+search+"' or intro like '"+search+"' ";
        ResultSet re;
        
        ArrayList<Article> Alist=new ArrayList<Article>();
        
        try {
			stmt=conn.createStatement();
			re=stmt.executeQuery(sql);
			while(re.next()){
				Article article=new Article();
				article.setUserid(re.getString(1));
				article.setArticleid(re.getString(2));
				article.setTitle(re.getString(3));
				article.setAtype(re.getString(4));
				article.setIntro(re.getString(5));
				article.setContent(re.getString(6));
				article.setTimes(re.getDate(7));
				article.setLikes(re.getInt(8));
				article.setReadcount(re.getInt(9));
				Alist.add(article);
			}
			dbc.Close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Alist;
	}

}
