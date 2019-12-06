package bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.Article;
import user.User;

public class ArticleOp {
	Connection conn=null;
    PreparedStatement ps=null;
    DBConn dbc=new DBConn();
	public int insertArticle(User user,String title,String atype,String content,String intro){
		dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
        String articleid=getid();
        Date times = new Date(System.currentTimeMillis());
//        System.out.println("times is "+times);
        
        String userid=user.getUserid();
//        System.out.println(userid+articleid+title+atype+intro+content+times);
        String sql="insert into article values (?,?,?,?,?,?,?,?,?)";
        try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, articleid);
			ps.setString(3,title);
			ps.setString(4, atype);
			ps.setString(5, intro);
			ps.setString(6, content);
			ps.setDate(7, times);
			ps.setInt(8, 0);
			ps.setInt(9, 0);
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return 0;
	}
	public String getid(){
		dbc.LoadDatabase();
		conn=dbc.ConnecteDatabase();
		ResultSet re;
		
		String sql="select top 1 articleid from article order by articleid desc";
		
		int count=0;
    	try {
			ps=conn.prepareStatement(sql);
			re=ps.executeQuery();
	    	if(re.next()){
	    		count=Integer.parseInt(re.getString(1));
	    		count+=1;}
	    	
//	    	System.out.println("here is running 1 count is "+ count );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String tail=""+count;
    	String nid="";
    	while(nid.length()+tail.length()<10){
    		nid+="0";
    	}
    	nid=nid+tail;
 //   	System.out.println("here is running nid is"+nid);
    	return nid;
	}
	public Article getArticle(String articleid){
		dbc.LoadDatabase();
		conn=dbc.ConnecteDatabase();
		ResultSet re;
		Article article=new Article();
		String sql="select * from article where articleid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, articleid);
			re=ps.executeQuery();
			if(re.next()){
				String content=re.getString(6);
//				content=content.replaceAll("\n","<br\\>");
//				content=content.replaceAll("<", "&lt;");
//				content=content.replaceAll(">","&gt;");
//				System.out.println("content is "+content);
				
				article.setUserid(re.getString(1));
				article.setArticleid(re.getString(2));
				article.setTitle(re.getString(3));
				article.setAtype(re.getString(4));
				article.setIntro(re.getString(5));
				article.setContent(content);
				article.setTimes(re.getDate(7));
				article.setLikes(re.getInt(8));
				article.setReadcount(re.getInt(9));
				return article;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public int updateArticle(Article article) {
		// TODO Auto-generated method stub
		dbc.LoadDatabase();
		conn=dbc.ConnecteDatabase();
		Date times=new Date(System.currentTimeMillis());
		String sql="update article set title=?,atype=?,intro=?,content=?,times=? where articleid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, article.getTitle());
			ps.setString(2, article.getAtype());
			ps.setString(3, article.getIntro());
			ps.setString(4, article.getContent());
			ps.setDate(5, times);
			ps.setString(6, article.getArticleid());
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int deleteArticle(String articleid) {
		// TODO Auto-generated method stub
		dbc.LoadDatabase();
		conn=dbc.ConnecteDatabase();
		String sql="delete article where articleid=?";
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
